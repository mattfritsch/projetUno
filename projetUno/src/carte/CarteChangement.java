package carte;

import java.util.Objects;

import exception.CarteException;
import partie.Partie;

public class CarteChangement extends CarteEvenement {
	/* Champs */
	private Couleur couleur;
	
	/* Constructeur */
	public CarteChangement(Couleur couleur) throws CarteException {
		super();
		
		// Classe immuable donc pas de setter
		if (couleur == null)
			throw new CarteException("Une CarteChangement doit avoir une couleur valide");
		
		this.couleur = couleur;
	}
	public Couleur getCouleur() {
		return couleur;
	}
	
	/* Methode metier */
	public void appliquerEffet(Partie laPartie) {
		// Changement de sens
	}
	
	/* Affichage */
	@Override
	public String toString() {
		return "CarteChangement [couleur= " + couleur + "]";
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
