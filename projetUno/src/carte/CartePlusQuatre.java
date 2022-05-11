package carte;

import partie.Partie;

public class CartePlusQuatre extends CarteJoker {
	
	/* Constructeur par defaut */
	public CartePlusQuatre() {
		super();
	}
	
	/* Methode metier */
	public void appliquerEffet(Partie laPartie) {
		//+4
		changerCouleurCourante(laPartie);
	}

	/* Affichage */
	@Override
	public String toString() {
		return "CartePlus4 []";
	}
}
