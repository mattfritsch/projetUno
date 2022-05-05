package carte;

public abstract class CarteEvenement extends Carte {
	public enum Effet {
		PASSER,
		PLUS2,
		PLUS4,
		CHANGEMENTSENS,
		CHANGEMENTCOULEUR
	}
	public Effet effet;
	
	public Effet getEffet() {
		return effet;
	}
	
}
