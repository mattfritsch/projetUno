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

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */
public class Partie {
	private static ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	private static Tas tas = new Tas();
	private static Pioche pioche = new Pioche();
	private static Joueur joueurCourant;
	private static Joueur joueurSuivant;
	private static Carte carteCourante;
	private static Joueur vientDeJouer;
	private static int sens = 0;
	private static int cumulCompteur;
	
	public Partie() {
		
	}
	/* Constructeurs */
	
	/**
	 * Creation d'une partie avec une liste de joueurs
	 * @param joueurs ArrayList
	 * @throws PartieException PartieException
	 */
	public Partie(ArrayList<Joueur> joueurs) throws PartieException {
		if (joueurs == null || joueurs.size() < 2)
			throw new PartieException("Le nombre de joueurs est invalide");
		this.joueurs.addAll(joueurs);
		Partie.joueurCourant = joueurs.get(0);
		calculerJoueurSuivant(0);
	}
	
	/**
	 * Creation d'une partie avec une deux joueurs
	 * @param joueur1 Joueur
	 * @param joueur2 Joueur
	 * @throws PartieException PartieException
	 */
	public Partie(Joueur joueur1, Joueur joueur2) throws PartieException {
		if (joueur1 == null || joueur2 == null)
			throw new PartieException("Au moins un des noms est null");
		this.addJoueur(joueur1);
		this.addJoueur(joueur2);
		Partie.joueurCourant = joueurs.get(0);
		calculerJoueurSuivant(0);
	}
	
	/**
	 * Creation d'une partie avec trois joueurs
	 * @param joueur1 Joueur
	 * @param joueur2 Joueur
	 * @param joueur3 Joueur
	 * @throws PartieException PartieException
	 */
	public Partie(Joueur joueur1, Joueur joueur2, Joueur joueur3) throws PartieException {
		if (joueur1 == null || joueur2 == null || joueur3 == null)
			throw new PartieException("Au moins un des noms est null");
		this.addJoueur(joueur1);
		this.addJoueur(joueur2);
		this.addJoueur(joueur3);
		Partie.joueurCourant = joueurs.get(0);
		calculerJoueurSuivant(0);
	}
	
	/**
	 * Creation d'une partie avec quatre joueurs
	 * @param joueur1 Joueur
	 * @param joueur2 Joueur
	 * @param joueur3 Joueur
	 * @param joueur4 Joueur
	 * @throws PartieException PartieException
	 */
	public Partie(Joueur joueur1, Joueur joueur2, Joueur joueur3, Joueur joueur4) throws PartieException {
		if (joueur1 == null || joueur2 == null || joueur3 == null || joueur4 == null)
			throw new PartieException("Au moins un des noms est null");
		this.addJoueur(joueur1);
		this.addJoueur(joueur2);
		this.addJoueur(joueur3);
		this.addJoueur(joueur4);
		Partie.joueurCourant = joueurs.get(0);
		calculerJoueurSuivant(0);
	}
	
	
	
	/* Getters */
	/**
	 * Retourne la liste de joueurs
	 * @return ArrayList
	 */
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}
	
	/**
	 * Retourne l'index du joueur dans la liste de joueurs
	 * @param joueur Joueur 
	 * @return int
	 */
	public int getIndexDuJoueur(Joueur joueur) {
		return joueurs.indexOf(joueur);
	}
	
	/**
	 * Retourne le joueur a l'index de la liste de joueurs
	 * @param index int
	 * @return Joueur
	 */
	public Joueur getJoueurAt(int index) {
		return joueurs.get(index);
	}
	
	/**
	 * Retourne le nombre de joueurs dans la partie
	 * @return int
	 */
	public int getNbJoueurs() {
		return joueurs.size();
	}
	
	/**
	 * Retourne le tas de la partie
	 * @return Tas
	 */
	public static Tas getTas() {
		return tas;
	}
	
	/**
	 * Retourne la pioche de la partie
	 * @return Pioche
	 */
	public static Pioche getPioche() {
		return pioche;
	}
	
	/**
	 * Retourne le joueur courant
	 * @return Joueur
	 */
	public static Joueur getJoueurCourant() {
		return joueurCourant;
	}
	
	/**
	 * Retourne le joueur suivant
	 * @return Joueur
	 */
	public static Joueur getJoueurSuivant() {
		return joueurSuivant;
	}
	
	/**
	 * Retourne le joueur qui vient de jouer
	 * @return Joueur
	 */
	public static Joueur getVientDeJouer() {
		return vientDeJouer;
	}
	
	/**
	 * Retourne la couleur actuelle de la carte sur le tas
	 * @return Couleur
	 */
	public static Couleur getCouleurCourante() {
		return carteCourante.couleur;
	}
	
	/**
	 * Retourne la valeur actuelle de la carte sur le tas
	 * @return int
	 */
	public static int getValeurCourante() {
		return carteCourante.valeur;
	}
	
	/**
	 * Retourne le sens de la partie
	 * @return int
	 */
	public static int getSens() {
		return sens;
	}
	
	/**
	 * Retourne le cumul de cartes de la partie
	 * @return int
	 */
	public static int getCumulCompteur() {
		return cumulCompteur;
	}
	


	/* Setters */
	
	/**
	 * Ajoute un joueur dans la partie
	 * @param joueur Joueur
	 * @return boolean
	 */
	public boolean addJoueur(Joueur joueur) {
		return joueurs.add(joueur);
	}
	
	/**
	 * Enleve un joueur de la partie
	 * @param joueur Joueur
	 * @return boolean
	 */
	private boolean removeJoueur(Joueur joueur) {
		return joueurs.remove(joueur);
	}
	
	/**
	 * Enleve le joueur a l'index de la partie
	 * @param index int
	 * @return Joueur
	 */
	public Joueur removeJoueurAt(int index) {
		return joueurs.remove(index);
	}
	
	/**
	 * Change la liste de joueurs par une autre liste de joueurs
	 * @param joueurs ArrayList<Joueur>
	 */
	private void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	
	/**
	 * Redefini le joueur courant de la partie
	 * @param joueurCourant Joueur
	 */
	public static void setJoueurCourant(Joueur joueurCourant) {
		Partie.joueurCourant = joueurCourant;
	}
	
	/**
	 * Redefini le joueur suivant
	 * @param joueurSuivant Joueur
	 */
	public static void setJoueurSuivant(Joueur joueurSuivant) {
		Partie.joueurSuivant = joueurSuivant;
	}
	
	/**
	 * Redefini la carte courante de la partie
	 * @param joueurCourant Joueur
	 */
	public static void setCarteCourante(Carte carteCourante) {
		Partie.carteCourante = carteCourante;
	}
	
	/**
	 * Redefini le joueur qui vient de jouer 
	 * @param vientDeJouer Joueur
	 */
	public static void setVientDeJouer(Joueur vientDeJouer) {
		Partie.vientDeJouer = vientDeJouer;
	}
	
	/**
	 * Redefini le sens
	 * @param sens int
	 */
	public static void setSens(int sens) {
		Partie.sens = sens;
	}
	
	/**
	 * Redefini le cumul
	 * @param cumulCompteur int
	 */
	public static void setCumulCompteur(int cumulCompteur) {
		Partie.cumulCompteur = cumulCompteur;
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
	
	/**
	 * Converti un string en couleur
	 * @param s String
	 * @return Couleur
	 */
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
	
	/**
	 * Initialise une partie
	 * @param nomFichier String
	 * @param nbCarteParJoueur int
	 */
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
	
	/**
	 * Initalise les joueurs
	 * @param nbCarteParJoueur int
	 * @throws PartieException PartieException
	 */
	private void initJoueurs(int nbCarteParJoueur) throws PartieException {
		if (nbCarteParJoueur > (pioche.getNbCartes() * getNbJoueurs()))
			throw new PartieException("Nombre de cartes insuffisant dans la pioche pour commencer la partie");
		
		// pour chaque carte on va parcourir tous les joueurs et leur donner 1 carte de la pioche
		for (int j = 0; j < nbCarteParJoueur; j++) {
			// pour la j-ieme carte on va parcourir tous les joueurs
			for (int i = 0 ; i < getNbJoueurs(); i++) {
				try {
					getJoueurAt(i).ajouterListeDeCarte(pioche.piocher(null,1));
				} catch (PiocheException e) {
					e.getMessage();
				}
			}
		}
	}
	
	/**
	 * Initialise le tas
	 */
	private void initTas() {
		try {
			tas.addListeDeCarte(pioche.piocher(null,1));
		} catch (PiocheException e) {
			e.getMessage();
		}
		
		CarteChiffre talon = (CarteChiffre) tas.getTop();
		/*setCouleurCourante(talon.getCouleur());
		setValeurCourante(talon.getValeur());*/
		setCarteCourante(talon);
	}
	
	/**
	 * Garde un nombre de carte dans la pioche
	 * @param n int
	 */
	public void garderLesNPremieresCarteDeLaPioche(int n) {
		for (int i = getPioche().getNbCartes()-1 ; i > n ; i--) {
			getPioche().removeCarte(i);
		}
	}
	
	/**
	 * Calcule et redefini le joueur suivant
	 * @param passerLeTour int 
	 */
	public static void calculerJoueurSuivant(int passerLeTour) {
		if (passerLeTour == 0) {
			if (getSens() == 0) {//sens horaire
				if (joueurCourant == joueurs.get(joueurs.size()-1)) { // dernier de la liste donc on retourne le premier joueur de la liste
					setJoueurSuivant(joueurs.get(0));
				} else {// cas general
					setJoueurSuivant(joueurs.get((joueurs.indexOf(Partie.joueurCourant) + 1)));
				}
			} else {// sens anti horaire
				if (joueurCourant == joueurs.get(0)) {// premier de la liste liste donc on retourne le dernier joueur de la liste
					setJoueurSuivant(joueurs.get(joueurs.size()-1));
				} else {
					setJoueurSuivant(joueurs.get((joueurs.indexOf(Partie.joueurCourant) - 1)));
				}
			}
		} else {// on passe le tour d'un joueur
			if (getSens() == 0) {//sens horaire
				if (joueurCourant == joueurs.get(joueurs.size()-1)) {// dernier de la liste donc on retourne le deuxieme joueur de la liste
					setJoueurSuivant(joueurs.get(1));
				} else if (joueurCourant == joueurs.get(joueurs.size()-2)) {// avant dernier de la liste donc on retourne le premier joueur de la liste
					setJoueurSuivant(joueurs.get(0));
				} else { // cas general
					setJoueurSuivant(joueurs.get((joueurs.indexOf(Partie.joueurCourant) + 2)));
				}
			} else {// sens anti horaire
				if (joueurCourant == joueurs.get(0)) {// premier de la liste liste donc on retourne l'avant dernier joueur de la liste
					setJoueurSuivant(joueurs.get(joueurs.size()-2));
				} else if (joueurCourant == joueurs.get(1)) {// deuxieme de la liste liste donc on retourne le dernier joueur de la liste
					setJoueurSuivant(joueurs.get(joueurs.size()-1));
				} else {// cas general
					setJoueurSuivant(joueurs.get((joueurs.indexOf(Partie.joueurCourant) - 2)));
				}
			}
		}
		
	}
	

	
	/**
	 * Retourne une couleur choisi par le joueur
	 * @return Couleur
	 */
	public Couleur demanderCouleur() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Entrer une couleur parmi : Vert | Jaune | Bleu | Rouge");
		String couleur= sc.nextLine();
		sc.close();
		return convertStringToCouleur(couleur);
	}
	
	/**
	 * Ajoute une carte au joueur courant
	 * @param carte Carte
	 * @return boolean
	 */
	public boolean ajouterCarteAuJoueurCourant(Carte carte) {
		return joueurCourant.ajouterCarte(carte);
	}
	
	/**
	 * Ajoute une liste de carte au joueur courant
	 * @param cartes ArrayList
	 * @return boolean
	 */
	public boolean ajouterListeDeCarteAuJoueurCourant(ArrayList<Carte> cartes) {
		return joueurCourant.ajouterListeDeCarte(cartes);
	}
	
	/**
	 * Retire une carte au joueur courant
	 * @param carte Carte
	 * @return boolean
	 */
	public boolean removeCarteAuJoueurCourant(Carte carte) {
		return joueurCourant.removeCarte(carte);
	}
	
	/**
	 * Retire la carte a d'index de la main du joueur courant
	 * @param index int
	 * @return Carte
	 */
	public Carte removeCarteAuJoueurCourantAt(int index) {
		return joueurCourant.removeCarteAt(index);
	}
	
	/**
	 * Retire la liste de carte de la main du joueur courant
	 * @param cartes ArrayList
	 * @return boolean
	 */
	public boolean removeListeDeCarteAuJoueurCourant(ArrayList<Carte> cartes) {
		return joueurCourant.removeListeDeCarte(cartes);
	}
	
	/**
	 * Passe le tour du joueur suivant
	 */
	public static void passerLeTourDuJoueurSuivant() {
		calculerJoueurSuivant(1);
	}
	
}
