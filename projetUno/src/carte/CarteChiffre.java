package carte;

import java.util.Objects;

public class CarteChiffre extends Carte {
	/* Champs */
	private int valeur;
	private Carte.Couleur couleur;
	
	
	
	/* Constructeurs */
	public CarteChiffre(int valeur, Couleur couleur) {
		super();
		this.valeur = valeur;
		this.couleur = couleur;
	}
	
	
	
	/* Getters */
	public int getValeur() {
		return valeur;
	}
	
	public Carte.Couleur getCouleur() {
		return couleur;
	}
	
	
	
	/* Setters */
	private void setValeur(int valeur) {
		this.valeur = valeur;
	}
	
	private void setCouleur(Carte.Couleur couleur) {
		this.couleur = couleur;
	}
	
	
	
	/* Affichage */
	@Override
	public String toString() {
		return "CarteChiffre [valeur=" + valeur + ", couleur=" + couleur + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(couleur, valeur);
	}
	
	/* Methode d'egalite */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarteChiffre other = (CarteChiffre) obj;
		return couleur == other.couleur && valeur == other.valeur;
	}
	
}
