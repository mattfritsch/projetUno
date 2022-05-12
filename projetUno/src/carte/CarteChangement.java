package carte;

import java.util.Objects;

import exception.CarteException;
import partie.Partie;

public class CarteChangement extends CarteEvenement {
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
	public Couleur getCouleur() {
		return couleur;
	}
	
	/* Methode metier */
	public void appliquerEffet(Partie laPartie) {
		changementDeSens(laPartie);
	}
	
	/* Inverser le sens de la partie*/
	public void changementDeSens(Partie laPartie) {
		System.out.println("\n HELLOOOOOOOO");
		if (Partie.getJoueurCourant() == laPartie.getJoueurAt(0)) { // joueurCourant est le premier de la liste
			for (int i = 1; i < (laPartie.getNbJoueurs() - 1) / 2; i++) {
				laPartie.swapJoueurPositionInArrayList(i, laPartie.getNbJoueurs() - i);
			}
		} else if (Partie.getJoueurCourant() == laPartie.getJoueurAt(laPartie.getNbJoueurs() - 1)) { // joueurCourant est le dernier de la liste
			for (int i = 0; i < (laPartie.getNbJoueurs() - 1) / 2; i++) {
				laPartie.swapJoueurPositionInArrayList(i, laPartie.getNbJoueurs() - i - 1);
			}
		} else { // joueurCourant est quelque part dans la liste
			int posJoueurCourant = laPartie.getIndexDuJoueur(Partie.getJoueurCourant());

			for (int i = 0; i < posJoueurCourant; i++) {
				laPartie.addJoueur(laPartie.removeJoueurAt(0));
				laPartie.setJoueurCourant(laPartie.getJoueurAt(posJoueurCourant - 1)); // On actualise la position du joueurCourant
			}
			// Le joueurCourant est donc maintenant le premier de la liste
			// On rappelle donc maintenant la fonction pour rentrer dans le cas ou le joueur est le premier
			changementDeSens(laPartie);
		}			
	}
	
	/* Affichage */
	@Override
	public String toString() {
		return "CarteChangement [couleur= " + couleur + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(couleur);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarteChangement other = (CarteChangement) obj;
		return couleur == other.couleur;
	}
}
