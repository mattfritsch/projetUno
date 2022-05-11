package test;

import exception.FichierException;
import parser.Parser;
import parser.ParserCarteSimple;
import parser.ParserPasser;
import parser.ParserPlusDeux;
import util.Fichier;

public class testParser {
	public static void main(String[] args) throws FichierException {
		try {
			String nomFichier = "../jeux_test/JeuTestCartePasser.csv";
			
			Parser premierParser = null;
			Parser deuxiemeParser = null;
			deuxiemeParser = new ParserCarteSimple(null);
			premierParser = new ParserPasser(deuxiemeParser);
			
			
			Fichier.lire(nomFichier, premierParser);
		}
		catch(FichierException e){
			System.err.println(e.getMessage());
		}
	}
}
