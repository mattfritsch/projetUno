package parser;

public class ParserPasser extends Parser {
	public ParserPasser(Parser suivant) {
		super(suivant);
	}
	
	@Override
	public void parser(String ligne) throws Exception{
		String tab[] = ligne.split(";");
		
	}
	
	@Override
	public boolean saitParser(String ligne) {
		return ligne.contains("CartePasser");
	}
}
