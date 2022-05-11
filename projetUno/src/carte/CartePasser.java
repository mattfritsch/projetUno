package carte;

import java.util.Objects;

import carte.Carte.Couleur;
import exception.CarteException;
import partie.Partie;

public class CartePasser {
	/* Champs */
	private Couleur couleur;
	
	/* Constructeur */
	public CartePasser(Couleur couleur) throws CarteException {
		super();
		
		// Classe immuable donc pas de setter
		if (couleur == null)
			throw new CarteException("Une CartePasser doit avoir une couleur valide");
		
		this.couleur = couleur;
	}
	
	/* Getters */
	public Couleur getCouleur() {
		return couleur;
	}

	/* Affichage */
	@Override
	public String toString() {
		return "CartePasser [couleur= " + couleur + "]";
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
	public void appliquerEffet(Partie laPartie) {
		// Passer le tour du joueur suivant
	}
}
