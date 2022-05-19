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
	public boolean examiner(Partie partie, Carte carte, Joueur joueur) throws ExpertException {
		CarteChiffre c = (CarteChiffre) carte;
		
		if (joueur == partie.getJoueurCourant()) {
			if((c.getValeur() == partie.getValeurCourante()) || (c.getCouleur() == partie.getCouleurCourante())) {
				partie.setCouleurCourante(c.getCouleur());
				partie.setValeurCourante(c.getValeur());
				partie.calculerJoueurSuivant(0);
				return partie.getTas().addCarte(carte);
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
