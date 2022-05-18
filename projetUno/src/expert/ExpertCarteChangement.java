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
	public boolean examiner(Partie partie, Carte carte, Joueur joueur) throws Exception {
		CarteChangement c = (CarteChangement) carte;
		
		if(joueur == partie.getJoueurCourant()) {
			if(c.getCouleur() == partie.getCouleurCourante()) {
				partie.setCouleurCourante(c.getCouleur());
				partie.setValeurCourante(-1);
				return partie.getTas().addCarte(carte);
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
