package parser;

import carte.CarteJoker;
import pioche.Pioche;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */


public class ParserCarteJoker extends Parser{
	/**
	 * Creation d'un parser ParserCarteJoker
	 * @param suivant Parser
	 */
	public ParserCarteJoker(Parser suivant) {
		super(suivant);
	}
	
	@Override
	public void parser(String ligne, Pioche pioche) throws Exception{
		String tab[] = ligne.split(";");
		CarteJoker carteJoker = new CarteJoker();
		pioche.addCarte(carteJoker);
		
		//System.out.println(Pioche.pioche);
	}
	
	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CarteJoker");
	}

}
