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
	public boolean examiner(Carte carte, Joueur joueur) throws ExpertException{
		CartePlusDeux c = (CartePlusDeux) carte;
		
		if(joueur == Partie.getJoueurCourant()) {
			if(c.getCouleur() == Partie.getCouleurCourante() || Partie.getTas().getTop().getClass() == c.getClass()) {
				Partie.setCarteCourante(c);
				c.appliquerEffet();
				Partie.calculerJoueurSuivant(0);
				return Partie.getTas().addCarte(carte);
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
