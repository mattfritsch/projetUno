package test;

import exception.JoueurException;
import exception.PartieException;
import joueur.Joueur;
import partie.Partie;

public class TestProjet {
	public static void main(String[] args) {
		Joueur alice = new Joueur("Alice");
		Joueur bob = new Joueur("Bob");
		Joueur charles = new Joueur("Charles");
		
		Partie partie = null;
		
		try {
			partie = new Partie(alice,bob,charles);
		} catch (PartieException e) {
			e.printStackTrace();
		}
		
		// on prend toutes les cartes
		partie.initPartie("../jeux_test/JeuTestCarteSimple.csv", 3);
		
		// on garde que celle qu'on veut pour le test
		for (int i = partie.getPioche().getNbCartes()-1 ; i > 4 ; i--) {
			partie.getPioche().removeCarte(i);
		}
			
		
		
		
		// Verifier	que	le	joueur	courant	est	bien	Alice
		if (partie.getJoueurCourant() == alice)
			System.out.println("Le joueur courant est bien Alice");
		
		// Vérifier	que	Alice	possède	bien	3	cartes
		if (alice.getNbCartes() == 3)
			System.out.println("Alice possede bien 3 cartes");
		
		// Alice	joue	le	« 2	Vert »
		try {
			alice.jouerUneCarte(partie, alice.getMaMain().getCarte(0));
		} catch (JoueurException e) {
			e.printStackTrace();
		}
		
		// Vérifier	que	Alice	possède	bien	2	cartes
		if (alice.getNbCartes() == 2)
			System.out.println("Alice possede bien 2 cartes");
		
		// Vérifier	que	les	cartes	d’Alice	sont	le	« 6	jaune »	et	le	« 1	rouge »
		System.out.println(alice);
		
		// Vérifier	que	la	carte	au	sommet	du	tas	est	le	« 2	Vert »
		System.out.println(partie.getTas().getTop());
		
		// Vérifier	que	le	nombre	de	cartes	du	tas	est	2
		if (partie.getTas().getNbCartes() == 2 )
			System.out.println("Le tas est composé de 2 cartes");
		
		// Alice	finit	le	tour
		partie.finirLeTour();
		
		// Vérifier	que	le	joueur	courant	est	Bob
		if (partie.getJoueurCourant() == bob)
			System.out.println("Le joueur courant est bob");

	}
}
