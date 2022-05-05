package parser;

public class ParserCarteSimple extends Parser{
	public ParserCarteSimple(Parser suivant) {
		super(suivant);
	}
	
	@Override
	public void parser(String ligne) throws Exception{
		String tab[] = ligne.split(";");
		
	}
	
	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CarteSimple");
	}
}
