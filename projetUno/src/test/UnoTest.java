package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import exception.JoueurException;
import exception.PartieException;
import joueur.Joueur;
import partie.Partie;
import carte.*;
import carte.Carte.Couleur;

class UnoTest {

	Partie partie;
	Joueur alice;
	Joueur bob;
	Joueur charles;
	
	@Nested
	class PremierTest {
		
		@BeforeEach
		void initTest() {
			alice = new Joueur("Alice");
			bob = new Joueur("Bob");
			charles = new Joueur("Charles");
			
			try {
				partie = new Partie(alice,bob,charles);
			} catch (PartieException e) {
				fail("Probleme lors de la creation de la partie");
			}
			
			partie.initPartie("../jeux_test/JeuTestCarteSimple.csv", 3);
			partie.garderNCarteDansLaPioche(5);
		}
		
		@Test
		void joueUneCarteDeLaBonneCouleur() {
			// Verifier	que	le	joueur	courant	est	bien	Alice
			if (partie.getJoueurCourant() != alice)
				fail("Le joueur courant n'est pas Alice");
			
			// Vérifier	que	Alice	possède	bien	3	cartes
			if (alice.getNbCartes() != 3)
				fail("Alice ne possede pas 3 cartes");
			
			// Alice	joue	le	« 2	Vert »
			try {
				alice.jouerUneCarte(partie, alice.getMaMain().getCarte(0));
			} catch (JoueurException e) {
				fail("Alice ne peut pas jouer le 2 Vert");
			}
			
			// Vérifier	que	Alice	possède	bien	2	cartes
			if (alice.getNbCartes() != 2)
				fail("Alice ne possede pas 2 cartes");
			
			// Vérifier	que	les	cartes	d’Alice	sont	le	« 6	jaune »	et	le	« 1	rouge »
			CarteChiffre sixJaune = (CarteChiffre) alice.getMaMain().getCarte(0);
			CarteChiffre unRouge = (CarteChiffre) alice.getMaMain().getCarte(1);
			if (sixJaune.getValeur() != 6 || sixJaune.getCouleur() != Couleur.JAUNE || unRouge.getValeur() != 1 || unRouge.getCouleur() != Couleur.ROUGE)
				fail("Alice ne possede pas le	« 6	jaune »	et	le	« 1	rouge »");
			
			// Vérifier	que	la	carte	au	sommet	du	tas	est	le	« 2	Vert »
			CarteChiffre deuxVert = (CarteChiffre) partie.getTas().getTop();
			if (deuxVert.getValeur() != 2 || deuxVert.getCouleur() != Couleur.VERT)
				fail("La carte au sommet du	tas	n'est pas le « 2 Vert »");
			
			// Vérifier	que	le	nombre	de	cartes	du	tas	est	2
			if (partie.getTas().getNbCartes() != 2 )
				fail("Le tas n'est pas composé de 2 cartes");
			
			// Alice	finit	le	tour
			partie.finirLeTour();
			
			// Vérifier	que	le	joueur	courant	est	Bob
			if (partie.getJoueurCourant() != bob)
				fail("Le joueur courant n'est pas bob");
			System.out.println("[OK] joueUneCarteDeLaBonneCouleur");
		}
	}
	
}
