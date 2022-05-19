package parser;

import carte.Carte.Couleur;
import carte.CarteChiffre;
import pioche.Pioche;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */


public class ParserCarteSimple extends Parser{
	/**
	 * Creation d'un ParserCarteSimple
	 * @param suivant Parser
	 */
	public ParserCarteSimple(Parser suivant) {
		super(suivant);
	}
	
	@Override
	public void parser(String ligne, Pioche pioche) throws Exception{
		String tab[] = ligne.split(";");
		int numCarte = Integer.parseInt(tab[2]);
		Couleur couleur = partie.Partie.convertStringToCouleur(tab[1]);
		CarteChiffre carte = new CarteChiffre(numCarte, couleur);
		
		pioche.addCarte(carte);		
	}
	
	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CarteSimple");
	}
}
