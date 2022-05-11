package test;

import exception.FichierException;
import parser.Parser;
import parser.ParserCarteSimple;
import parser.ParserChangement;
import parser.ParserJoker;
import parser.ParserPasser;
import parser.ParserPlusDeux;
import parser.ParserPlusQuatre;
import util.Fichier;

public class testParser {
	public static void main(String[] args) throws FichierException {
		try {
			String nomFichier = "../jeux_test/JeuTestComplet.csv";
			
			Parser premierParser = null;
			Parser deuxiemeParser = null;
			Parser troisiemeParser = null;
			Parser quatriemeParser = null;
			Parser cinquiemeParser = null;
			Parser sixiemeParser = null;
			
			sixiemeParser = new ParserJoker(null);
			cinquiemeParser = new ParserPlusQuatre(sixiemeParser);
			quatriemeParser = new ParserPlusDeux(cinquiemeParser);
			troisiemeParser = new ParserChangement(quatriemeParser);
			deuxiemeParser = new ParserPasser(troisiemeParser);
			premierParser = new ParserCarteSimple(deuxiemeParser);
			
			
			Fichier.lire(nomFichier, premierParser);
		}
		catch(FichierException e){
			System.err.println(e.getMessage());
		}
	}
}
