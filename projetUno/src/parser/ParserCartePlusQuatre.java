package parser;

import carte.CartePlusQuatre;
import pioche.Pioche;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */


public class ParserCartePlusQuatre extends Parser{
	/**
	 * Creation d'un parser ParserCartePlusQuatre
	 * @param suivant Parser
	 */
	public ParserCartePlusQuatre(Parser suivant) {
		super(suivant);
	}
	
	@Override
	public void parser(String ligne, Pioche pioche) throws Exception{
		String tab[] = ligne.split(";");
		CartePlusQuatre cartePlusQuatre = new CartePlusQuatre();
		pioche.addCarte(cartePlusQuatre);
		
	}
	
	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CartePlus4");
	}

}
