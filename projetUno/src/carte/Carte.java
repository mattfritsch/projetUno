package carte;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

public abstract class Carte {
	/* Champs et constantes */
	
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
	
}
