package test;

import carte.Carte.Couleur;
import carte.CarteChiffre;
import exception.CarteException;
import exception.FichierException;
import exception.PartieException;
import partie.Partie;
import util.Fichier;

public class testParser {
	public static void main(String[] args) throws FichierException, PartieException {
		try {
			String nomFichier = "../jeux_test/JeuTestComplet.csv";
			
			
			Fichier.lire(nomFichier);
			CarteChiffre carte = null;
			try {
				carte = new CarteChiffre(8,Couleur.BLEU);
			}catch (CarteException e) {
				e.getMessage();
			}
			
			Partie.getPioche().addCarte(carte);
			System.out.println(Partie.getPioche());
		}
		catch(FichierException e){
			System.err.println(e.getMessage());
		}
	}
}
