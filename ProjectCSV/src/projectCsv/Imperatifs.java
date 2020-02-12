package projectCsv;

import java.util.function.Predicate;

public class Imperatifs {
	String alias;
	Predicate<String> lambdaFun;
	public Imperatifs(String alias, Predicate<String> lambdaFun) {
		super();
		this.alias = alias;
		this.lambdaFun = lambdaFun;
	}
	
	public boolean use(String s) {
		return this.lambdaFun.test(s);
	}
	
	
}
