package projectCsv;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Map;

public abstract class ParserInterface {
	String fileFormat;
	int size;
	int nbCol;
	ColumnHeader[] attributes;
	String FilePath;
	
	//Anonymisation method;
	
	
	public ParserInterface(	ColumnHeader[] attrib) {
		super();
		this.nbCol = attrib.length;
		this.attributes = attrib;
	}
	
	public ParserInterface(	ColumnHeader[] attrib, String path) {
		super();
		this.nbCol = attrib.length;
		this.attributes = attrib;
		this.FilePath = path;
	}
	

	public abstract int parse(boolean write, BufferedWriter fw) throws FileNotFoundException, IOException;
	
	protected abstract String verifyLine(String s);

}
