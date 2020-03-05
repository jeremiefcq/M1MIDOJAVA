package projectCsv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.function.Predicate;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/**
		 * L'application nécessite 5 arguments obligatoire et un 6ème optionnel
		 * In.CSV:    Fichier CSV à anonymiser
		 * Desc.JSON: FIchier JSON de la forme {NomDeColonne, TypeDeData}
		 * Rules.JSON: FIchier JSON de la forme {NomDeColonne, [Liste des règles s'appliquant aux champs]}
		 * Anon.Json: Fichier JSON de la forme {NomDeColonne, RegleDAnonymization}
		 * [Opt] --Anon: Si l'on veut écrire le fichier anonymisé resultant
		 */
		int l = args.length;
		
		Predicate<String> rAdress = (String s) -> {return s.matches("[a-zA-Z0-9]+([.][a-zA-Z0-9]+)??[@][a-zA-Z0-9]+[.][a-zA-Z0-9]+"); };
		Predicate<String> rAdressDauph = (String s) -> {return s.matches("[a-zA-Z0-9]+([.][a-zA-Z0-9]+)??[@]((dauphine.eu)|(dauphine.psl.eu)|(lamsade.dauphine.fr))"); };
		Predicate<String> rName = (String s) -> {return s.matches("[a-zA-Z]+"); };
		Predicate<String> rLong = (String s) -> {return s.length() > 5; };
		Predicate<String> rAge = (String s) -> {return s.matches("[1][01][0-9]|[0-9][0-9]?|120"); };
		
		Imperatifs iAdress = new Imperatifs("BE_AN_EMAIL",rAdress);
		Imperatifs iAdressDauph = new Imperatifs("BE_AN_DAUPHINE_EMAIL", rAdressDauph );
		Imperatifs iName = new Imperatifs("IS_NAME",rName);
		Imperatifs iLong = new Imperatifs("IS_LONG",rLong);
		Imperatifs iAge = new Imperatifs("BE_AN_AGE", rAge);
		
		Imperatifs[] implemented = {iAdress, iAdressDauph, iName, iLong, iAge};
		
		Random rn = new Random(System.currentTimeMillis());
		Anonymisation rdLetter = new Anonymisation("RANDOM_LETTER",
				(s)-> {
					char[] oldletters = s.toCharArray();
					int i = 0, lmax = oldletters.length;
					char newC;
					while(i < lmax) {
						if(s.charAt(i) == oldletters[i]) {
							//Test pour assurer qu'une lettre déjà remplacée ne le soit pas une seconde fois
							newC = (char) (oldletters[i]+rn.nextInt()%26);
							if(newC == '|')
								newC += 1;
							s = s.replace(oldletters[i], newC);
						}
						i++;
					}
					return s;});
		Anonymisation rdLetterAdress = new Anonymisation("RANDOM_LETTER_FOR_LOCAL_PART",
				(s)->{
					
					String[] ish = s.split("@");
					int lmax = ish.length, i = 1;
					StringBuilder out = new StringBuilder(rdLetter.hide.apply(ish[0]));
					while(i < lmax) {
						out.append("@");
						out.append(ish[i]);
						i++;
					}
					
					return out.toString();
				}
				
				);
		
		Anonymisation[] anonImp= {rdLetter, rdLetterAdress};
		
		if(l >= 5) {
			String mainFile = args[0];
			String descFile = args[1];
			String ruleFile = args[2];
			String anonFile = args[3];
			String outFile = args[4];
			JsonParser prsjs = new JsonParser(implemented, anonImp);
			
			ColumnHeader[] truc = prsjs.initialize(descFile, ruleFile, anonFile);
			for(int i = 0; i < truc.length; i++)
				System.out.println(truc[i].toString());
			
			ParserCSV test = new ParserCSV(truc, mainFile);
			if(l ==6 && args[5].equals("--anon") ) {
				try {
					 File out = new File(outFile);
				      	out.createNewFile();
				    } catch (IOException e) {
				      System.out.println("An error occurred.");
				      e.printStackTrace();
				    }
					
					BufferedWriter outCSV = new BufferedWriter( new FileWriter(outFile));
					test.parse(true, outCSV);
					outCSV.close();
			}
			else	
				test.parse(false,null);
		}
		
	}

}
