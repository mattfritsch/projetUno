package parser;

import carte.CartePlus2;
import carte.Carte.Couleur;

public class ParserPlusDeux extends Parser{
	public ParserPlusDeux(Parser suivant) {
		super(suivant);
	}
	
	@Override
	public void parser(String ligne) throws Exception{
		String tab[] = ligne.split(";");
		Couleur couleur = partie.convertStringToCouleur(tab[1]);
		CartePlus2 cartePlusDeux = new CartePlus2(couleur);
		
		System.out.println(cartePlusDeux);
	}
	
	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CartePlus2");
	}

}
