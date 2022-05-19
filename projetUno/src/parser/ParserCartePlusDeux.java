package parser;

import carte.CartePlusDeux;
import partie.Partie;
import pioche.Pioche;
import carte.Carte.Couleur;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */


public class ParserCartePlusDeux extends Parser{
	/**
	 * Creation d'un parser ParserCartePlusDeux
	 * @param suivant Parser
	 */
	public ParserCartePlusDeux(Parser suivant) {
		super(suivant);
	}
	
	@Override
	public void parser(String ligne, Pioche pioche) throws Exception{
		String tab[] = ligne.split(";");
		Couleur couleur = partie.Partie.convertStringToCouleur(tab[1]);
		CartePlusDeux cartePlusDeux = new CartePlusDeux(couleur);
		pioche.addCarte(cartePlusDeux);
		
		//System.out.println(Pioche.pioche);
	}
	
	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CartePlus2");
	}

}
