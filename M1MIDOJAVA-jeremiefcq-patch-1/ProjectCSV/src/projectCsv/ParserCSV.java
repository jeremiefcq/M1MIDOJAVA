package projectCsv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Map;

public class ParserCSV extends ParserInterface {
	
	
	
	
	public ParserCSV(ColumnHeader[] attrib, String path) {
		super(attrib, path);
		// TODO Auto-generated constructor stub
	}

	private void getColumnNamesFromCSV(BufferedReader br) {
		/**
		 * Stores the column names given in the first data line of the CSV File
		 * 
		 */
		
		String st;
		String[] values = null;
		try {
			st = br.readLine();
			values = st.split("\\|");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int parse(boolean write, BufferedWriter fw) throws IOException {
		// TODO Auto-generated method stub
		/**
		 * To avoid code duplication, this method uses the boolean argument "write" to determine
		 * either it should write down the anonymized line in the FileWriter argument.
		 * Either way, the line is first parsed and tested to prevent the parser from writing
		 * a corrupted output 
		 */
		System.out.println(this.FilePath);
		File fl = new File(this.FilePath);
		BufferedReader br = new BufferedReader(new FileReader(fl)); 

		this.getColumnNamesFromCSV(br); 
		String st; 
		String anonSt;
		StringBuffer error = new StringBuffer();
		boolean fatal = false;
		int i = 0;
		 while ((st = br.readLine()) != null) { 
			  i++;
			  if((anonSt = this.verifyLine(st) )!=null) {
				  if( write) {
					  fw.append(anonSt);
					  fw.append("\r\n");
				  }
					  			
			  }
			  else  {
				  fatal = true;
				  error.append(String.format("Erreur detectée ligne %d\n", i)); //Stores the faulting line
			  } 
		  } 
		 
		 if(fatal && !write)
			  System.out.println(error.toString());
		  else
			  System.out.println("Lecture avec succès");
			
		return 1;
	}

	@Override
	protected String verifyLine(String line) {
		// TODO Auto-generated method stub
		/**
		 * Splits the given line into the tokens
		 * Verifies their compatibility with rules of their respective Column
		 * Writes the tokens once anonymized (if an anonymization rule exists for the specific column)
		 * If a token doesn't meet the requirements of his column, the matching is stopped and null is returned
		 */
		StringBuilder out = new StringBuilder();
		String[] values = line.split("\\|");
		boolean first = true;
		if(values.length == this.nbCol) {
			int i = 0;
			while(i < this.nbCol && this.attributes[i].match(values[i])) {
				if(this.attributes[i].hidingrule != null) {
					if(first)
						first = false;
					else
						out.append("\\|");
					out.append(this.attributes[i].hidingrule.hide.apply(values[i]));
				}
				
				i++;
			}
				
			if(i == this.nbCol)
				return out.toString();
		}
		return null;
	}

}
