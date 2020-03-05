package projectCsv;

import java.util.function.Predicate;

public class Imperatifs {
	
	/**
	 * Represents a rule that applies to both a column and a string
	 */
	
	String alias;
	Predicate<String> lambdaFun;
	
	public Imperatifs(String alias, Predicate<String> lambdaFun) {
		super();
		this.alias = alias;
		this.lambdaFun = lambdaFun;
	}
	
	public boolean match(String name) {
		/**
		 * Ensures the given string matches the name of the rule
		 */
		return name.equals(this.alias);
	}
	
	public boolean use(String s) {
		/**
		 * Applies the implementation of the rule to the given argument
		 */
		return this.lambdaFun.test(s);
	}

	@Override
	public String toString() {
		return "Imperatifs [alias=" + alias + "]";
	}
	
	
	
}
