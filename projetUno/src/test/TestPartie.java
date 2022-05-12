package test;

import java.util.ArrayList;

import carte.Carte;
import carte.Carte.Couleur;
import carte.CarteChiffre;
import carte.CarteJoker;
import exception.CarteException;
import exception.PartieException;
import joueur.Joueur;
import partie.Partie;
import pioche.Pioche;
import tas.Tas;

public class TestPartie {
	public static void main(String[] args) {
		Pioche pioche = new Pioche();
		Tas tas = new Tas();
		
		Joueur aurelien = new Joueur("Aurelien");
		Joueur nathan = new Joueur("Nathan");
		
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		joueurs.add(aurelien);
		joueurs.add(nathan);
		Partie partie = null;
		Carte carte = null;
		Carte carte2 = null;
		try {
			carte = new CarteChiffre(6,Couleur.JAUNE);
			carte2 = new CarteJoker();
		} catch(CarteException e) {
			e.getMessage();
		}
		
		try {
			partie = new Partie(joueurs);
		} catch (PartieException e) {
			e.getMessage();
		}
		ArrayList<Carte> cartes = new ArrayList<Carte>();
		cartes.add(carte);
		cartes.add(carte2);
		
		partie.ajouterListeDeCarteAuJoueurCourant(cartes);
		//partie.ajouterCarteAuJoueurCourant(carte);
		System.out.println(partie);
	}
}
