package expert;

import carte.Carte;
import carte.CartePlusDeux;
import exception.ExpertException;
import joueur.Joueur;
import partie.Partie;

public class ExpertCartePlusDeux extends Expert{

	public ExpertCartePlusDeux(Expert suivant) {
		super(suivant);
	}
	
	@Override
	public boolean examiner(Carte carte, Joueur joueur) throws Exception{
		CartePlusDeux c = (CartePlusDeux) carte;
		
		if(joueur == Partie.getJoueurCourant()) {
			if(c.getCouleur() == Partie.getCouleurCourante()) {
				return true;
			}
		}
		else {
			throw new ExpertException("examiner : ExpertCartePlusDeux");
		}
		return false;
	}

	@Override
	public boolean saitExaminer(Carte carte) {
		if(carte.getClass().getSimpleName().equals("CartePlusDeux")) {
			return true;
		}
		return false;
	}
}