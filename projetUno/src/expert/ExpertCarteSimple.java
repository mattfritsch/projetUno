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
	public boolean examiner(Carte carte, Joueur joueur) throws Exception{
		CarteChiffre c = (CarteChiffre) carte;
		
		if (joueur == Partie.getJoueurCourant()) {
			if((c.getValeur() == Partie.getValeurCourante()) || (c.getCouleur() == Partie.getCouleurCourante())) {
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
