package test;

import carte.Carte;
import carte.CarteChiffre;
import exception.CarteException;

public class TestCarteChiffre {
	public static void main(String[] args) {
		
		try {
			// 8 vert
			CarteChiffre carte1 = new CarteChiffre(8,Carte.Couleur.VERT);
			System.out.println(carte1);
			
			// 10 jaune qui existe pas evidemment
			CarteChiffre carte2 = new CarteChiffre(10,Carte.Couleur.JAUNE);
			System.out.println(carte2);	
			
		} catch (CarteException e) {
			System.err.println(e.getMessage());
		}
	}
}
