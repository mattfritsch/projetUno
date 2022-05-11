package expert;

import carte.Carte;
import joueur.Joueur;
import partie.Partie;

public class ExpertCarteJoker extends Expert{

	public ExpertCarteJoker(Expert suivant) {
		super(suivant);
	}

	@Override
	public boolean examiner(Carte carte, Joueur joueur) throws Exception {
		if(joueur == Partie.getJoueurCourant()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean saitExaminer(Carte carte) {
		if(carte.getClass().getSimpleName().equals("CarteJoker"))
			return true;
		return false;
	}

}
