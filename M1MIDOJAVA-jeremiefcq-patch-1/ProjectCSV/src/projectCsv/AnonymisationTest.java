package projectCsv;

import java.util.Random;

public class AnonymisationTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rn = new Random(System.currentTimeMillis());
		Anonymisation rdLetter = new Anonymisation("RANDOM_LETTER",
				(s)-> {
					char[] oldletters = s.toCharArray();
					int i = 0, l = oldletters.length;
					while(i < l) {
						if(s.charAt(i) == oldletters[i]) {
							//Test pour assurer qu'une lettre déjà remplacée ne le soit pas une seconde fois
							s = s.replace(oldletters[i], (char) (oldletters[i]+rn.nextInt()%26));
						}
						i++;
					}
					return s;});
		Anonymisation rdLetterAdress = new Anonymisation("RANDOM_LETTER_FOR_LOCAL_PART",
				(s)->{
					
					String[] ish = s.split("@");
					int l = ish.length, i = 1;
					StringBuilder out = new StringBuilder(rdLetter.hide.apply(ish[0]));
					while(i < l) {
						out.append("@");
						out.append(ish[i]);
						i++;
					}
					
					return out.toString();
				}
				
				);
		for(int j = 0; j < 10; j++)
			System.out.println(rdLetter.hide.apply("Jeremie"));
			System.out.println(rdLetterAdress.hide.apply("Jeremie@gmail.com"));
	}

}
