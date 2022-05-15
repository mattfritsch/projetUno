package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import exception.CarteException;
import exception.JoueurException;
import exception.PartieException;
import exception.PiocheException;
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
			partie.garderLesNPremieresCarteDeLaPioche(5);
		}
		
		@Test
		void joueUneCarteDeLaBonneCouleur() {
			// Verifier	que	le	joueur	courant	est	bien	Alice
			if (partie.getJoueurCourant() != alice)
				fail("Le joueur courant n'est pas Alice");
			
			// Vérifier	que	Alice	possède	bien	3	cartes
			if (alice.getNbCarte() != 3)
				fail("Alice ne possede pas 3 cartes");
			
			// Alice	joue	le	« 2	Vert »
			try {
				alice.jouerUneCarte(partie, alice.getMaMain().getCarte(0));
			} catch (JoueurException e) {
				fail("Alice ne peut pas jouer le 2 Vert");
			}
			
			// Vérifier	que	Alice	possède	bien	2	cartes
			if (alice.getNbCarte() != 2)
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
			try {
				partie.finirLeTour();
			} catch (PartieException e) {
				fail("Le joueur n'a pas jouer");
			}
			
			// Vérifier	que	le	joueur	courant	est	Bob
			if (partie.getJoueurCourant() != bob)
				fail("Le joueur courant n'est pas bob");
			System.out.println("[OK] joueUneCarteDeLaBonneCouleur");
		}
		
		@Test
		void joueUneDeCouleurDifferenteMaisDeMemeValeur() {
			
			// Alice	joue	le	« 2	Vert »
			try {
				alice.jouerUneCarte(partie, alice.getMaMain().getCarte(0));
			} catch (JoueurException e) {
				fail("Alice ne peut pas jouer le 2 Vert");
			}
			
			// Alice	finit	le	tour
			try {
				partie.finirLeTour();
			} catch (PartieException e) {
				fail("Le joueur n'a pas jouer");
			}
			
			// DEBUT DU TEST
			
			if (bob.getNbCarte() != 3)
				fail("Bob ne possede pas 3 cartes");
			
			try {
				bob.jouerUneCarte(partie, bob.getMaMain().getCarte(0));
			} catch (JoueurException e) {
				fail("Bob ne peut pas jouer le 2 Vert");
			}
			
			if (bob.getNbCarte() != 2)
				fail("Bob ne possede pas 2 cartes");
			
			CarteChiffre quatreJaune = (CarteChiffre) bob.getMaMain().getCarte(0);
			CarteChiffre neufRouge = (CarteChiffre) bob.getMaMain().getCarte(1);
			if (quatreJaune.getValeur() != 4 || quatreJaune.getCouleur() != Couleur.JAUNE || neufRouge.getValeur() != 9 || neufRouge.getCouleur() != Couleur.ROUGE)
				fail("Bob ne possede pas le	« 4	jaune »	et	le	« 9	rouge »");
			
			CarteChiffre deuxBleu = (CarteChiffre) partie.getTas().getTop();
			if (deuxBleu.getValeur() != 2 || deuxBleu.getCouleur() != Couleur.BLEU)
				fail("La carte au sommet du tas n'est pas le 2 BLEU");
			
			if (partie.getTas().getNbCartes() != 3)
				fail("Le nombre de cartes du tas n'est pas egal a 3");
			
			try {
				partie.finirLeTour();
			} catch (PartieException e) {
				fail("Le joueur n'a pas jouer");
			}
			
			if (partie.getJoueurCourant() != charles)
				fail("Le joueur courant n'est pas charles");
			System.out.println("[OK] joueUneDeCouleurDifferenteMaisDeMemeValeur");
		}
		
		@Test
		void joueUneCarteIllegale() {
			// Alice	joue	le	« 6 jaune »
			try {
				alice.jouerUneCarte(partie, alice.getMaMain().getCarte(1));
			} catch (JoueurException e) {
				if (alice.getNbCarte() != 3)
					fail("Alice ne possede pas 3 cartes");
			}
			System.out.println("[OK] joueUneCarteIllegale");
		}
		
		@Test
		void poseDeuxCartesLegalesDeSuite() {
			// Alice	joue	le	« 2	Vert »
			try {
				alice.jouerUneCarte(partie, alice.getMaMain().getCarte(0));
			} catch (JoueurException e) {
				fail("Alice ne peut pas jouer le 2 Vert");
			}
			
			try {
				partie.finirLeTour();
			} catch (PartieException e) {
				fail("Le joueur n'a pas jouer");
			}
			
			// Bob	joue	le	« 2	Bleu »
			try {
				bob.jouerUneCarte(partie, bob.getMaMain().getCarte(0));
			} catch (JoueurException e) {
				fail("Bob ne peut pas jouer le 2 Bleu");
			}
			
			try {
				partie.finirLeTour();
			} catch (PartieException e) {
				fail("Le joueur n'a pas jouer");
			}
			
			try {
				charles.jouerUneCarte(partie, charles.getMaMain().getCarte(0));
			} catch (JoueurException e) {
				fail("Charles ne peut pas jouer le 9 Bleu");
			}
			
			if (charles.getNbCarte() != 2)
				fail("Charles ne possede pas 2 cartes");
			
			try {
				charles.jouerUneCarte(partie, charles.getMaMain().getCarte(0));
			} catch (JoueurException e) {
				if (charles.getNbCarte() != 2)
					fail("Charles ne possede pas 2 cartes");
			}
			
			System.out.println("[OK] poseDeuxCartesLegalesDeSuite");
		}
		
		@Test
		void joueSansRienFaire() {
			// Alice finit son tour
			try {
				partie.finirLeTour();
			} catch (PartieException e) {
				if (alice.getNbCarte() != 3)
					fail("Alice ne possede pas 3 cartes");
			}
			
			System.out.println("[OK] joueSansRienFaire");
		}
		
		@Test
		void jouePuisPioche() {
			//Alice joue le "2 vert" -> coup est légal
			//Alice pioche
			try {
				alice.jouerUneCarte(partie, alice.getMaMain().getCarte(0));
				if(partie.getJoueurCourant() == partie.getVientDeJouer()) //en attendant de changer piocher
					alice.ajouterListeDeCarte(partie.getPioche().piocher(1));
			} catch (PiocheException e) {
				fail("Le joueur ne pas piocher");
			} catch (JoueurException e) {
				if(alice.getNbCarte() != 2) {
					fail("Alice ne possède pas 2 cartes");
				}
			}
			
			CarteChiffre sixJaune;
			sixJaune = (CarteChiffre) partie.getPioche().getTop();
			try {
				if(!partie.getPioche().getBottom().equals(sixJaune)) {
					fail("La carte de la pioche n'est pas le 6 jaune");
				}
			} catch (PiocheException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("[OK] jouePuisPioche");
		}
		
		@Test
		void punitionCoupIllegalJoueurCourant() {
			if (partie.getJoueurCourant() != alice)
				fail("Alice n'est pas le joueur courant");
			// Alice	joue	le	« 2	Vert »
			try {
				alice.jouerUneCarte(partie, alice.getMaMain().getCarte(1));
			} catch (JoueurException e) {
				
				try {
					alice.ajouterListeDeCarte(partie.getPioche().piocher(2));
				} catch (PiocheException e1) {
					fail("Impossible d'ajouter les cartes a Alice");
				}
				
				if (partie.getJoueurCourant() != bob)
					fail("Le joueur courant n'est pas bob");
				CarteChiffre sixJaune = (CarteChiffre) alice.getMaMain().getCarte(3);
				CarteChiffre quatreRouge = (CarteChiffre) alice.getMaMain().getCarte(4);
				if (alice.getNbCarte() != 5 || !alice.getMaMain().possedeCarte(quatreRouge) || !alice.getMaMain().possedeCarte(sixJaune))
					fail("Alice ne possede pas 5 cartes dont le 6 Jaune et le 4 rouge");
				
				CarteChiffre deuxVert = null;
				try {
					deuxVert = new CarteChiffre(2, Couleur.VERT);
				} catch (CarteException e1) {
					fail("Impossible de creer la carte 2 Vert");
				}
				try {
					if (partie.getPioche().getBottom().equals(deuxVert))
						fail("La prochaine carte de la pioche n'est pas le 2 Vert");
				} catch (PiocheException e1) {
					fail("Pioche vide");
				}
				
				System.out.println("[OK] punitionCoupIllegalJoueurCourant");
			}
		}
		//@Test
		
		 
	}
	
}
