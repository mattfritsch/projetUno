package carte;

import java.util.ArrayList;

import exception.CarteException;
import exception.PiocheException;
import partie.Partie;
import pioche.Pioche;

public class CartePlusDeux extends CarteEvenement{
	/* Champs */
	private Couleur couleur;
	
	/* Constructeur */
	public CartePlusDeux(Couleur couleur) throws CarteException {
		super();
		
		// Classe immuable donc pas de setter
		if (couleur == null)
			throw new CarteException("Une CartePlus2 doit avoir une couleur valide");
		
		this.couleur = couleur;
	}
	
	/* Getters */
	public Couleur getCouleur() {
		return couleur;
	}

	/* Methode metier */
	public void appliquerEffet(Partie laPartie) {
		//+2
		Pioche pioche = laPartie.getPioche();
		/*try {
			laPartie.ajouterListeDeCarteAuJoueurCourant(pioche.piocher(laPartie,2));
		} catch (PiocheException e) {
			e.getMessage();
		}*/
		laPartie.passerLeTourDuJoueurSuivant();
	}
	
	/* Affichage */
	@Override
	public String toString() {
		return "CartePlus2 [" + couleur + "]";
	}
}
