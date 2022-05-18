package main;

import java.util.ArrayList;

import carte.Carte;
import exception.MainException;
import joueur.Joueur;

public class Main {
	private ArrayList<Carte> main = new ArrayList<Carte>();
	
	/* Constructeurs */
	public Main() {
		
	}
	
	public Main(ArrayList<Carte> main) throws MainException {
		if (main == null) 
			throw new MainException("La main est null");
		setMain(main);
	}
	
	/* Getters */
	/*public Joueur getJoueur() {
		return joueur;
	}*/
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
	/*private void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}*/
	private void setMain(ArrayList<Carte> main) {
		this.main = main;
	}

	@Override
	public String toString() {
		String s = "Main [\n";
		for (Carte carte : main) {
			s+="\t\t";
			s+=carte.toString();
			s+="\n";
		}
		return s + "\t\t]";
	}
	
	/* Methode metier*/
	
	public boolean possedeCarte(Carte carte) {
		boolean trouve = false;
		int i = 0;
		while (trouve == false && i < getNbCarte()) {
			Carte carteEnMain = getMain().get(i);
			if (carteEnMain.equals(carte))
				trouve = true;
			i++;
		}
		return trouve;
	}
	
	public boolean possedeTypeDeCarte(Carte carte) {
		boolean trouve = false;
		int i = 0;
		while (trouve == false && i < getNbCarte()) {
			Carte carteEnMain = getMain().get(i);
			
			if (carteEnMain.getClass() == carte.getClass())
				trouve = true;
			i++;
		}
		return trouve;
	}
	
}
