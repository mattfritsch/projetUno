package expert;

import carte.Carte;
import carte.CarteChangement;
import exception.ExpertException;
import joueur.Joueur;
import partie.Partie;

public class ExpertCarteChangement extends Expert{

	public ExpertCarteChangement(Expert suivant) {
		super(suivant);
	}

	@Override
	public boolean examiner(Carte carte, Joueur joueur) throws Exception {
		CarteChangement c = (CarteChangement) carte;
		
		if(joueur == Partie.getJoueurCourant()) {
			if(c.getCouleur() == Partie.getCouleurCourante()) {
				return true;
			}
		}
		else {
			throw new ExpertException("examiner : ExpertCarteChangement");
		}
		return false;
	}

	@Override
	public boolean saitExaminer(Carte carte) {
		if(carte.getClass().getSimpleName().equals("CarteChangement"))
			return true;
		return false;
	}

}
