package carte;

import exception.PiocheException;
import partie.Partie;
import pioche.Pioche;

public class CartePlusQuatre extends CarteJoker {
	
	/* Constructeur par defaut */
	public CartePlusQuatre() {
		super();
	}
	
	/* Methode metier */
	public void appliquerEffet(Partie laPartie) {
		// +4
		Pioche pioche = laPartie.getPioche();
		try {
			laPartie.ajouterListeDeCarteAuJoueurCourant(pioche.piocher(4));
		} catch(PiocheException e) {
			e.getMessage();
		}
		changerCouleurCourante(laPartie);
		laPartie.passerLeTour();
	}

	/* Affichage */
	@Override
	public String toString() {
		return "CartePlus4";
	}
}
