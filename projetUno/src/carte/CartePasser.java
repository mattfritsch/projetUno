package carte;

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
	
	/* Methode metier */
	public void appliquerEffet(Partie laPartie) {
		// Passer le tour du joueur suivant
	}
}
