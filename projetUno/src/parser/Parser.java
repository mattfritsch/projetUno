package parser;

import exception.ParserManquantException;
import pioche.Pioche;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

public abstract class Parser{
	private Parser suivant = null;
	
	/**
	 * Creation d'un parser
	 * @param suivant Parser
	 */
	public Parser(Parser suivant) {
		this.suivant = suivant;
	}
	
	/**
	 * Methode qui traite une ligne et ajoute dans la pioche
	 * @param ligne String
	 * @param pioche Pioche
	 * @throws Exception Exception
	 */
	public void traiter(String ligne, Pioche pioche) throws Exception{
		if(saitParser(ligne))
			parser(ligne, pioche);
		else if(aUnSuivant())
			getSuivant().traiter(ligne, pioche);
		else
			throw new ParserManquantException();
	}
	
	/**
	 * Retourne le parser qui suit
	 * @return Parser
	 */
	private Parser getSuivant() {
		return suivant;
	}
	
	/**
	 * Retourne si un parser a un suivant
	 * @return boolean
	 */
	private boolean aUnSuivant() {
		return suivant != null;
	}
	
	/**
	 * Methode pour parser
	 * @param ligne String
	 * @param pioche Pioche
	 * @throws Exception Exception
	 */
	public abstract void parser(String ligne, Pioche pioche) throws Exception;
	
	/**
	 * Methode qui verifie si un parser sait parser une ligne
	 * @param ligne String
	 * @return boolean
	 */
	public abstract boolean saitParser(String ligne);
	
}
