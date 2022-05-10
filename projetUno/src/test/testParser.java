package test;

import exception.FichierException;
import parser.Parser;
import parser.ParserCarteSimple;
import util.Fichier;

public class testParser {
	public static void main(String[] args) throws FichierException {
		try {
			String nomFichier = "../jeux_test/JeuTestCarteSimple.csv";
			
			Parser premierParser = null;
			premierParser = new ParserCarteSimple(premierParser);
			
			Fichier.lire(nomFichier, premierParser);
		}
		catch(FichierException e){
			System.err.println(e.getMessage());
		}
	}
}
