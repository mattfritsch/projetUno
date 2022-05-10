package carte;

import partie.Partie;

public abstract class CarteEvenement extends Carte {
	
	public CarteEvenement() {
		super();
	}
	
	public abstract void appliquerEffet(Partie laPartie);
	
}
