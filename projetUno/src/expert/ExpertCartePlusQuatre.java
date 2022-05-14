package expert;

import carte.Carte;
import carte.CartePlusQuatre;
import exception.ExpertException;
import joueur.Joueur;
import partie.Partie;

public class ExpertCartePlusQuatre extends Expert{

	public ExpertCartePlusQuatre(Expert suivant) {
		super(suivant);
	}

	@Override
	public boolean examiner(Partie partie, Carte carte, Joueur joueur) throws Exception {
		if(joueur == partie.getJoueurCourant()) {
			return partie.getTas().addCarte(partie, carte);
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
