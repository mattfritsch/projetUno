package main;

import java.util.ArrayList;

import carte.Carte;
import exception.MainException;
import joueur.Joueur;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

public class Main {
	private ArrayList<Carte> main = new ArrayList<Carte>();
	
	/**
	 * Creation d'une main
	 */
	public Main() {
		
	}
	
	/**
	 * Creation d'une main a partir d'une liste de carte
	 * @param main ArrayList
	 * @throws MainException MainException
	 */
	public Main(ArrayList<Carte> main) throws MainException {
		if (main == null) 
			throw new MainException("La main est null");
		setMain(main);
	}
	
	/* Getters */
	
	/**
	 * Retourne la main
	 * @return ArrayList
	 */
	public ArrayList<Carte> getMain() {
		return main;
	}
	
	/**
	 * Ajoute une carte a la main
	 * @param carte Carte
	 * @return boolean
	 */
	public boolean addCarte(Carte carte) {
		return main.add(carte);
	}
	
	/**
	 * Ajoute une liste de carte a la main
	 * @param cartes ArrayList
	 * @return boolean
	 */
	public boolean addListeDeCarte(ArrayList<Carte> cartes) {
		return main.addAll(cartes);
	}
	
	/**
	 * Retire une carte de la main
	 * @param carte Carte
	 * @return boolean
	 */
	public boolean removeCarte(Carte carte) {
		return main.remove(carte);
	}
	
	/**
	 * Retire la carte a l'index de la main
	 * @param index int
	 * @return Carte
	 */
	public Carte removeCarteAt(int index) {
		return main.remove(index);
	}
	
	/**
	 * Retire une liste de carte de la main
	 * @param cartes ArrayList
	 * @return boolean
	 */
	public boolean removeListeDeCarte(ArrayList<Carte> cartes) {
		return main.removeAll(cartes);
	}
	
	/**
	 * Retourne la carte a l'index
	 * @param index int
	 * @return Carte
	 */
	public Carte getCarte(int index) {
		return main.get(index);
	}
	
	/**
	 * Retourne le nombre de carte dans la main
	 * @return int
	 */
	public int getNbCarte() {
		return main.size();
	}
	
	
	/* Setters */
	
	/**
	 * Redefini la liste de carte
	 * @param main ArrayList
	 */
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
	
	/**
	 * Methode qui verifie si une carte appartient a une main
	 * @param carte Carte
	 * @return boolean
	 */
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
	
	/**
	 * Methode qui verifie si dans un main il existe carte un de meme type que carte
	 * @param carte Carte
	 * @return boolean
	 */
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
