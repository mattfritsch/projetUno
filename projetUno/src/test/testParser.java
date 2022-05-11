package test;

import exception.FichierException;
import parser.Parser;
import parser.ParserCarteSimple;
import parser.ParserCarteChangement;
import parser.ParserCarteJoker;
import parser.ParserCartePasser;
import parser.ParserCartePlus2;
import parser.ParserCartePlus4;
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
			
			sixiemeParser = new ParserCarteJoker(null);
			cinquiemeParser = new ParserCartePlus4(sixiemeParser);
			quatriemeParser = new ParserCartePlus2(cinquiemeParser);
			troisiemeParser = new ParserCarteChangement(quatriemeParser);
			deuxiemeParser = new ParserCartePasser(troisiemeParser);
			premierParser = new ParserCarteSimple(deuxiemeParser);
			
			
			Fichier.lire(nomFichier, premierParser);
		}
		catch(FichierException e){
			System.err.println(e.getMessage());
		}
	}
}
