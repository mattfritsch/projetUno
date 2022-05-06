package partie;

import java.util.ArrayList;

import carte.Carte;
import joueur.Joueur;
import pioche.Pioche;
import tas.Tas;

public class Partie {
	private ArrayList<Joueur> joueurs;
	private Tas tas;
	private Pioche pioche;
	private Joueur joueurCourant;
	private Carte.Couleur couleurCourante;
	private int sens;
	
	
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}
	public Tas getTas() {
		return tas;
	}
	public Pioche getPioche() {
		return pioche;
	}
	public Joueur getJoueurCourant() {
		return joueurCourant;
	}
	public Carte.Couleur getCouleurCourante() {
		return couleurCourante;
	}
	public int getSens() {
		return sens;
	}
	
	
	/* Setters */
	private void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	private void setTas(Tas tas) {
		this.tas = tas;
	}
	private void setPioche(Pioche pioche) {
		this.pioche = pioche;
	}
	private void setJoueurCourant(Joueur joueurCourant) {
		this.joueurCourant = joueurCourant;
	}
	private void setCouleurCourante(Carte.Couleur couleurCourante) {
		this.couleurCourante = couleurCourante;
	}
	private void setSens(int sens) {
		this.sens = sens;
	}
	
	/* Methode metier */
	private Joueur joueurSuivant() {
		if (joueurCourant == joueurs.get(joueurs.size()-1)) {
			return joueurs.get(0);
		}
		return joueurs.get((joueurs.indexOf(this.joueurCourant) + 1));
	}
	
	
}
