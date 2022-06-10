package joueur;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import carte.Carte;
import exception.ExpertException;
import exception.JoueurException;
import exception.PartieException;
import exception.PiocheException;
import main.Main;
import partie.Partie;
import expert.*;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

public class Joueur {
	/* Champs */
	private String nom;
	private Main maMain = new Main();
	private boolean aJouer = false;
	private boolean aDitUno = false;
	
	
	/* Constructeurs */
	
	/**
	 * Creation d'un joueur avec le nom et sa main
	 * @param nom String
	 * @param maMain Main
	 * @throws JoueurException JoueurException
	 */
	public Joueur(String nom, Main maMain) throws JoueurException {
		super();
		try {
			setNom(nom);
		} catch (JoueurException e) {
			e.getMessage();
		}
		
		try {
			setMain(maMain);
		} catch (JoueurException e) {
			e.getMessage();
		}
	}

	/**
	 * Creation d'un joueur avec le nom
	 * @param nom String
	 */
	public Joueur(String nom) {
		super();
		this.nom = nom;
	}

	
	
	/* Getters */
	
	/**
	 * Retourne le nom du joueur
	 * @return String
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Retourne la main du joueur
	 * @return Main
	 */
	public Main getMaMain() {
		return maMain;
	}

	/**
	 * Retourne le nombre de carte de la main du joueur
	 * @return int
	 */
	public int getNbCarte() {
		return maMain.getMain().size();
	}
	
	/**
	 * Retourne si le joueur a jouer 
	 * @return boolean
	 */
	public boolean getAJouer() {
		return aJouer;
	}
	
	/**
	 * Retourne si le joueur a dit UNO
	 * @return boolean
	 */
	public boolean getADitUno() {
		return aDitUno;
	}
	
	
	/* Setters */
	
	/**
	 * Redefini le nom du joueur
	 * @param nom String
	 * @throws JoueurException JoueurException
	 */
	private void setNom(String nom) throws JoueurException {
		if (nom == null || nom.trim().equals(""))
			throw new JoueurException("Le nom du joueur est invalide");
		this.nom = nom;
	}

	/**
	 * Redefini la main du joueur
	 * @param maMain Main
	 * @throws JoueurException JoueurException
	 */
	private void setMain(Main maMain) throws JoueurException {
		if (maMain == null)
			throw new JoueurException("La main est null");
		this.maMain = maMain;
	}
	
	/**
	 * Redefini si le joueur a jouer
	 * @param aJouer boolean
	 */
	public void setAJouer(boolean aJouer) {
		this.aJouer = aJouer;
	}
	
	/**
	 * Redefini si le joueur a dit UNO
	 * @param aDitUno boolean
	 */
	public void setADitUno(boolean aDitUno) {
		this.aDitUno = aDitUno;
	}
	
	@Override
	public String toString() {
		return "Joueur [" + nom + ", " + maMain + "\n]";
	}

	
	
	
	/* Methode metier */
	
	/**
	 * Ajoute une carte dans la main du joueur
	 * @param carte Carte
	 * @return boolean
	 */
	public boolean ajouterCarte(Carte carte) {
		return maMain.addCarte(carte);
	}
	
	/**
	 * Ajoute une liste de carte dans la main du joueur
	 * @param cartes ArrayList
	 * @return boolean
	 */
	public boolean ajouterListeDeCarte(ArrayList<Carte> cartes) {
		return maMain.addListeDeCarte(cartes);
	}
	
	/**
	 * Retire une carte de la main du joueur
	 * @param carte Carte
	 * @return boolean
	 */
	public boolean removeCarte(Carte carte) {
		return maMain.removeCarte(carte);
	}
	
	/**
	 * Retire la carte a l'index de la main du joueur
	 * @param index int
	 * @return Carte
	 */
	public Carte removeCarteAt(int index) {
		return maMain.removeCarteAt(index);
	}
	
	/**
	 * Retire une liste de carte de la main du joueur
	 * @param cartes ArrayList
	 * @return boolean
	 */
	public boolean removeListeDeCarte(ArrayList<Carte> cartes) {
		return maMain.removeListeDeCarte(cartes);
	}
	
	/**
	 * Methode pour punir un joueur et le faire piocher un nombre de carte
	 * @param partie Partie
	 * @param nbCarte int
	 */
	public void punir(int nbCarte) {
		try {
			ajouterListeDeCarte(Partie.getPioche().piocher(null,nbCarte));
		} catch (PiocheException e1) {
			fail("Impossible d'ajouter les cartes a "+ this.getNom());
		}
		if (Partie.getJoueurCourant() == this) {
			try {
				this.finirLeTour();
			} catch (JoueurException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Methode pour qu'un joueur joue une carte dans une partie
	 * @param partie Partie
	 * @param carte Carte
	 * @throws JoueurException JoueurException
	 */
	public void jouerUneCarte(Partie partie, Carte carte) throws JoueurException {
		if (this.aJouer == true)
			throw new JoueurException("Le joueur a deja jouer");
		
		Expert sixiemeExpert = new ExpertCarteJoker(null);
		Expert cinquiemeExpert = new ExpertCartePlusQuatre(sixiemeExpert);
		Expert quatriemeExpert = new ExpertCartePlusDeux(cinquiemeExpert);
		Expert troisiemeExpert = new ExpertCarteChangement(quatriemeExpert);
		Expert deuxiemeExpert = new ExpertCartePasser(troisiemeExpert);
		Expert expert = new ExpertCarteSimple(deuxiemeExpert);
		
		//on test si la carte est bien dans la main
		if (this.getMaMain().possedeCarte(carte) == false) {
			throw new JoueurException("La carte n'est pas dans la main");
		} else {
			try {
				this.setAJouer(true);
				expert.traiter(carte, this);
			} catch (ExpertException e) {
				throw new JoueurException(e.getMessage());
			} catch (Exception e1) {
				System.err.println("Aucun expert n'existe pour la carte "+carte);
			}
			this.removeCarte(carte);
		}
		Partie.setVientDeJouer(this);
	}
	
	/**
	 * Methode qui permet a un joueur de dire UNO dans une partie
	 * @param partie Partie
	 * @param joueur Joueur
	 * @throws JoueurException JoueurException
	 */
	public void ditUno(Joueur joueur) throws JoueurException {
		if(Partie.getJoueurCourant().equals(joueur) && joueur.getNbCarte() == 1 && joueur.getAJouer() == true) {
			joueur.setADitUno(true);
		}
		else {
			throw new JoueurException("Le joueur ne peut pas dire UNO");
		}
	}
	
	/**
	 * Methode qui permet a un joueur d'encaisser le cumul de carte dans une partie
	 * @param partie Partie
	 */
	public void encaisserCumul(Partie partie) {
		if (!this.getMaMain().possedeTypeDeCarte(partie.getTas().getTop())) {
			this.setAJouer(true);
			Partie.setVientDeJouer(this);
			punir(Partie.getCumulCompteur());
			try {
				this.finirLeTour();
			} catch (JoueurException e) {
				e.getMessage();
			}
		}
		
	}
	
	/**
	 * Fini le tour du joueur
	 * @throws JoueurException JoueurException
	 */
	public void finirLeTour() throws JoueurException {
		if (this != Partie.getJoueurCourant())
			throw new JoueurException("Le joueur n'est pas le joueur courant");
		if (Partie.getJoueurCourant().getAJouer() == false) {
			throw new JoueurException("Le joueur courant n'a pas jouer");
		} else if (Partie.getJoueurCourant().getNbCarte() == 1 && Partie.getJoueurCourant().getADitUno() == false) {
			throw new JoueurException("Le joueur courant n'a pas dit UNO alors qu'il a jouer son avant derniere carte");	
		} else {
			Partie.setJoueurCourant(Partie.getJoueurSuivant());
			Partie.getJoueurCourant().setAJouer(false);
		}
	}
}
