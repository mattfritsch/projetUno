package expert;

import carte.Carte;
import carte.CarteJoker;
import exception.ExpertException;
import joueur.Joueur;
import partie.Partie;

public class ExpertCarteJoker extends Expert{

	public ExpertCarteJoker(Expert suivant) {
		super(suivant);
	}

	@Override
	public boolean examiner(Partie partie, Carte carte, Joueur joueur) throws ExpertException {
		CarteJoker c = (CarteJoker) carte;
		
		if(joueur == partie.getJoueurCourant()) {
			partie.setValeurCourante(-1);
			c.appliquerEffet(partie);
			return partie.getTas().addCarte(carte);
		}
		else {
			throw new ExpertException("examiner : ExpertCarteJoker");
		}
	}

	@Override
	public boolean saitExaminer(Carte carte) {
		if(carte.getClass().getSimpleName().equals("CarteJoker"))
			return true;
		return false;
	}

}
