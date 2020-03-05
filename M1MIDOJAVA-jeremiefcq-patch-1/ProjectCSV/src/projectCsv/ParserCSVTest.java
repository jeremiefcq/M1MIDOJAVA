package projectCsv;

import java.io.IOException;
import java.util.function.Predicate;

public class ParserCSVTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Predicate<String> r1 = (String s) -> {return s.matches("[a-zA-Z0-9]+([.][a-zA-Z0-9]+)??[@][a-zA-Z0-9]+[.][a-zA-Z0-9]+"); };
		Predicate<String> r2 = (String s) -> {return s.matches("[a-zA-Z]+"); };
		Predicate<String> r3 = (String s) -> {return s.length() > 5; };
		Imperatifs[] rule1 =  {new Imperatifs("IS_ADDRESS",r1)};
		Imperatifs[] rule2 =  {new Imperatifs("IS_NAME",r2),new Imperatifs("IS_LONG",r3)};

		
	//	ColumnHeader[] r = {new ColumnHeader(0, rule1),new ColumnHeader(1, rule2) };
		
		//ParserCSV prs = new ParserCSV(r);
		
		//System.out.println(prs.verifyLine("abc@dauphine.fr|court|err"));
		//System.out.println(prs.verifyLine("abc@dauphine.fr|noncourt"));
		//System.out.println(prs.verifyLine("faux.fr|noncourt"));
		
		//ParserCSV prs1 = new ParserCSV(r,"/home/facquet/Documents/Java/testParse.txt" );
		//prs1.parse();
		
		//ParserCSV prs2 = new ParserCSV(r,"testParse.txt" );
		//prs2.parse();

		
	}

}
