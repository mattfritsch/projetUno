package parser;

import carte.CartePlus4;

public class ParserCartePlus4 extends Parser{
	Parser suivant;
	public ParserCartePlus4(Parser suivant) {
		super(suivant);
	}
	
	@Override
	public void parser(String ligne) throws Exception{
		String tab[] = ligne.split(";");
		CartePlus4 cartePlusQuatre = new CartePlus4();
		System.out.println(cartePlusQuatre);
		
	}
	
	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CartePlus4");
	}

}
