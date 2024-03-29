package expert;

import carte.Carte;
import carte.CartePlusDeux;
import exception.ExpertException;
import joueur.Joueur;
import partie.Partie;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

public class ExpertCartePlusDeux extends Expert{
	/**
	 * Creation d'un ExpertCartePlusDeux
	 * @param suivant Expert
	 */
	public ExpertCartePlusDeux(Expert suivant) {
		super(suivant);
	}
	
	@Override
	public boolean examiner(Partie partie, Carte carte, Joueur joueur) throws ExpertException{
		CartePlusDeux c = (CartePlusDeux) carte;
		
		if(joueur == Partie.getJoueurCourant()) {
			if(c.getCouleur() == partie.getCouleurCourante() || partie.getTas().getTop().getClass() == c.getClass()) {
				partie.setCarteCourante(c);
				c.appliquerEffet(partie);
				partie.calculerJoueurSuivant(0);
				return partie.getTas().addCarte(carte);
			}
			else {
				throw new ExpertException("Coup illegal");
			}
		}
		else {
			throw new ExpertException("examiner : ExpertCartePlusDeux");
		}
	}

	@Override
	public boolean saitExaminer(Carte carte) {
		if(carte.getClass().getSimpleName().equals("CartePlusDeux")) {
			return true;
		}
		return false;
	}
}
