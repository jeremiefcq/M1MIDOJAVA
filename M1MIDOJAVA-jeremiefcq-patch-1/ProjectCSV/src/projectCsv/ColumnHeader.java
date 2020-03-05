package projectCsv;

import java.util.Arrays;

public class ColumnHeader/*<T>*/ {
	
	/**
	 * Represents all the conditions applying to a specified column
	 */

	Imperatifs[] rules; 
	Anonymisation hidingrule;
	String name;
	String trypeStr;

	int nbrules;
	
	public ColumnHeader(String name, String type) {
		super();
		this.name = name;
		this.trypeStr = type;
		this.nbrules = 0;
		this.rules = null;
	}


	public boolean match(String s) {
		/**
		 * Makes sure that the content given matches all the predicates required
		 */
		int i = 0;
		while(i < this.nbrules && this.rules[i].use(s))
			i++;
		return i == this.nbrules;
	}


	@Override
	public String toString() {
		return "ColumnHeader [rules=" + Arrays.toString(rules) + ", hidingrule=" + hidingrule + ", name=" + name
				+ ", trypeStr=" + trypeStr + ", nbrules=" + nbrules + "]";
	}
	
	
}
