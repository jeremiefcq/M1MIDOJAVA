package projectCsv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ParserCSV extends ParserInterface {
	
	
	public ParserCSV(RulesColumn[] r) {
		// TODO Auto-generated constructor stub
		super(r);
	}
	public ParserCSV(RulesColumn[] r,String path) {
		// TODO Auto-generated constructor stub
		super(r,path);
	}

	@Override
	public int parse() throws IOException {
		// TODO Auto-generated method stub
		System.out.println(this.FilePath);
		File fl = new File(this.FilePath);
		BufferedReader br = new BufferedReader(new FileReader(fl)); 
		  
		String st; 
		  while ((st = br.readLine()) != null) { 
			  if(this.verifyLine(st) == 0)
					System.out.println(st); 
		  } 
			
		return 0;
	}

	@Override
	public int writeLine() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int verifyLine(String line) {
		// TODO Auto-generated method stub
		String[] values = line.split("\\|");
		//for(int j = 0; j < values.length; j++)
			//System.out.println(values[j]);
		if(values.length == this.nbCol) {
			int i = 0;
			while(i < this.nbCol && this.rules[i].match(values[i]))
				i++;
			if(i == this.nbCol)
				return 0;
		}
		return 1;
	}

}
