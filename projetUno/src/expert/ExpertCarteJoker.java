package expert;

import carte.Carte;
import joueur.Joueur;
import partie.Partie;

public class ExpertCarteJoker extends Expert{

	public ExpertCarteJoker(Expert suivant) {
		super(suivant);
	}

	@Override
	public boolean examiner(Partie partie, Carte carte, Joueur joueur) throws Exception {
		if(joueur == partie.getJoueurCourant()) {
			return partie.getTas().addCarte(partie, carte);
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
