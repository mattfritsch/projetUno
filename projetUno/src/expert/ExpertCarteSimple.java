package expert;

import carte.Carte;
import carte.CarteChiffre;
import exception.ExpertException;
import joueur.Joueur;
import partie.Partie;

public class ExpertCarteSimple extends Expert{

	public ExpertCarteSimple(Expert suivant) {
		super(suivant);
	}
	
	@Override
	public boolean examiner(Partie partie, Carte carte, Joueur joueur) throws Exception{
		CarteChiffre c = (CarteChiffre) carte;
		
		if (joueur == partie.getJoueurCourant()) {
			if((c.getValeur() == partie.getValeurCourante()) || (c.getCouleur() == partie.getCouleurCourante())) {
				return true;
			}
		}
		else {
			throw new ExpertException("examiner : ExpertCarteSimple");
		}
		return false;
	}
	
	@Override
	public boolean saitExaminer(Carte carte){
		if(carte.getClass().getSimpleName().equals("CarteChiffre"))
			return true;
		return false;
	} 
}
