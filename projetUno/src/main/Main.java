package main;

import java.util.ArrayList;

import carte.Carte;
import exception.MainException;
import joueur.Joueur;

public class Main {
	private Joueur joueur;
	private ArrayList<Carte> main = new ArrayList<Carte>();
	
	/* Constructeurs */
	public Main(Joueur joueur) throws MainException {
		if (joueur == null)
			throw new MainException("Le joueur est null");
		setJoueur(joueur);
	}
	
	public Main(Joueur joueur, ArrayList<Carte> main) throws MainException {
		if (joueur == null)
			throw new MainException("Le joueur est null");
		setJoueur(joueur);
		
		if (main == null) 
			throw new MainException("La main est null");
		setMain(main);
	}
	
	/* Getters */
	public Joueur getJoueur() {
		return joueur;
	}
	public ArrayList<Carte> getMain() {
		return main;
	}
	
	public boolean addCarte(Carte carte) {
		return main.add(carte);
	}
	
	public boolean addListeDeCarte(ArrayList<Carte> cartes) {
		return main.addAll(cartes);
	}
	
	public boolean removeCarte(Carte carte) {
		return main.remove(carte);
	}
	
	public Carte removeCarteAt(int index) {
		return main.remove(index);
	}
	
	public boolean removeListeDeCarte(ArrayList<Carte> cartes) {
		return main.removeAll(cartes);
	}
	
	public Carte getCarte(int index) {
		return main.get(index);
	}
	
	public int getNbCarte() {
		return main.size();
	}
	
	
	/* Setters */
	private void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
	private void setMain(ArrayList<Carte> main) {
		this.main = main;
	}
	
	
}
