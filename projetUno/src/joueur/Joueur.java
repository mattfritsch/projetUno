package joueur;

import java.util.ArrayList;
import carte.Carte;
import main.Main;

public class Joueur {
	/* Champs */
	private String nom;
	private Main maMain;
	
	
	
	/* Constructeurs */
	
	// Nom et main connu
	public Joueur(String nom, Main maMain) {
		super();
		this.nom = nom;
		this.maMain = maMain;
	}

	// Nom connu
	public Joueur(String nom) {
		super();
		this.nom = nom;
	}

	// Par defaut
	public Joueur() {
		super();
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
	private void setNom(String nom) {
		this.nom = nom;
	}

	private void setMaMain(Main maMain) {
		this.maMain = maMain;
	}
	
	
}
