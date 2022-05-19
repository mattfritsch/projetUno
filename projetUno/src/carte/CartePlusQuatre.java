package carte;

import partie.Partie;
import joueur.Joueur;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

public class CartePlusQuatre extends CarteJoker {
	
	/* Constructeur par defaut */
	
	/**
	 * Creation d'une CartePlusQuatre
	 */
	public CartePlusQuatre() {
		super();
	}
	
	/* Methode metier */
	public void appliquerEffet(Partie laPartie) {
		changerCouleurCourante(laPartie);
		laPartie.calculerJoueurSuivant(0);
		laPartie.getJoueurSuivant().punir(laPartie, 4);
		laPartie.calculerJoueurSuivant(1);
		
	}

	/* Affichage */
	@Override
	public String toString() {
		return "CartePlus4";
	}
}
