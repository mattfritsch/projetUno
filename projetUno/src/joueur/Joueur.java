package joueur;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import carte.Carte;
import exception.ExpertException;
import exception.JoueurException;
import exception.PartieException;
import exception.PiocheException;
import main.Main;
import parser.Parser;
import parser.ParserCarteChangement;
import parser.ParserCarteJoker;
import parser.ParserCartePasser;
import parser.ParserCartePlusDeux;
import parser.ParserCartePlusQuatre;
import parser.ParserCarteSimple;
import partie.Partie;
import expert.*;

public class Joueur {
	/* Champs */
	private String nom;
	private Main maMain = new Main();
	private boolean aJouer = false;
	private boolean aDitUno = false;
	
	
	/* Constructeurs */
	
	// Nom et main connu
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

	// Nom connu
	public Joueur(String nom) {
		super();
		this.nom = nom;
	}

	// Par defaut
	public Joueur() {
		
	}

	
	
	/* Getters */
	public String getNom() {
		return nom;
	}

	public Main getMaMain() {
		return maMain;
	}

	public int getNbCarte() {
		return maMain.getMain().size();
	}
	public boolean getAJouer() {
		return aJouer;
	}
	public boolean getADitUno() {
		return aDitUno;
	}
	
	
	/* Setters */
	private void setNom(String nom) throws JoueurException {
		if (nom == null || nom.trim().equals(""))
			throw new JoueurException("Le nom du joueur est invalide");
		this.nom = nom;
	}

	private void setMain(Main maMain) throws JoueurException {
		if (maMain == null)
			throw new JoueurException("La main est null");
		this.maMain = maMain;
	}
	public void setAJouer(boolean aJouer) {
		this.aJouer = aJouer;
	}
	public void setADitUno(boolean aDitUno) {
		this.aDitUno = aDitUno;
	}
	
	@Override
	public String toString() {
		return "Joueur [" + nom + ", " + maMain + "\n]";
	}

	
	
	
	/* Methode metier */
	public boolean ajouterCarte(Carte carte) {
		return maMain.addCarte(carte);
	}
	
	public boolean ajouterListeDeCarte(ArrayList<Carte> cartes) {
		return maMain.addListeDeCarte(cartes);
	}
	
	public boolean removeCarte(Carte carte) {
		return maMain.removeCarte(carte);
	}
	
	public Carte removeCarteAt(int index) {
		return maMain.removeCarteAt(index);
	}
	
	public boolean removeListeDeCarte(ArrayList<Carte> cartes) {
		return maMain.removeListeDeCarte(cartes);
	}
	
	public void punir(Partie partie, int nbCarte) {
		try {
			ajouterListeDeCarte(partie.getPioche().piocher(partie,null,nbCarte));
		} catch (PiocheException e1) {
			fail("Impossible d'ajouter les cartes a "+ this.getNom());
		}
		if (partie.getJoueurCourant() == this) {
			try {
				partie.finirLeTour();
			} catch (PartieException e) {
				e.printStackTrace();
			}
		}
	}
	
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
				expert.traiter(partie, carte, this);
			} catch (ExpertException e) {
				throw new JoueurException(e.getMessage());
			} catch (Exception e1) {
				System.err.println("Aucun expert n'existe pour la carte "+carte);
			}
			this.removeCarte(carte);
		}
		partie.setVientDeJouer(this);
	}
	public void ditUno(Partie partie, Joueur joueur) throws JoueurException {
		if(partie.getJoueurCourant().equals(joueur) && joueur.getNbCarte() == 1 && joueur.getAJouer() == true) {
			joueur.setADitUno(true);
		}
		else {
			throw new JoueurException("Le joueur ne peut pas dire UNO");
		}
	}
}
