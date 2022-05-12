package partie;

import java.util.ArrayList;
import java.util.Scanner;

import carte.Carte;
import carte.Carte.Couleur;
import exception.PartieException;
import joueur.Joueur;
import pioche.Pioche;
import carte.CarteChiffre;
import tas.Tas;

public class Partie {
	private ArrayList<Joueur> joueurs;
	private Tas tas = new Tas();
	private Pioche pioche = new Pioche();
	private static Joueur joueurCourant;
	private static Couleur couleurCourante;
	private static int valeurCourante;
	private static int sens = 0;
	
	public Partie(ArrayList<Joueur> joueurs) throws PartieException {
		if (joueurs == null || joueurs.size() < 2)
			throw new PartieException("Le nombre de joueurs est invalide");
		this.joueurs = joueurs;
		Partie.joueurCourant = joueurs.get(0);
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
	public static Joueur getJoueurCourant() {
		return joueurCourant;
	}
	public static Couleur getCouleurCourante() {
		return couleurCourante;
	}
	public static int getValeurCourante() {
		return valeurCourante;
	}
	public static int getSens() {
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
		Partie.joueurCourant = joueurCourant;
	}
	public void setCouleurCourante(Couleur couleurCourante) {
		Partie.couleurCourante = couleurCourante;
	}
	public void setSens(int sens) {
		Partie.sens = sens;
	}
	
	/* Methode metier */
	
	/* Converti un String en Couleur */
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
	
	/* Retourne le joueur suivant */
	public Joueur getJoueurSuivant() {
		if (joueurCourant == joueurs.get(joueurs.size()-1)) {
			return joueurs.get(0);
		}
		return joueurs.get((joueurs.indexOf(Partie.joueurCourant) + 1));
	}
	
	/* Retourne une couleur choisi par le joueur */
	public Couleur demanderCouleur() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Entrer une couleur parmi : Vert | Jaune | Bleu | Rouge");
		String couleur= sc.nextLine();
		sc.close();
		return convertStringToCouleur(couleur);
	}
	
	public boolean ajouterCarteAuJoueurCourant(Carte carte) {
		return joueurCourant.ajouterCarte(carte);
	}
	
	public boolean ajouterListeDeCarteAuJoueurCourant(ArrayList<Carte> cartes) {
		return joueurCourant.ajouterListeDeCarte(cartes);
	}
	
	public boolean removeCarteAuJoueurCourant(Carte carte) {
		return joueurCourant.removeCarte(carte);
	}
	
	public Carte removeCarteAuJoueurCourantAt(int index) {
		return joueurCourant.removeCarteAt(index);
	}
	
	public boolean removeListeDeCarteAuJoueurCourant(ArrayList<Carte> cartes) {
		return joueurCourant.removeListeDeCarte(cartes);
	}
}
