package carte;

import java.util.Objects;

import exception.CarteException;

public class CarteChiffre extends Carte {
	/* Champs */
	private int valeur;
	private Couleur couleur;
	
	
	
	/* Constructeurs */
	public CarteChiffre(int valeur, Couleur couleur) throws CarteException {
		super();
		
		// Classe immuable donc pas de setter
		if (valeur < 0 || valeur > 9) 
			throw new CarteException("Une CarteChiffre doit avoir une valeur entre 0 et 9");
		
		this.valeur = valeur;
		
		if (couleur == null)
			throw new CarteException("Une CarteChiffre doit avoir une couleur valide");
		
		this.couleur = couleur;
	}
	
	
	
	/* Getters */
	public int getValeur() {
		return valeur;
	}
	
	public Couleur getCouleur() {
		return couleur;
	}
	
	
	
	/* Affichage */
	@Override
	public String toString() {
		return "CarteChiffre [numero= " + valeur + ", couleur= " + couleur + "]";
	}
	
	/* Comparaison */
	@Override
	public int hashCode() {
		return Objects.hash(couleur, valeur);
	}
	
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
