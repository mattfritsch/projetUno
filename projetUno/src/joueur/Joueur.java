package joueur;

import java.util.ArrayList;
import carte.Carte;
import exception.JoueurException;
import main.Main;
import partie.Partie;
import expert.*;

public class Joueur {
	/* Champs */
	private String nom;
	private Main maMain = new Main();
	private boolean aJouer = false;
	
	
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
	
	public void jouerUneCarte(Partie partie, Carte carte) throws JoueurException {
		if (this.aJouer == true)
			throw new JoueurException("Le joueur a deja jouer");
		
		Expert expert = new ExpertCarteSimple(null);
		
		//on test si la carte est bien dans la main
		int size = maMain.getNbCarte();
		boolean isInMain =false;
		for (int i=0; i<size; i++) {
			if (maMain.getCarte(i)==carte) isInMain = true;
		}
		if (isInMain == false) {
			throw new JoueurException("La carte n'est pas dans la main");
		} else {
			try {
				expert.traiter(partie, carte, this);
				this.setAJouer(true);
			} catch (Exception e) {
				System.err.println("Aucun expert n'existe pour la carte "+carte);
			}
			maMain.removeCarte(carte);
		}
	}
}
