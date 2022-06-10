package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
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
			System.out.println("\n--------------------- Premier Test ---------------------\n");
		}
		
		@Test
		void joueUneCarteDeLaBonneCouleur() {
			// Verifier	que	le	joueur	courant	est	bien	Alice
			if (Partie.getJoueurCourant() != alice)
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
				alice.finirLeTour();
			} catch (JoueurException e) {
				fail("Le joueur n'a pas jouer");
			}
			
			// Vérifier	que	le	joueur	courant	est	Bob
			if (Partie.getJoueurCourant() != bob)
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
				alice.finirLeTour();
			} catch (JoueurException e) {
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
			if (quatreJaune.getValeur() != 4 && quatreJaune.getCouleur() != Couleur.JAUNE || neufRouge.getValeur() != 9 && neufRouge.getCouleur() != Couleur.ROUGE)
				fail("Bob ne possede pas le	« 4	jaune »	et	le	« 9	rouge »");
			
			CarteChiffre deuxBleu = (CarteChiffre) partie.getTas().getTop();
			if (deuxBleu.getValeur() != 2 && deuxBleu.getCouleur() != Couleur.BLEU)
				fail("La carte au sommet du tas n'est pas le 2 BLEU");
			
			if (partie.getTas().getNbCartes() != 3)
				fail("Le nombre de cartes du tas n'est pas egal a 3");
			
			try {
				bob.finirLeTour();
			} catch (JoueurException e) {
				fail("Le joueur n'a pas jouer");
			}
			
			if (Partie.getJoueurCourant() != charles)
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
				else {
					//dont le 6 jaune
					CarteChiffre sixJaune = (CarteChiffre) alice.getMaMain().getCarte(1);
					if (!alice.getMaMain().possedeCarte(sixJaune))
						fail("Alice n'a pas la carte 6 jaune");
				}
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
				alice.finirLeTour();
			} catch (JoueurException e) {
				fail("Le joueur n'a pas jouer");
			}
			
			// Bob	joue	le	« 2	Bleu »
			try {
				bob.jouerUneCarte(partie, bob.getMaMain().getCarte(0));
			} catch (JoueurException e) {
				fail("Bob ne peut pas jouer le 2 Bleu");
			}
			
			try {
				bob.finirLeTour();
			} catch (JoueurException e) {
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
				else {
					//dont le 7 bleu
					CarteChiffre septBleu = (CarteChiffre) charles.getMaMain().getCarte(1);
					if (!charles.getMaMain().possedeCarte(septBleu))
						fail("Charles n'a pas la carte 7 bleu");
				}
			}
			
			System.out.println("[OK] poseDeuxCartesLegalesDeSuite");
		}
		
		@Test
		void joueSansRienFaire() {
			// Alice finit son tour
			try {
				alice.finirLeTour();
			} catch (JoueurException e) {
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
				alice.ajouterListeDeCarte(partie.getPioche().piocher(partie,alice,1));
			} catch (PiocheException e) {
				if(alice.getNbCarte() != 2) {
					fail("Alice ne possède pas 2 cartes");
				}
			} catch (JoueurException e) {
				fail("Alice ne peut pas jouer sa carte");
			}
			
			
			CarteChiffre sixJaune;
			try {
				sixJaune = (CarteChiffre) partie.getPioche().getBottom();
				if(!partie.getPioche().getBottom().equals(sixJaune)) {
					fail("La carte de la pioche n'est pas le 6 jaune");
				}
			} catch (PiocheException e1) {
				fail("La carte de la pioche n'est pas le 6 jaune");
			}
			
			System.out.println("[OK] jouePuisPioche");
		}
		
		@Test
		void punitionCoupIllegalJoueurCourant() {
			if (Partie.getJoueurCourant() != alice)
				fail("Alice n'est pas le joueur courant");
			// Alice	joue	le	« 6 jaune »
			try {
				alice.jouerUneCarte(partie, alice.getMaMain().getCarte(1));
			} catch (JoueurException e) {
				
				alice.punir(partie,2);
				
				if (Partie.getJoueurCourant() != bob)
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
					if (!partie.getPioche().getBottom().equals(deuxVert))
						fail("La prochaine carte de la pioche n'est pas le 2 Vert");
				} catch (PiocheException e1) {
					fail("Pioche vide");
				}
				
				
			}
			System.out.println("[OK] punitionCoupIllegalJoueurCourant");
		}
		@Test
		void punitionBobPasJoueurCourant() {
			if(!Partie.getJoueurCourant().equals(alice)) {
				fail("Le joueur courant n'est pas alice");
			}
			try {
				bob.ajouterListeDeCarte(partie.getPioche().piocher(partie,bob, 1));
			} catch (PiocheException e) {
				
				bob.punir(partie,2);
				
				if (!Partie.getJoueurCourant().equals(alice))
					fail("le joueur courant n'est pas alice");
				
				CarteChiffre sixJaune = (CarteChiffre) bob.getMaMain().getCarte(3);
				CarteChiffre quatreRouge = (CarteChiffre) bob.getMaMain().getCarte(4);
				
				if (bob.getNbCarte() != 5 || !bob.getMaMain().possedeCarte(sixJaune) || !bob.getMaMain().possedeCarte(quatreRouge)) {
					fail("bob ne possede pas 5 carte dont le 6 jaune et le 4 rouge ");
				}
				
				CarteChiffre deuxVert = null;
				try {
					deuxVert = new CarteChiffre(2, Couleur.VERT);
				} catch (CarteException e1) {
					fail("Impossible de creer la carte 2 Vert");
				}
				
				
				try {
					if (!partie.getPioche().getBottom().equals(deuxVert))
						fail("La prochaine carte de la pioche n'est pas le 2 Vert");
				} catch (PiocheException e1) {
					fail("Pioche vide");
				}
			}
			System.out.println("[OK] punitionBobPasJoueurCourant");
		}
		
	}
	
	@Nested
	class SecondTest {
		
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
			
			partie.initPartie("../jeux_test/JeuTestCarteSimplePourTestUno.csv", 2);
			partie.garderLesNPremieresCarteDeLaPioche(5);
			System.out.println("\n--------------------- Deuixème Test ---------------------\n");
		}
		@Test
		void ditUnoAuBonMoment() {
			//Verifier qu'Alice a deux cartes
			if(alice.getNbCarte() != 2)
				fail("Le nombre de carte d'Alice n'est pas égale à 2");
			//alice pose le 2 vert
			try{
				alice.jouerUneCarte(partie, alice.getMaMain().getCarte(0));
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas poser sa carte");
			}
			//alice dit uno
			try {
				alice.ditUno(alice);
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas dire uno");
			}
			//alice finit son tour
			try {
				alice.finirLeTour();
			}
			catch(JoueurException e) {
				fail("Le tour n'est pas terminé");
			}
			//verifier qu'Alice n'a plus qu'une seule carte
			if(alice.getNbCarte() != 1) {
				fail("Le nombre de carte d'alice n'est pas égale à 1");
			}
			//verifier que la derniere carte du tas est le 2 vert
			CarteChiffre deuxVert = (CarteChiffre) partie.getTas().getTop();
			if (deuxVert.getValeur() != 2 || deuxVert.getCouleur() != Couleur.VERT)
				fail("La carte au sommet du tas n'est pas le 2 VERT");
			//verifier que le joueur courant est bob
			if(!Partie.getJoueurCourant().equals(bob)) {
				fail("Le joueur courant n'est pas bob");
			}
			System.out.println("[OK] ditUnoAuBonMoment");
		}
		@Test
        void oublieDeDireUno() {
            //alice pose le 2 vert
            try {
                alice.jouerUneCarte(partie, alice.getMaMain().getCarte(0));
            } catch (JoueurException e) {
                fail("Le joueur ne peut pas posé sa carte");
            }
            try {
                alice.finirLeTour();
            } catch (JoueurException e) {
                alice.punir(partie,3);
                if(alice.getMaMain().getNbCarte() != 4)
                    fail("Le joueur n'a pas 4 cartes");
              //verifier que la derniere carte du tas est le 8 vert
                CarteChiffre deuxVert = (CarteChiffre) partie.getTas().getTop();
                if (deuxVert.getValeur() != 2 || deuxVert.getCouleur() != Couleur.VERT)
                    fail("La carte au sommet du tas n'est pas le 2 VERT");
                if(!Partie.getJoueurCourant().equals(bob)){
                    fail("Le joueur courant n'est pas bob");
                }
               
    		}
    
            System.out.println("[OK] oublieDeDireUno");
        }
		@Test
		void ditUnoAuMauvaisTour() {
			//verifier que alice est le joueur courant
			if(!Partie.getJoueurCourant().equals(alice))
				fail("Alice n'est pas le joueur courant");
			//bob dit "uno"
			try {
				bob.ditUno(bob);
			} catch (JoueurException e) {
				//punir bob
				bob.punir(partie,2);
				//verifier que bob a maintenant 4 cartes
				if(bob.getNbCarte() != 4)
					fail("Bob a un nombre de carte différent de 4");
				//verifier que alice est toujours le joueur courant
				if(!Partie.getJoueurCourant().equals(alice))
					fail("Alice n'est pas le joueur courant");
				//verifier que la carte au sommet du tas est le 8 vert
				 CarteChiffre huitVert = (CarteChiffre) partie.getTas().getTop();
	             if (huitVert.getValeur() != 8 || huitVert.getCouleur() != Couleur.VERT)
	                 fail("La carte au sommet du tas n'est pas le 8 VERT");
			}
             System.out.println("[OK] ditUnoAuMauvaisTour");
		}
		
	}
	@Nested
	class TroisiemeTest {
		
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
			
			partie.initPartie("../jeux_test/JeuTestCartePasser.csv", 3);
			partie.garderLesNPremieresCarteDeLaPioche(5);
			
			System.out.println("\n--------------------- Troisième Test ---------------------\n");
		}
		@Test
		void coupLegauxPasseTonTour() {
			//verifier qu'alice est le joueur courant
			if(!Partie.getJoueurCourant().equals(alice)) {
				fail("Alice n'est pas le joueur courant");
			}
			//alice pose le passe ton tour rouge
			try {
				alice.jouerUneCarte(partie, alice.getMaMain().getCarte(0));
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas poser sa carte");
			}
			//alice finit son tour
			try {
				alice.finirLeTour();
			}
			catch(JoueurException e) {
				fail("Le tour ne peut pas se terminer");
			}
			//verifie que charles est le joueur courant
			if (!Partie.getJoueurCourant().equals(charles))
				fail("Charles n'est pas le joueur courant");
			//verifier que la carte du tas est bien le "passe ton tour rouge"
			CartePasser passerRouge = (CartePasser) partie.getTas().getTop();
			if (passerRouge.getCouleur() != Couleur.ROUGE || partie.getValeurCourante() != -1)
				fail("La carte au sommet du tas n'est pas le passe ton tour rouge");
			
			//charles pose le passe ton tour vert
			try {
				charles.jouerUneCarte(partie, charles.getMaMain().getCarte(1));
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas poser sa carte");
			}
			//charles finit son tour
			try {
				charles.finirLeTour();
			}
			catch(JoueurException e) {
				fail("Le tour ne peut pas se terminer");
			}
			//verifier que bob est le joueur courant
			if(!Partie.getJoueurCourant().equals(bob)) {
				fail("Bob n'est pas le joueur courant");
			}
			//verifier que la carte du tas est bien le "passe ton tour vert"
			CartePasser passerVert = (CartePasser) partie.getTas().getTop();
			if (passerVert.getCouleur() != Couleur.VERT || passerVert.getClass() != partie.getTas().getTop().getClass())
				fail("La carte au sommet du tas n'est pas le passe ton tour vert");
			
			//bob pose le 6 vert
			try {
				bob.jouerUneCarte(partie, bob.getMaMain().getCarte(1));
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas poser sa carte");
			}
			//bob finit son tour
			try {
				bob.finirLeTour();
			}
			catch(JoueurException e) {
				fail("Le tour ne peut pas se terminer");
			}
			//verifier que charles est le joueur courant
			if(!Partie.getJoueurCourant().equals(charles)) {
				fail("Bob n'est pas le joueur courant");
			}
			//verifier que la carte au sommet du tas est le 6 vert
			 CarteChiffre sixVert = (CarteChiffre) partie.getTas().getTop();
            if (sixVert.getValeur() != 6 || sixVert.getCouleur() != Couleur.VERT)
                fail("La carte au sommet du tas n'est pas le 6 VERT");
            
            System.out.println("[OK] coupLegauxPasseTonTour");
		}
		@Test
		void carteSimpleIllegalSurPasseTonTour() {
			//verifier qu'alice est le joueur courant
			if(!Partie.getJoueurCourant().equals(alice)) {
				fail("Alice n'est pas le joueur courant");
			}
			//alice pose le passe ton tour rouge
			try {
				alice.jouerUneCarte(partie, alice.getMaMain().getCarte(0));
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas poser sa carte");
			}
			//alice finit son tour
			try {
				alice.finirLeTour();
			}
			catch(JoueurException e) {
				fail("Le tour ne peut pas se terminer");
			}
			
			//verifier que charles est le joueur courant
			if(!Partie.getJoueurCourant().equals(charles)) {
				fail("Charles n'est pas le joueur courant");
			}
			//verifier que charles possède 3 cartes
			if(charles.getMaMain().getNbCarte() != 3) {
				fail("Charle ne possède pas 3 cartes");
			}
			//charles pose le 1 bleu
			try {
				charles.jouerUneCarte(partie, charles.getMaMain().getCarte(0));
			}
			catch(JoueurException e) {
				if (charles.getNbCarte() != 3)
					fail("Charles ne possede pas 3 cartes");
			}
			//charles finit son tour
			try {
				charles.finirLeTour();
			}
			catch(JoueurException e) {
				fail("Le tour ne peut pas se terminer");
			}
			System.out.println("[OK] carteSimpleIllegalSurPasseTonTour");
		}
		
		@Test
		void passeTonTourIllegalCarteSimple() {
			//verifier qu'alice est le joueur courant
			if(!Partie.getJoueurCourant().equals(alice)) {
				fail("Alice n'est pas le joueur courant");
			}
			//alice pose le 9 bleu
			try {
				alice.jouerUneCarte(partie, alice.getMaMain().getCarte(1));
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas poser sa carte");
			}
			//alice finit son tour
			try {
				alice.finirLeTour();
			}
			catch(JoueurException e) {
				fail("Le tour ne peut pas se terminer");
			}
			//bob pose le 7 bleu
			try {
				bob.jouerUneCarte(partie, bob.getMaMain().getCarte(2));
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas poser sa carte");
			}
			//bob finit son tour
			try {
				bob.finirLeTour();
			}
			catch(JoueurException e) {
				fail("Le tour ne peut pas se terminer");
			}
			
			//verifier que charles est le joueur courant
			if(!Partie.getJoueurCourant().equals(charles)) {
				fail("Charles n'est pas le joueur courant");
			}
			//verifier que charles possède 3 cartes
			if(charles.getMaMain().getNbCarte() != 3) {
				fail("Charle ne possède pas 3 cartes");
			}
			//charles pose le passe ton tour vert
			try {
				charles.jouerUneCarte(partie, charles.getMaMain().getCarte(1));
			}
			catch(JoueurException e) {
				if(charles.getNbCarte() != 3) {
					fail("Charle ne possède pas 3 cartes");
				}
			}
			//charles finit son tour
			try {
				charles.finirLeTour();
			}
			catch(JoueurException e) {
				fail("Le tour ne peut pas se terminer");
			}
			System.out.println("[OK] passeTonTourIllegalCarteSimple");
		}
	}
	@Nested
	class QuatriemeTest {
		
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
			
			partie.initPartie("../jeux_test/JeuTestCarteChangementSens.csv", 3);
			partie.garderLesNPremieresCarteDeLaPioche(5);
			System.out.println("\n--------------------- Quatrième Test ---------------------\n");
		}
		@Test
		void coupLegauxChangementSens() {
			//verifier qu'alice est le joueur courant
			if(!Partie.getJoueurCourant().equals(alice)) {
				fail("Alice n'est pas le joueur courant");
			}
			//alice pose le passe ton tour rouge
			try {
				alice.jouerUneCarte(partie, alice.getMaMain().getCarte(0));
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas poser sa carte");
			}
			//alice finit son tour
			try {
				alice.finirLeTour();
			}
			catch(JoueurException e) {
				fail("Le tour ne peut pas se terminer");
			}
			//verifier que la carte du tas est bien le "changement de sens rouge"
			CarteChangement sensRouge = (CarteChangement) partie.getTas().getTop();
			if (sensRouge.getCouleur() != Couleur.ROUGE && sensRouge.getClass() != partie.getTas().getTop().getClass())
				fail("La carte au sommet du tas n'est pas le changement de sens rouge");
			
			//charles pose le changement de sens vert
			try {
				charles.jouerUneCarte(partie, charles.getMaMain().getCarte(1));
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas poser sa carte");
			}
			//charles finit son tour
			try {
				charles.finirLeTour();
			}
			catch(JoueurException e) {
				fail("Le tour ne peut pas se terminer");
			}
			//verifier que alice est le joueur courant
			if(!Partie.getJoueurCourant().equals(alice)) {
				fail("alice n'est pas le joueur courant");
			}
			//verifier que la carte du tas est bien le "changement de sens vert"
			CarteChangement sensVert = (CarteChangement) partie.getTas().getTop();
			if (sensVert.getCouleur() != Couleur.VERT && sensVert.getClass() != partie.getTas().getTop().getClass())
				fail("La carte au sommet du tas n'est pas le changement de sens vert");
			
            
            System.out.println("[OK] coupLegauxChangementDeSens");
		}
		@Test
		void carteSimpleIllegaleSurChangementDeSens() {
			//verifier qu'alice est le joueur courant
			if(!Partie.getJoueurCourant().equals(alice)) {
				fail("Alice n'est pas le joueur courant");
			}
			//alice pose le changement de sens rouge
			try {
				alice.jouerUneCarte(partie, alice.getMaMain().getCarte(0));
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas poser sa carte");
			}
			//alice finit son tour
			try {
				alice.finirLeTour();
			}
			catch(JoueurException e) {
				fail("Le tour ne peut pas se terminer");
			}
			
			//verifier que charles est le joueur courant
			if(!Partie.getJoueurCourant().equals(charles)) {
				fail("Charles n'est pas le joueur courant");
			}
			//verifier que charles possède 3 cartes
			if(charles.getMaMain().getNbCarte() != 3) {
				fail("Charle ne possède pas 3 cartes");
			}
			//charles pose le 1 bleu
			try {
				charles.jouerUneCarte(partie, charles.getMaMain().getCarte(0));
			}
			catch(JoueurException e) {
				//verifier dans l'exception approprié que charles possède toujours 3 cartes
				if(charles.getNbCarte() != 3) {
					fail("Charle ne possède pas 3 cartes");
				}
			}
			//charles finit son tour
			try {
				charles.finirLeTour();
			}
			catch(JoueurException e) {
				fail("Le tour ne peut pas se terminer");
			}
			 System.out.println("[OK] carteSimpleIllegalSurChangementDeSens");
		}
		
		@Test
		void changementDeSensIllegalCarteSimple() {
			//verifier qu'alice est le joueur courant
			if(!Partie.getJoueurCourant().equals(alice)) {
				fail("Alice n'est pas le joueur courant");
			}
			//alice pose 9 bleu
			try {
				alice.jouerUneCarte(partie, alice.getMaMain().getCarte(1));
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas poser sa carte");
			}
			//alice finit son tour
			try {
				alice.finirLeTour();
			}
			catch(JoueurException e) {
				fail("Le tour ne peut pas se terminer");
			}
			//bob pose le 7 bleu
			try {
				bob.jouerUneCarte(partie, bob.getMaMain().getCarte(2));
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas poser sa carte");
			}
			//bob finit son tour
			try {
				bob.finirLeTour();
			}
			catch(JoueurException e) {
				fail("Le tour ne peut pas se terminer");
			}
			
			//verifier que charles est le joueur courant
			if(!Partie.getJoueurCourant().equals(charles)) {
				fail("Charles n'est pas le joueur courant");
			}
			//verifier que charles possède 3 cartes
			if(charles.getNbCarte() != 3) {
				fail("Charle ne possède pas 3 cartes");
			}
			//charles pose le changement de sens vert
			try {
				charles.jouerUneCarte(partie, charles.getMaMain().getCarte(1));
			}
			catch(JoueurException e) {
				//verifier dans l'exception approprié que charles possède toujours 3 cartes
				if(charles.getNbCarte() != 3) {
					fail("Charle ne possède pas 3 cartes");
				}
			}
			//charles finit son tour
			try {
				charles.finirLeTour();
			}
			catch(JoueurException e) {
				fail("Le tour ne peut pas se terminer");
			}
			System.out.println("[OK] changementDeSensIllegalCarteSimple");
		}
	}
	
	@Nested
	class CinquiemeTest {
		
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
			
			partie.initPartie("../jeux_test/JeuTestCartePlus2.csv", 3);
			partie.garderLesNPremieresCarteDeLaPioche(5);
			System.out.println("\n--------------------- Cinquième Test ---------------------\n");
		}
		@Test
		void coupLegalAvecCartePlus2() {
			//verifier que le joueur courant est alice
			if(!Partie.getJoueurCourant().equals(alice)) {
				fail("Le joueur courant n'est pas alice");
			}
			//alice pose +2 vert
			try {
				alice.jouerUneCarte(partie, alice.getMaMain().getCarte(0));
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas poser sa carte");
			}
			//alice finit son tour
			try {
				alice.finirLeTour();
			}
			catch (JoueurException e) {
				fail("Le tour n'a pas pu se terminer");
			}
			//verifier que le joueur courant est bob
			if(!Partie.getJoueurCourant().equals(bob)) {
				fail("Le joueur courant n'est pas bob");
			}
			//verifier que bob possède 3 cartes
			if(bob.getNbCarte() != 3) {
				fail("bob ne possède pas 3 cartes");
			}
			/*bob doit encaisser le +2 */
			bob.encaisserCumul(partie);
			//verifier que bob possède 5 cartes
			if(bob.getNbCarte() != 5) {
				fail("bob ne possède pas 5 cartes");
			}
			
			//verifier que le joueur courant est charles
			if(!Partie.getJoueurCourant().equals(charles)) {
				fail("Le joueur courant n'est pas charles");
			}
			//charles pose le 1 vert
			try {
				charles.jouerUneCarte(partie, charles.getMaMain().getCarte(2));
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas poser sa carte");
			}
			//charles finit son tour
			try {
				charles.finirLeTour();
			}
			catch (JoueurException e) {
				fail("Le tour n'a pas pu se terminer");
			}
			//verifier que charles possède 2 cartes
			if(charles.getNbCarte() != 2) {
				fail("charles ne possède pas 2 cartes");
			}
			System.out.println("[OK] coupLegalAvecCartePlus2");
		}
		@Test
		void coupLegalAvecCumulCartePlus2() {
			//verifier que alice est le joueur courant
			if(!Partie.getJoueurCourant().equals(alice)) {
				fail("Alice n'est pas le joueur courant");
			}
			//alice pioche une carte
			try{
				alice.ajouterListeDeCarte(partie.getPioche().piocher(partie,alice,1));
			} catch (PiocheException e) {
			fail("Le joueur ne pas piocher");
			}
			//alice finit son tour
			try {
				alice.finirLeTour();
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas finir son tour");
			}
			//verifier que bob est le joueur courant
			if(!Partie.getJoueurCourant().equals(bob))
				fail("Le joueur courant n'est pas bob");
			//bob pioche une carte
			try{
				bob.ajouterListeDeCarte(partie.getPioche().piocher(partie,bob,1));
			} catch (PiocheException e) {
				fail("Le joueur ne pas piocher");
			}
			//bob finit son tour
			try {
				bob.finirLeTour();
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas finir son tour");
			}
			//verifier que charles est le joueur courant
			if(!Partie.getJoueurCourant().equals(charles))
				fail("Le joueur courant n'est pas charles");
			//charles pose le +2 vert
			try {
				charles.jouerUneCarte(partie, charles.getMaMain().getCarte(1));
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas poser sa carte");
			}
			//charles finit son tour
			try {
				charles.finirLeTour();
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas finir son tour");
			}
			//verifier que alice est le joueur courant
			if(!Partie.getJoueurCourant().equals(alice)) {
				fail("Alice n'est pas le joueur courant");
			}
			//alice pose le +2 vert
			try {
				alice.jouerUneCarte(partie, alice.getMaMain().getCarte(0));
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas poser sa carte");
			}
			//alice finit son tour
			try {
				alice.finirLeTour();
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas finir son tour");
			}
			//verifier que bob est le joueur courant
			if(!Partie.getJoueurCourant().equals(bob))
				fail("Le joueur courant n'est pas bob");
			//verifier que bob possede 4 cartes
			if(bob.getMaMain().getNbCarte() != 4)
				fail("bob ne possede pas 4 cartes");
			//bob encaisse l'attaque (donc pioche 4 cartes et finit son tour automatiquement)
			bob.encaisserCumul(partie);
			//verifier que bob possede 8 cartes
			if(bob.getMaMain().getNbCarte() != 8)
				fail("bob ne possede pas 8 cartes");
			//verifier que charles est le joueur courant
			if(!Partie.getJoueurCourant().equals(charles))
				fail("Le joueur courant n'est pas charles");
			System.out.println("[OK] coupLegalAvecCumulCartePlus2");
		}
	}
	
	@Nested
	class SixiemeTest {
		
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
			
			partie.initPartie("../jeux_test/JeuTestCartePlus4.csv", 3);
			partie.garderLesNPremieresCarteDeLaPioche(10);
			
			System.out.println("\n--------------------- Sixième Test ---------------------\n");
		}
		@Test
		void coupLegalAvecCartePlus4() {
			//verifier que le joueur courant est alice
			if(!Partie.getJoueurCourant().equals(alice)) {
				fail("Le joueur courant n'est pas alice");
			}
			//alice pose +4 et choisit la couleur verte (-> ????)
			try {
				alice.jouerUneCarte(partie, alice.getMaMain().getCarte(0));
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas poser sa carte");
			}
			//alice finit son tour
			try {
				alice.finirLeTour();
			}
			catch (JoueurException e) {
				fail("Le tour n'a pas pu se terminer");
			}
			//verifier que le joueur courant est charles
			if(!Partie.getJoueurCourant().equals(charles)) {
				fail("Le joueur courant n'est pas charles");
			}
			/*bob doit encaisser le +4 automatiquement */
			//verifier que bob possède 7 cartes
			if(bob.getMaMain().getNbCarte() != 7) {
				fail("bob ne possède pas 7 cartes");
			}
			//verifier que le joueur courant est charles
			if(!Partie.getJoueurCourant().equals(charles)) {
				fail("Le joueur courant n'est pas charles");
			}
			//charles pose le 1 vert
			try {
				charles.jouerUneCarte(partie, charles.getMaMain().getCarte(2));
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas poser sa carte");
			}
			//charles finit son tour
			try {
				charles.finirLeTour();
			}
			catch (JoueurException e) {
				fail("Le tour n'a pas pu se terminer");
			}
			//verifier que charles possède 2 cartes
			if(charles.getMaMain().getNbCarte() != 2) {
				fail("charles ne possède pas 2 cartes");
			}
			System.out.println("[OK] coupLegalAvecCartePlus4");
		}
	}
	@Nested
	class SeptiemeTest {
		
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
			
			partie.initPartie("../jeux_test/JeuTestCarteChangementCouleur.csv", 3);
			partie.garderLesNPremieresCarteDeLaPioche(5);
			
			System.out.println("\n--------------------- Septième Test ---------------------\n");
		}
		@Test
		void coupLegalAvecCarteChangementCouleur() {
			
			//verifier que le joueur courant est alice
			if(!Partie.getJoueurCourant().equals(alice)) {
				fail("Le joueur courant n'est pas alice");
			}
			//alice pose la carte changement couleur et choisit la couleur vert
			try {
				alice.jouerUneCarte(partie, alice.getMaMain().getCarte(0));
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas poser sa carte");
			}
			//alice finit son tour
			try {
				alice.finirLeTour();
			}
			catch (JoueurException e) {
				fail("Le tour n'a pas pu se terminer");
			}
			
			//verifier que le joueur courant est bob
			if(!Partie.getJoueurCourant().equals(bob)) {
				fail("Le joueur courant n'est pas bob");
			}
			
			//bob pose le 6 vert
			try {
				bob.jouerUneCarte(partie, bob.getMaMain().getCarte(1));
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas poser sa carte");
			}
			//bob finit son tour
			try {
				bob.finirLeTour();
			}
			catch (JoueurException e) {
				fail("Le tour n'a pas pu se terminer");
			}
			//charles pose le 1 vert
			
			try {
				charles.jouerUneCarte(partie, charles.getMaMain().getCarte(2));
			}
			catch(JoueurException e) {
				fail("Le joueur ne peut pas poser sa carte");
			}
			//charles finit son tour
			try {
				charles.finirLeTour();
			}
			catch (JoueurException e) {
				fail("Le tour n'a pas pu se terminer");
			}
			
			System.out.println("[OK] coupLegalAvecCarteChangementCouleur");
		}
		
	}
}

