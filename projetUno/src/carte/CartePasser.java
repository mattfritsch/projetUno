package carte;

import java.util.Objects;

import exception.CarteException;
import partie.Partie;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

public class CartePasser extends CarteEvenement{	
	/* Constructeur */
	
	/**
	 * Creation d'une CartePasser
	 * @param couleur Couleur
	 * @throws CarteException CarteException
	 */
	public CartePasser(Couleur couleur) throws CarteException {
		super();
		this.valeur = -1;
		// Classe immuable donc pas de setter
		if (couleur == null)
			throw new CarteException("Une CartePasser doit avoir une couleur valide");
		
		this.couleur = couleur;
	}

	/* Affichage */
	@Override
	public String toString() {
		return "CartePasser [" + couleur + "]";
	}
	
	/* Comparaison */
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
		CartePasser other = (CartePasser) obj;
		return couleur == other.couleur;
	}

	/* Methode metier */
	public void appliquerEffet() {
		Partie.passerLeTourDuJoueurSuivant();
	}
}
