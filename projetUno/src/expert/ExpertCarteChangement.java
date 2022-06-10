package expert;

import carte.Carte;
import carte.CarteChangement;
import exception.ExpertException;
import joueur.Joueur;
import partie.Partie;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

public class ExpertCarteChangement extends Expert{
	/**
	 * Creation d'un ExpertCarteChangement 
	 * @param suivant Expert
	 */
	public ExpertCarteChangement(Expert suivant) {
		super(suivant);
	}

	@Override
	public boolean examiner(Carte carte, Joueur joueur) throws ExpertException {
		CarteChangement c = (CarteChangement) carte;
		
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
			throw new ExpertException("examiner : ExpertCarteChangement");
		}
	}

	@Override
	public boolean saitExaminer(Carte carte) {
		if(carte.getClass().getSimpleName().equals("CarteChangement"))
			return true;
		return false;
	}

}
