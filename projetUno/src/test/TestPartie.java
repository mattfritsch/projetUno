package test;

import java.util.ArrayList;

import carte.Carte;
import carte.Carte.Couleur;
import carte.CarteChangement;
import carte.CarteChiffre;
import carte.CarteEvenement;
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
		Joueur jean = new Joueur("Jean");
		Joueur matthieu = new Joueur("Matthieu");
		
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		
		joueurs.add(nathan);
		joueurs.add(jean);
		joueurs.add(aurelien);
		joueurs.add(matthieu);
		
		Partie partie = null;
		CarteChiffre carte = null;
		CarteChangement carte2 = null;
		try {
			carte = new CarteChiffre(6,Couleur.JAUNE);
			carte2 = new CarteChangement(Couleur.BLEU);
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
		
		System.out.println(joueurs);
		System.out.println("\n------------------------------------------\n");
		
		carte2.appliquerEffet(partie);
		partie.finirLeTour();
		
		System.out.println(partie);
	}
}
