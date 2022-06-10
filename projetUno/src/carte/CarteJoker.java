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
		this.valeur = -1;
		this.couleur = null;
	}
	
	/* Methode metier */
	
	/**
	 * Methode qui change la couleur courante d'une partie
	 * @param laPartie Partie
	 */
	public void changerCouleurCourante() {
		/*Couleur nextCouleur = Couleur.VERT;//laPartie.demanderCouleur();
		// couleur forcement valide
		laPartie.setCouleurCourante(nextCouleur);*/
		this.couleur = Couleur.VERT;
	}
	
	public void appliquerEffet() {
		changerCouleurCourante();
	}

	/* Affichage */
	@Override
	public String toString() {
		return "CarteJoker";
	}
}
