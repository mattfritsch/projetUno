package parser;

import carte.Carte.Couleur;
import exception.ParserManquantException;

public abstract class Parser {
	private Parser suivant = null;
	
	public Couleur convertStringToCouleur(String s) {
		switch(s) {
		case "Bleu":
			return Couleur.BLEU;
		case "Vert":
			return Couleur.VERT;
		case "Rouge":
			return Couleur.ROUGE;
		case "Jaune":
			return Couleur.JAUNE;
		}
		return null;
	}
	
	public Parser(Parser suivant) {
		this.suivant = suivant;
	}
	
	public void traiter(String ligne) throws Exception{
		if(saitParser(ligne))
			parser(ligne);
		else if(aUnSuivant())
			getSuivant().traiter(ligne);
		else
			throw new ParserManquantException();
	}
	
	private Parser getSuivant() {
		return suivant;
	}
	
	private boolean aUnSuivant() {
		return suivant != null;
	}
	
	public abstract void parser(String ligne) throws Exception;
	
	public abstract boolean saitParser(String ligne);
	
}
