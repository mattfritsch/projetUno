package joueur;

import java.util.ArrayList;
import carte.Carte;
import exception.JoueurException;
import main.Main;

public class Joueur {
	/* Champs */
	private String nom;
	private Main maMain = new Main();
	
	
	
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

	public int getNbCartes() {
		return maMain.getMain().size();
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
	
	@Override
	public String toString() {
		return "Joueur [nom=" + nom + ", maMain=" + maMain + "]";
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
	
}
