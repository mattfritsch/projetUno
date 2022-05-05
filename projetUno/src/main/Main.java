package main;

import java.util.ArrayList;

import carte.Carte;
import joueur.Joueur;

public class Main {
	private Joueur joueur;
	private ArrayList<Carte> main;
	
	/* Getters */
	public Joueur getJoueur() {
		return joueur;
	}
	public ArrayList<Carte> getMain() {
		return main;
	}
	
	/* Setters */
	private void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	private void setMain(ArrayList<Carte> main) {
		this.main = main;
	}
	
	
}
