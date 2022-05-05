package parser;

public class ParserPlusDeux extends Parser{
	public ParserPlusDeux(Parser suivant) {
		super(suivant);
	}
	
	@Override
	public void parser(String ligne) throws Exception{
		String tab[] = ligne.split(";");
		
	}
	
	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CartePlus2");
	}

}
