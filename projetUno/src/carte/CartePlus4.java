package carte;

public class CartePlus4 extends CarteJoker {
	public CartePlus4() {
		this.effet = Effet.PLUS4;
	}
	
	public void Plus4(Carte.Couleur couleur) {
		changerCouleur(Couleur.BLEU);
		// +4
		// passer le tour
	}
}
