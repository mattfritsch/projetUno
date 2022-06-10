package expert;

import carte.Carte;
import carte.CarteChiffre;
import exception.ExpertException;
import joueur.Joueur;
import partie.Partie;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

public class ExpertCarteSimple extends Expert{
	/**
	 * Creation d'un ExpertCarteSimple
	 * @param suivant Expert
	 */
	public ExpertCarteSimple(Expert suivant) {
		super(suivant);
	}
	
	@Override
	public boolean examiner(Carte carte, Joueur joueur) throws ExpertException {
		CarteChiffre c = (CarteChiffre) carte;
		
		if (joueur == Partie.getJoueurCourant()) {
			if((c.getValeur() == Partie.getValeurCourante()) || (c.getCouleur() == Partie.getCouleurCourante())) {
				Partie.setCarteCourante(c);
				Partie.calculerJoueurSuivant(0);
				return Partie.getTas().addCarte(carte);
			}
			else {
				throw new ExpertException("Coup illegal");
			}
		}
		else {
			throw new ExpertException("examiner : ExpertCarteSimple");
		}
	}
	
	@Override
	public boolean saitExaminer(Carte carte){
		if(carte.getClass().getSimpleName().equals("CarteChiffre"))
			return true;
		return false;
	} 
}
