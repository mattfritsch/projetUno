package parser;

import carte.CarteJoker;
import pioche.Pioche;

public class ParserCarteJoker extends Parser{
	public ParserCarteJoker(Parser suivant) {
		super(suivant);
	}
	
	@Override
	public void parser(String ligne) throws Exception{
		String tab[] = ligne.split(";");
		CarteJoker carteJoker = new CarteJoker();
		//Pioche.pioche.add(carteJoker);
		
		//System.out.println(Pioche.pioche);
	}
	
	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CarteJoker");
	}

}
