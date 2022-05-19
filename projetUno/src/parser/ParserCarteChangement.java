package parser;

import carte.Carte.Couleur;
import carte.CarteChangement;
import pioche.Pioche;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

public class ParserCarteChangement extends Parser{
	
	/**
	 * Creation d'un parser ParserCarteChangement
	 * @param suivant Parser
	 */
	public ParserCarteChangement(Parser suivant) {
		super(suivant);
	}
	
	@Override
	public void parser(String ligne, Pioche pioche) throws Exception{
		String tab[] = ligne.split(";");
		Couleur couleur = partie.Partie.convertStringToCouleur(tab[1]);
		CarteChangement carteChangement = new CarteChangement(couleur);
		pioche.addCarte(carteChangement);
	}
	
	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CarteChangement");
	}
}
