package expert;

import carte.Carte;
import carte.CartePasser;
import exception.ExpertException;
import joueur.Joueur;
import partie.Partie;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

public class ExpertCartePasser extends Expert{
	/**
	 * Creation d'un ExpertCartePasser
	 * @param suivant Expert
	 */
	public ExpertCartePasser(Expert suivant) {
		super(suivant);
	}

	@Override
	public boolean examiner(Carte carte, Joueur joueur) throws ExpertException {
		CartePasser c = (CartePasser) carte;
		
		if (joueur == Partie.getJoueurCourant()) {
			if((c.getCouleur() == Partie.getCouleurCourante()) || Partie.getTas().getTop().getClass() == c.getClass()) {
				Partie.setCarteCourante(c);
				c.appliquerEffet();
				return Partie.getTas().addCarte(carte);
			}
			else {
				throw new ExpertException("Coup illegal");
			}
		}
		else {
			throw new ExpertException("examiner : ExpertCartePasser");
		}
	}

	@Override
	public boolean saitExaminer(Carte carte) {
		if(carte.getClass().getSimpleName().equals("CartePasser"))
			return true;
		return false;
	}

}
