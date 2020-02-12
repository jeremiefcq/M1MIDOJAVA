package projectCsv;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class ParserInterface {
	String fileFormat;
	int size;
	int nbCol;
	RulesColumn[] rules;
	String FilePath;
	
	
	
	public ParserInterface(RulesColumn[] rules) {
		super();
		this.rules = rules;
		this.nbCol = rules.length;
	}
	
	public ParserInterface(RulesColumn[] rules,String path) {
		super();
		this.rules = rules;
		this.nbCol = rules.length;
		this.FilePath = path;
	}

	public abstract int parse() throws FileNotFoundException, IOException;
	
	protected abstract int writeLine();
	protected abstract int verifyLine(String s);
	//public int parseBuff();
}
