package parser;

import carte.CarteJoker;
import carte.Carte.Couleur;

public class ParserCarteJoker extends Parser{
	public ParserCarteJoker(Parser suivant) {
		super(suivant);
	}
	
	@Override
	public void parser(String ligne) throws Exception{
		String tab[] = ligne.split(";");
		CarteJoker carteJoker = new CarteJoker();
		
		System.out.println(carteJoker);
	}
	
	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CarteJoker");
	}

}
