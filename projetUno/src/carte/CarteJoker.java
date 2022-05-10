package carte;

import partie.Partie;

public class CarteJoker extends CarteEvenement {
	
	/* Constructeur par defaut */
	public CarteJoker() {
		super();
	}
	
	/* Methode metier */
	public void changerCouleurCourante(Partie laPartie) {
		Carte.Couleur nextCouleur = laPartie.demanderCouleur();
		// couleur forcement valide
		laPartie.setCouleurCourante(nextCouleur);
	}
	
	public void appliquerEffet(Partie laPartie) {
		changerCouleurCourante(laPartie);
	}
}
