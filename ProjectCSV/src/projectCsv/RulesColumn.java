package projectCsv;

import java.util.ArrayList;

public class RulesColumn {
	int colonne;
	Imperatifs[] rules;
	int nbrules;
	
	
	
	public RulesColumn(int colonne, Imperatifs[] rules) {
		super();
		this.colonne = colonne;
		this.rules = rules;
		this.nbrules = rules.length;
	}



	public boolean match(String s) {
		int i = 0;
		while(i < this.nbrules && this.rules[i].use(s))
			i++;
		return i == this.nbrules;
	}
}
