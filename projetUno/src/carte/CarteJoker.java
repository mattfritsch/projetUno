package carte;

import partie.Partie;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

public class CarteJoker extends CarteEvenement {
	
	/* Constructeur par defaut */
	
	/**
	 * Creation d'une CarteJoker
	 */
	public CarteJoker() {
		super();
	}
	
	/* Methode metier */
	
	/**
	 * Methode qui change la couleur courante d'une partie
	 * @param laPartie Partie
	 */
	public void changerCouleurCourante(Partie laPartie) {
		Couleur nextCouleur = Couleur.VERT;//laPartie.demanderCouleur();
		// couleur forcement valide
		laPartie.setCouleurCourante(nextCouleur);
	}
	
	public void appliquerEffet(Partie laPartie) {
		changerCouleurCourante(laPartie);
	}

	/* Affichage */
	@Override
	public String toString() {
		return "CarteJoker";
	}
}
