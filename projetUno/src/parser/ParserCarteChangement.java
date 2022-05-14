package parser;

import carte.Carte.Couleur;
import carte.CarteChangement;
import pioche.Pioche;

public class ParserCarteChangement extends Parser{
	
	public ParserCarteChangement(Parser suivant) {
		super(suivant);
	}
	
	@Override
	public void parser(String ligne) throws Exception{
		String tab[] = ligne.split(";");
		Couleur couleur = partie.Partie.convertStringToCouleur(tab[1]);
		CarteChangement carteChangement = new CarteChangement(couleur);
		//Pioche.pioche.add(carteChangement);
		
		//System.out.println(Pioche.pioche);
	}
	
	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CarteChangement");
	}
}
