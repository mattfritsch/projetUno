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
	private int sens = 0;
	
	
	/* Constrcuteurs */
	public Partie(ArrayList<Joueur> joueurs) throws PartieException {
		if (joueurs == null || joueurs.size() < 2)
			throw new PartieException("Le nombre de joueurs est invalide");
		this.joueurs = joueurs;
		Partie.joueurCourant = joueurs.get(0);
	}
	
	
	
	/* Getters */
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}
	public int getIndexDuJoueur(Joueur joueur) {
		return joueurs.indexOf(joueur);
	}
	public Joueur getJoueurAt(int index) {
		return joueurs.get(index);
	}
	public int getNbJoueurs() {
		return joueurs.size();
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
	public int getSens() {
		return sens;
	}
	


	/* Setters */
	public boolean addJoueur(Joueur joueur) {
		return joueurs.add(joueur);
	}
	private boolean removeJoueur(Joueur joueur) {
		return joueurs.remove(joueur);
	}
	public Joueur removeJoueurAt(int index) {
		return joueurs.remove(index);
	}
	private Joueur setJoueurAt(int index, Joueur joueur) {
		return joueurs.set(index, joueur);
	}
	private void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	private void setTas(Tas tas) {
		this.tas = tas;
	}
	private void setPioche(Pioche pioche) {
		this.pioche = pioche;
	}
	public void setJoueurCourant(Joueur joueurCourant) {
		Partie.joueurCourant = joueurCourant;
	}
	public void setCouleurCourante(Couleur couleurCourante) {
		Partie.couleurCourante = couleurCourante;
	}
	public void setSens(int sens) {
		this.sens = sens;
	}
	
	
	
	/* Affichage */
	@Override
	public String toString() {
		String s = "Partie [joueurs=" + joueurs + ", tas=" + tas + ", pioche=" + pioche + "]";
		s += "\n------------------------------------------\n";
		s += "Le joueur courant est : " + joueurCourant;
		s += "\n------------------------------------------\n";
		return s;
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
	
	public Joueur getJoueurSuivant() {
		if (sens == 0) {
			if (joueurCourant == joueurs.get(joueurs.size()-1)) {
				return joueurs.get(0);
			}
			return joueurs.get((joueurs.indexOf(Partie.joueurCourant) + 1));
		} else {
			if (joueurCourant == joueurs.get(0)) {
				return joueurs.get(joueurs.size()-1);
			}
			return joueurs.get((joueurs.indexOf(Partie.joueurCourant) - 1));
		}
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
	
	public void passerLeTourDuJoueurSuivant() {
		if (joueurCourant == joueurs.get(joueurs.size()-1)) {
			setJoueurCourant(joueurs.get(1));
		}
		if (joueurCourant == joueurs.get(joueurs.size()-2)) {
			setJoueurCourant(joueurs.get(0));
		}
		setJoueurCourant(joueurs.get((joueurs.indexOf(Partie.joueurCourant) +2 )));
	}
	
	public void finirLeTour() {
		setJoueurCourant(getJoueurSuivant());
	}
}
