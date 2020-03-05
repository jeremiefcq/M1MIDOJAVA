package projectCsv;

import java.util.function.Function;

public class Anonymisation {
	
	/**
	 * Represents a hiding rule used for anonymizing data
	 */
	
	String alias;
	Function<String,String> hide;
	
	public Anonymisation(String alias, Function<String, String> hide) {
		super();
		this.alias = alias;
		this.hide = hide;
	}
	
	
}
