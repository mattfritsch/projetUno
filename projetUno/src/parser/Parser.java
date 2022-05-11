package parser;

import exception.ParserManquantException;

public abstract class Parser{
	private Parser suivant = null;
	
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
