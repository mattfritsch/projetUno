package test;

import exception.FichierException;
import exception.PartieException;
import joueur.Joueur;
import partie.Partie;
import util.Fichier;

public class testParser {
	public static void main(String[] args) throws FichierException, PartieException {
		try {
			Joueur aurelien = new Joueur("Aurelien");
			Joueur matthieu = new Joueur("Matthieu");
			Partie partie = new Partie(aurelien,matthieu);
			String nomFichier = "../jeux_test/JeuTestComplet.csv";
			
			
			Fichier.lire(nomFichier, partie.getPioche());
			
			System.out.println(partie.getPioche());
		}
		catch(FichierException e){
			System.err.println(e.getMessage());
		}
	}
}
