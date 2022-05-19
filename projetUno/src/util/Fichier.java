package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import exception.FichierException;
import exception.ParserManquantException;
import parser.Parser;
import parser.ParserCarteChangement;
import parser.ParserCarteJoker;
import parser.ParserCartePasser;
import parser.ParserCartePlusDeux;
import parser.ParserCartePlusQuatre;
import parser.ParserCarteSimple;
import pioche.Pioche;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

public class Fichier {
	/**
	 * Methode qui lit un fichier et ajoute les cartes dans la pioche
	 * @param nomFichier String
	 * @param pioche Pioche
	 * @throws FichierException FichierException
	 */
	public static void lire (String nomFichier, Pioche pioche) throws FichierException {
		
		Parser sixiemeParser = new ParserCarteJoker(null);
		Parser cinquiemeParser = new ParserCartePlusQuatre(sixiemeParser);
		Parser quatriemeParser = new ParserCartePlusDeux(cinquiemeParser);
		Parser troisiemeParser = new ParserCarteChangement(quatriemeParser);
		Parser deuxiemeParser = new ParserCartePasser(troisiemeParser);
		Parser parser = new ParserCarteSimple(deuxiemeParser);
		
		if(nomFichier == null) {
			throw new FichierException("Le nom du fichier ne peut pas être null");
		}
		
		File fichier = new File(nomFichier);
		
		if(!fichier.isFile()) {
			throw new FichierException("Le fichier doit être un fichier");
		}
		
		BufferedReader reader = null;
		String ligne;
		
		try {
			reader = new BufferedReader(new FileReader(fichier));
			
			while((ligne = reader.readLine()) != null) {
				if(parser != null) {
					try {
						parser.traiter(ligne, pioche);
					}
					catch(ParserManquantException e) {
						System.err.println("Aucun parser n'existe pour la ligne "+ligne);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
				else {
					System.out.println("Parser null ligne : "+ligne);
				}
			}
			reader.close();
		}
			
		catch(IOException e) {
			e.printStackTrace();
		}
		}
	}

