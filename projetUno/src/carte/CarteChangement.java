package carte;

import java.util.Objects;
import exception.CarteException;
import partie.Partie;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

public class CarteChangement extends CarteEvenement {
	/* Champs */
	private Couleur couleur;
	
	/* Constructeur */
	
	/**
	 * Creation d'une CarteChangement
	 * @param couleur Couleur
	 * @throws CarteException CarteException
	 */
	public CarteChangement(Couleur couleur) throws CarteException {
		super();
		
		// Classe immuable donc pas de setter
		if (couleur == null)
			throw new CarteException("Une CarteChangement doit avoir une couleur valide");
		
		this.couleur = couleur;
	}
	
	/* Getters */
	
	/**
	 * Retourne la couleur de la carte
	 * @return Couleur
	 */
	public Couleur getCouleur() {
		return couleur;
	}
	
	/* Methode metier */
	
	public void appliquerEffet(Partie laPartie) {
		changementDeSens(laPartie);
	}
	
	/* Inverser le sens de la partie*/
	
	/**
	 * Methode qui inverse le sens d'une partie
	 * @param laPartie Partie
	 */
	public void changementDeSens(Partie laPartie) {
		if (laPartie.getSens() == 0)
			laPartie.setSens(1);
		else
			laPartie.setSens(0);
	}
	
	/* Affichage */
	@Override
	public String toString() {
		return "CarteChangement [" + couleur + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(couleur);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarteChangement other = (CarteChangement) obj;
		return couleur == other.couleur;
	}
}
