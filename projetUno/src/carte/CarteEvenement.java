package carte;

import partie.Partie;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

public abstract class CarteEvenement extends Carte {
	
	/**
	 * Creation d'une CarteEvenement
	 */
	public CarteEvenement() {
		super();
	}
	
	/**
	 * Methode qui permet d'appliquer un effet sur une partie
	 * @param laPartie Partie
	 */
	public abstract void appliquerEffet(Partie laPartie);
	
}
