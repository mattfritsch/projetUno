package expert;

import carte.Carte;
import carte.CartePasser;
import exception.ExpertException;
import joueur.Joueur;
import partie.Partie;

public class ExpertCartePasser extends Expert{

	public ExpertCartePasser(Expert suivant) {
		super(suivant);
	}

	@Override
	public boolean examiner(Carte carte, Joueur joueur) throws Exception {
		CartePasser c = (CartePasser) carte;
		
		if(joueur == Partie.getJoueurCourant()) {
			if((c.getCouleur() == Partie.getCouleurCourante())) {
				return true;
			}
		}
		else {
			throw new ExpertException("examiner : ExpertCartePasser");
		}
		return false;
	}

	@Override
	public boolean saitExaminer(Carte carte) {
		if(carte.getClass().getSimpleName().equals("CartePasser"))
			return true;
		return false;
	}

}
