package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import exception.FichierException;
import exception.ParserManquantException;
import parser.Parser;

public class Fichier {
	public static void lire (String nomFichier, Parser parser) throws FichierException {
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
						parser.traiter(ligne);
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

