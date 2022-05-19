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
	public boolean examiner(Partie partie, Carte carte, Joueur joueur) throws ExpertException {
		CarteChangement c = (CarteChangement) carte;
		
		if(joueur == partie.getJoueurCourant()) {
			if(c.getCouleur() == partie.getCouleurCourante() || partie.getTas().getTop().getClass() == c.getClass()) {
				partie.setCouleurCourante(c.getCouleur());
				partie.setValeurCourante(-1);
				c.appliquerEffet(partie);
				partie.calculerJoueurSuivant(0);
				return partie.getTas().addCarte(carte);
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
