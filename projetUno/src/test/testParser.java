package test;

import exception.FichierException;
import parser.Parser;
import parser.ParserCarteSimple;
import parser.ParserCarteChangement;
import parser.ParserCarteJoker;
import parser.ParserCartePasser;
import parser.ParserCartePlusDeux;
import parser.ParserCartePlusQuatre;
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
			cinquiemeParser = new ParserCartePlusQuatre(sixiemeParser);
			quatriemeParser = new ParserCartePlusDeux(cinquiemeParser);
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
