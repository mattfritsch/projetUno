package carte;

import carte.Carte.Couleur;
import exception.CarteException;
import partie.Partie;

public class CartePlus2 {
	/* Champs */
	private Couleur couleur;
	
	/* Constructeur */
	public CartePlus2(Couleur couleur) throws CarteException {
		super();
		
		// Classe immuable donc pas de setter
		if (couleur == null)
			throw new CarteException("Une CartePlus2 doit avoir une couleur valide");
		
		this.couleur = couleur;
	}
	
	/* Methode metier */
	public void appliquerEffet(Partie laPartie) {
		//+2
	}
}
