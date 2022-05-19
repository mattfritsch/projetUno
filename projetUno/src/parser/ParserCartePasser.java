package parser;

import carte.Carte.Couleur;
import pioche.Pioche;
import carte.CartePasser;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */


public class ParserCartePasser extends Parser {
	/**
	 * Creation d'un parser ParserCartePasser
	 * @param suivant Parser
	 */
	public ParserCartePasser(Parser suivant) {
		super(suivant);
	}
	
	@Override
	public void parser(String ligne, Pioche pioche) throws Exception{
		String tab[] = ligne.split(";");
		
		Couleur couleur = partie.Partie.convertStringToCouleur(tab[1]);
		CartePasser cartePasser = new CartePasser(couleur);
		pioche.addCarte(cartePasser);
		
		//System.out.println(Pioche.pioche);
		
	}
	
	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CartePasser");
	}
}
