package partie;

import java.util.ArrayList;
import java.util.Scanner;

import carte.Carte.Couleur;
import exception.PartieException;
import joueur.Joueur;
import pioche.Pioche;
import tas.Tas;

public class Partie {
	private ArrayList<Joueur> joueurs;
	private Tas tas = new Tas();
	private Pioche pioche = new Pioche();
	private Joueur joueurCourant;
	private Couleur couleurCourante;
	private int sens = 0;
	
	public Partie(ArrayList<Joueur> joueurs) throws PartieException {
		if (joueurs == null || joueurs.size() < 2)
			throw new PartieException("Le nombre de joueurs est invalide");
		this.joueurs = joueurs;
		this.joueurCourant = joueurs.get(0);
	}
	
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
	public Couleur getCouleurCourante() {
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
	public void setCouleurCourante(Couleur couleurCourante) {
		this.couleurCourante = couleurCourante;
	}
	private void setSens(int sens) {
		this.sens = sens;
	}
	
	/* Methode metier */
	public static Couleur convertStringToCouleur(String s) {
		if (s.equalsIgnoreCase("Bleu"))
			return Couleur.BLEU;
		else if (s.equalsIgnoreCase("Vert"))
			return Couleur.VERT;
		else if (s.equalsIgnoreCase("Rouge"))
			return Couleur.ROUGE;
		else if (s.equalsIgnoreCase("Jaune"))
			return Couleur.JAUNE;
		else
			throw new IllegalArgumentException("convertStringToCouleur la couleur est invalide"); // peut etre changer le type de l'exception?
    }
	
	private Joueur getJoueurSuivant() {
		if (joueurCourant == joueurs.get(joueurs.size()-1)) {
			return joueurs.get(0);
		}
		return joueurs.get((joueurs.indexOf(this.joueurCourant) + 1));
	}
	
	public Couleur demanderCouleur() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Entrer une couleur parmi : Vert | Jaune | Bleu | Rouge");
		String couleur= sc.nextLine();
		sc.close();
		return convertStringToCouleur(couleur);
	}
	
}
