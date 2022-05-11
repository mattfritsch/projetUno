package parser;

import carte.Carte.Couleur;
import carte.CarteChiffre;

public class ParserCarteSimple extends Parser{
	public ParserCarteSimple(Parser suivant) {
		super(suivant);
	}
	
	@Override
	public void parser(String ligne) throws Exception{
		String tab[] = ligne.split(";");
		int numCarte = Integer.parseInt(tab[2]);
		Couleur couleur = partie.Partie.convertStringToCouleur(tab[1]);
		CarteChiffre carte = new CarteChiffre(numCarte, couleur);
		
		System.out.println(carte);		
	}
	
	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CarteSimple");
	}
}
