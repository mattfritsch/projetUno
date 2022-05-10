package carte;

import partie.Partie;

public class CartePlus4 extends CarteJoker {
	
	/* Constructeur par defaut */
	public CartePlus4() {
		super();
	}
	
	/* Methode metier */
	public void appliquerEffet(Partie laPartie) {
		//+4
		changerCouleurCourante(laPartie);
	}
}
