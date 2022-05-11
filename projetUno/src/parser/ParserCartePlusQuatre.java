package parser;

import carte.CartePlusQuatre;

public class ParserCartePlusQuatre extends Parser{
	Parser suivant;
	public ParserCartePlusQuatre(Parser suivant) {
		super(suivant);
	}
	
	@Override
	public void parser(String ligne) throws Exception{
		String tab[] = ligne.split(";");
		CartePlusQuatre cartePlusQuatre = new CartePlusQuatre();
		System.out.println(cartePlusQuatre);
		
	}
	
	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CartePlus4");
	}

}
