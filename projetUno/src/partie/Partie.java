package partie;

import java.util.ArrayList;
import java.util.Scanner;

import carte.Carte;
import carte.Carte.Couleur;
import exception.FichierException;
import exception.PartieException;
import exception.PiocheException;
import joueur.Joueur;
import pioche.Pioche;
import carte.CarteChiffre;
import tas.Tas;
import util.Fichier;

public class Partie {
	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	private Tas tas = new Tas();
	private Pioche pioche = new Pioche();
	private Joueur joueurCourant;
	private Couleur couleurCourante;
	private int valeurCourante;
	private int sens = 0;
	
	
	/* Constructeurs */
	public Partie(ArrayList<Joueur> joueurs) throws PartieException {
		if (joueurs == null || joueurs.size() < 2)
			throw new PartieException("Le nombre de joueurs est invalide");
		this.joueurs.addAll(joueurs);
		this.joueurCourant = joueurs.get(0);
	}
	
	/* 2 joueurs */
	public Partie(Joueur joueur1, Joueur joueur2) throws PartieException {
		if (joueur1 == null || joueur2 == null)
			throw new PartieException("Au moins un des noms est null");
		this.addJoueur(joueur1);
		this.addJoueur(joueur2);
		this.joueurCourant = joueurs.get(0);
	}
	
	/* 3 joueurs */
	public Partie(Joueur joueur1, Joueur joueur2, Joueur joueur3) throws PartieException {
		if (joueur1 == null || joueur2 == null || joueur3 == null)
			throw new PartieException("Au moins un des noms est null");
		this.addJoueur(joueur1);
		this.addJoueur(joueur2);
		this.addJoueur(joueur3);
		this.joueurCourant = joueurs.get(0);
	}
	
	/* 4 joueurs */
	public Partie(Joueur joueur1, Joueur joueur2, Joueur joueur3, Joueur joueur4) throws PartieException {
		if (joueur1 == null || joueur2 == null || joueur3 == null || joueur4 == null)
			throw new PartieException("Au moins un des noms est null");
		this.addJoueur(joueur1);
		this.addJoueur(joueur2);
		this.addJoueur(joueur3);
		this.addJoueur(joueur4);
		this.joueurCourant = joueurs.get(0);
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
	public Joueur getJoueurCourant() {
		return joueurCourant;
	}
	public Couleur getCouleurCourante() {
		return couleurCourante;
	}
	public int getValeurCourante() {
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
		this.joueurCourant = joueurCourant;
	}
	public void setCouleurCourante(Couleur couleurCourante) {
		this.couleurCourante = couleurCourante;
	}
	public void setValeurCourante(int valeurCourante) {
		this.valeurCourante = valeurCourante;
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
	
	public void initPartie(String nomFichier, int nbCarteParJoueur) {
		try {
			Fichier.lire(nomFichier, getPioche());
		} catch (FichierException e) {
			e.getMessage();
		}
		try {
			initJoueurs(nbCarteParJoueur);
		} catch (PartieException e) {
			e.getMessage();
		}
		initTas();
	}
	
	private void initJoueurs(int nbCarteParJoueur) throws PartieException {
		if (nbCarteParJoueur > (pioche.getNbCartes() * getNbJoueurs()))
			throw new PartieException("Nombre de cartes insuffisant dans la pioche pour commencer la partie");
		
		// pour chaque carte on va parcourir tous les joueurs et leur donner 1 carte de la pioche
		for (int j = 0; j < nbCarteParJoueur; j++) {
			// pour la j-ieme carte on va parcourir tous les joueurs
			for (int i = 0 ; i < getNbJoueurs(); i++) {
				try {
					getJoueurAt(i).ajouterListeDeCarte(pioche.piocher(1));
				} catch (PiocheException e) {
					e.getMessage();
				}
			}
		}
	}
	
	private void initTas() {
		try {
			tas.addListeDeCarte(pioche.piocher(1));
		} catch (PiocheException e) {
			e.getMessage();
		}
		CarteChiffre talon = (CarteChiffre) tas.getTop();
		setCouleurCourante(talon.getCouleur());
		setValeurCourante(talon.getValeur());
	}
	
	public void garderLesNPremieresCarteDeLaPioche(int n) {
		for (int i = getPioche().getNbCartes()-1 ; i > n ; i--) {
			getPioche().removeCarte(i);
		}
	}
	
	public Joueur getJoueurSuivant() {
		if (sens == 0) {
			if (joueurCourant == joueurs.get(joueurs.size()-1)) {
				return joueurs.get(0);
			}
			return joueurs.get((joueurs.indexOf(this.joueurCourant) + 1));
		} else {
			if (joueurCourant == joueurs.get(0)) {
				return joueurs.get(joueurs.size()-1);
			}
			return joueurs.get((joueurs.indexOf(this.joueurCourant) - 1));
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
		setJoueurCourant(joueurs.get((joueurs.indexOf(this.joueurCourant) +2 )));
	}
	
	public void finirLeTour() {
		setJoueurCourant(getJoueurSuivant());
	}
}
