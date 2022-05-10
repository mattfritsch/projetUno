package carte;

import carte.Carte.Couleur;
import exception.CarteException;
import partie.Partie;

public class CarteChangement {
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
	
	/* Methode metier */
	public void appliquerEffet(Partie laPartie) {
		// Changement de sens
	}
}
