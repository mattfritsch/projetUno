package carte;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

public abstract class Carte {
	/* Champs et constantes */
	public int valeur;
	public Couleur couleur;
	
	/**
	 * 
	 * Constante qui defini les couleurs possibles des cartes
	 *
	 */
	public enum Couleur {
		/**
		 * Couleur Vert
		 */
		VERT,
		/**
		 * Couleur Jaune
		 */
		JAUNE,
		/**
		 * Couleur Bleu
		 */
		BLEU,
		/**
		 * Couleur Rouge
		 */
		ROUGE
	}
	
	/**
	 * Creation d'une carte
	 */
	public Carte() {
		
	}
	
	/* Getters */
	
	/**
	 * Retourne la valeur de la carte
	 * @return int
	 */
	public int getValeur() {
		return valeur;
	}
	
	/**
	 * Retourne la couleur de la carte
	 * @return Couleur
	 */
	public Couleur getCouleur() {
		return couleur;
	}
	
}
