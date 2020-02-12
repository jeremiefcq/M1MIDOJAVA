package projectCsv;

import java.util.function.Predicate;

public class ImperatifsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Predicate<String> r1 = (String s) -> {return s.matches("[a-zA-Z0-9]+([.][a-zA-Z0-9]+)??[@][a-zA-Z0-9]+[.][a-zA-Z0-9]+"); };
		Predicate<String> r2 = (String s) -> {return s.matches("[a-zA-Z]+"); };
		
		Imperatifs rule1 =  new Imperatifs("IS_ADDRESS",r1);
		Imperatifs rule2 =  new Imperatifs("IS_NAME",r2);
		
		System.out.println(rule1.use("jeremie.facquet@dauphine.eu"));
		System.out.println(rule2.use("jeremie.facquet@dauphine.eu"));
		System.out.println(rule1.use("jeremiefacquet@dauphine.eu"));
		System.out.println(rule1.use("jeremie.facquet.pasbon@dauphine.eu"));
	}

}
