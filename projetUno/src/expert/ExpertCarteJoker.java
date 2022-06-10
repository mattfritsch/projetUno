package expert;

import carte.Carte;
import carte.CarteJoker;
import exception.ExpertException;
import joueur.Joueur;
import partie.Partie;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

public class ExpertCarteJoker extends Expert{
	/**
	 * Creation d'un ExpertCarteJoker
	 * @param suivant Expert
	 */
	public ExpertCarteJoker(Expert suivant) {
		super(suivant);
	}

	@Override
	public boolean examiner(Partie partie, Carte carte, Joueur joueur) throws ExpertException {
		CarteJoker c = (CarteJoker) carte;
		
		if(joueur == Partie.getJoueurCourant()) {
			partie.setCarteCourante(c);
			c.appliquerEffet(partie);
			return partie.getTas().addCarte(carte);
		}
		else {
			throw new ExpertException("examiner : ExpertCarteJoker");
		}
	}

	@Override
	public boolean saitExaminer(Carte carte) {
		if(carte.getClass().getSimpleName().equals("CarteJoker"))
			return true;
		return false;
	}

}
