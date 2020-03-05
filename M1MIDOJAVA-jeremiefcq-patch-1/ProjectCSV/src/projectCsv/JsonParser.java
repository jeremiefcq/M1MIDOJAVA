package projectCsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

public class JsonParser {
	Imperatifs[] rImplemented;
	Anonymisation[] aImplemented;
	
	private static String fileToString(String fileName) {
		  StringBuilder contentBuilder = new StringBuilder();
		    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) 
		    {
		 
		        String sCurrentLine;
		        while ((sCurrentLine = br.readLine()) != null) 
		        {
		            contentBuilder.append(sCurrentLine).append("\n");
		        }
		    } 
		    catch (IOException e) 
		    {
		        e.printStackTrace();
		    }
		    return contentBuilder.toString();
	}
	
	public JsonParser(Imperatifs[] rImplemented, Anonymisation[] aImplemented) {
		super();
		this.rImplemented = rImplemented;
		this.aImplemented = aImplemented;
	}

	private void addConstraints(ColumnHeader[] header ,String json, Anonymisation[] implemented) {
		int i = 0,j=0,k = 0, l = header.length;
		Gson gson = new GsonBuilder().create();
		Anonymazer[] rList = gson.fromJson(json, Anonymazer[].class);
		
		for(Anonymazer r: rList) {
			if(r  == null)
				throw new IllegalArgumentException("Unexpected commas in the file");
			i = 0;
			while(i < l && !header[i].name.equals(r.name) )
				i++;
			if(i==l)
				throw new IllegalStateException(String.format("Alias %s unfound in column names", r.name));
			
			k = 0;
			while(k < implemented.length && !implemented[k].alias.equals(r.changeTo))
				k++;
			if(k == implemented.length)
				throw new IllegalStateException(String.format("Anonymization Rule %s isn't implemented in the system", r.changeTo));
			header[i].hidingrule = implemented[k];

		}
	}
	
	private void addRulesFromJSON(ColumnHeader[] header ,String json, Imperatifs[] implemented) {
		int i = 0,j=0,k = 0, l = header.length;
		Gson gson = new GsonBuilder().create();
		Rule[] rList = gson.fromJson(json, Rule[].class);
		for(Rule r: rList) {
			System.out.println(r.toString());
			i = 0;
			while(i < l && !header[i].name.equals(r.name) )
				i++;
			if(i==l)
				throw new IllegalStateException(String.format("Alias %s unfound in column names", r.name));
			header[i].rules = new Imperatifs[r.should.length];
			header[i].nbrules += r.should.length;
			j = 0;
			for(String should: r.getShould()) {
				k = 0;
				while(k < implemented.length && !implemented[k].alias.equals(should))
					k++;
				if(k == implemented.length)
					throw new IllegalStateException(String.format("Rule %s isn't implemented in the system", should));
				header[i].rules[j] = implemented[k];
				j++;
			}
		}
	}
	
	private ColumnHeader[] getColumnNames(String json) {
		Gson gson = new GsonBuilder().create();
		Column[] col =  gson.fromJson(json, Column[].class);
		ColumnHeader[] out = new ColumnHeader[col.length];
		int i = 0;
		for(Column cl : col) {
			out[i] = new ColumnHeader(cl.name, cl.dataType);
			i++;
		}
		return out;
	}
	
	public ColumnHeader[] initialize(String descFileName,String rulesFileName,String anonFileName) {

		String djson = fileToString(descFileName);
		String rjson =  fileToString(rulesFileName);
		String ajson = fileToString(anonFileName);
		
		ColumnHeader[] descCol = this.getColumnNames(djson);

		this.addRulesFromJSON(descCol, rjson, rImplemented);
		
		this.addConstraints(descCol, ajson, aImplemented);
		
		return descCol;
	}
	
	public static void main(String[] args) {
		String json = "[ \r\n" + 
				"   { \r\n" + 
				"      \"name\":\"NOM\",\r\n" + 
				"      \"dataType\":\"STRING\"\r\n" + 
				"   },\r\n" + 
				"   { \r\n" + 
				"      \"name\":\"AGE\",\r\n" + 
				"      \"dataType\":\"INT\"\r\n" + 
				"   },\r\n" + 
				"   { \r\n" + 
				"      \"name\":\"DATE_DE_NAISSANCE\",\r\n" + 
				"      \"dataType\":\"STRING\"\r\n" + 
				"   },\r\n" + 
				"   { \r\n" + 
				"      \"name\":\"EMAIL_PRO\",\r\n" + 
				"      \"dataType\":\"STRING\"\r\n" + 
				"   },\r\n" + 
				"   { \r\n" + 
				"      \"name\":\"EMAIL_PERSO\",\r\n" + 
				"      \"dataType\":\"STRING\"\r\n" + 
				"   }\r\n" + 
				"]";
		//System.out.println(new JsonParser().getColumnNames(json).toString());
		
		
	}
	
}
