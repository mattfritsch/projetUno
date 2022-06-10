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
		this.valeur = -1;
		this.couleur = null;
	}
	
	/* Methode metier */
	public void appliquerEffet() {
		changerCouleurCourante();
		Partie.calculerJoueurSuivant(0);
		Partie.getJoueurSuivant().punir(4);
		Partie.calculerJoueurSuivant(1);
		
	}

	/* Affichage */
	@Override
	public String toString() {
		return "CartePlus4";
	}
}
