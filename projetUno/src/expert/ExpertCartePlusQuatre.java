package expert;

import carte.Carte;
import carte.CartePlusQuatre;
import exception.ExpertException;
import joueur.Joueur;
import partie.Partie;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

public class ExpertCartePlusQuatre extends Expert{
	/**
	 * Creation d'un ExpertCartePlusQuatre
	 * @param suivant Expert
	 */
	public ExpertCartePlusQuatre(Expert suivant) {
		super(suivant);
	}

	@Override
	public boolean examiner(Partie partie, Carte carte, Joueur joueur) throws ExpertException {
		CartePlusQuatre c = (CartePlusQuatre) carte;
		
		if(joueur == partie.getJoueurCourant()) {
			partie.setValeurCourante(-1);
			c.appliquerEffet(partie);
			return partie.getTas().addCarte(carte);
		}
		else {
			throw new ExpertException("examiner : ExpertCartePlusQuatre");
		}
	}

	@Override
	public boolean saitExaminer(Carte carte) {
		if(carte.getClass().getSimpleName().equals("CartePlusQuatre"))
			return true;
		return false;
	}

}
