package parser;

import carte.Carte.Couleur;
import carte.CarteChangement;

public class ParserChangement extends Parser{
	
	public ParserChangement(Parser suivant) {
		super(suivant);
	}
	
	@Override
	public void parser(String ligne) throws Exception{
		String tab[] = ligne.split(";");
		Couleur couleur = partie.convertStringToCouleur(tab[1]);
		CarteChangement carteChangement = new CarteChangement(couleur);
		
		System.out.println(carteChangement);
	}
	
	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CarteChangement");
	}
}
