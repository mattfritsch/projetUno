package parser;

import carte.CartePlus4;

public class ParserPlusQuatre extends Parser{
	Parser suivant;
	public ParserPlusQuatre(Parser suivant) {
		super(suivant);
	}
	
	@Override
	public void parser(String ligne) throws Exception{
		String tab[] = ligne.split(";");
		CartePlus4 cartePlusQuatre = new CartePlus4();
		System.out.println("Carte changement :[ " +cartePlusQuatre + " ]\n");
		
	}
	
	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CartePlus4");
	}

}
