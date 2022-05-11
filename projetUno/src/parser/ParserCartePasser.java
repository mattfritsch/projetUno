package parser;

import carte.Carte.Couleur;
import carte.CartePasser;

public class ParserCartePasser extends Parser {
	public ParserCartePasser(Parser suivant) {
		super(suivant);
	}
	
	@Override
	public void parser(String ligne) throws Exception{
		String tab[] = ligne.split(";");
		
		Couleur couleur = partie.Partie.convertStringToCouleur(tab[1]);
		CartePasser cartePasser = new CartePasser(couleur);
		
		System.out.println(cartePasser);
		
	}
	
	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CartePasser");
	}
}
