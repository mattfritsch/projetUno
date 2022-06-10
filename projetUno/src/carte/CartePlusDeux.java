package carte;

import exception.CarteException;
import partie.Partie;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

public class CartePlusDeux extends CarteEvenement{	
	/* Constructeur */
	
	/**
	 * Creation d'une CartePlusDeux
	 * @param couleur Couleur
	 * @throws CarteException CarteException
	 */
	public CartePlusDeux(Couleur couleur) throws CarteException {
		super();
		this.valeur = -1;
		// Classe immuable donc pas de setter
		if (couleur == null)
			throw new CarteException("Une CartePlus2 doit avoir une couleur valide");
		
		this.couleur = couleur;
	}

	/* Methode metier */
	public void appliquerEffet() {
		Partie.setCumulCompteur(Partie.getCumulCompteur() + 2);
	}
	
	/* Affichage */
	@Override
	public String toString() {
		return "CartePlus2 [" + couleur + "]";
	}
}
