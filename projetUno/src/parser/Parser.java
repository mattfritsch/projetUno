package parser;

import exception.ParserManquantException;
import pioche.Pioche;

public abstract class Parser{
	private Parser suivant = null;
	
	public Parser(Parser suivant) {
		this.suivant = suivant;
	}
	
	public void traiter(String ligne, Pioche pioche) throws Exception{
		if(saitParser(ligne))
			parser(ligne, pioche);
		else if(aUnSuivant())
			getSuivant().traiter(ligne, pioche);
		else
			throw new ParserManquantException();
	}
	
	private Parser getSuivant() {
		return suivant;
	}
	
	private boolean aUnSuivant() {
		return suivant != null;
	}
	
	public abstract void parser(String ligne, Pioche pioche) throws Exception;
	
	public abstract boolean saitParser(String ligne);
	
}
