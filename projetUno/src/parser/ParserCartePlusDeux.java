package parser;

import carte.CartePlusDeux;
import partie.Partie;
import pioche.Pioche;
import carte.Carte.Couleur;

public class ParserCartePlusDeux extends Parser{
	public ParserCartePlusDeux(Parser suivant) {
		super(suivant);
	}
	
	@Override
	public void parser(String ligne) throws Exception{
		String tab[] = ligne.split(";");
		Couleur couleur = partie.Partie.convertStringToCouleur(tab[1]);
		CartePlusDeux cartePlusDeux = new CartePlusDeux(couleur);
		Pioche.pioche.add(cartePlusDeux);
		
		System.out.println(Pioche.pioche);
	}
	
	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CartePlus2");
	}

}
