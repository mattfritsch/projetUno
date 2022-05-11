package expert;

import carte.Carte;
import exception.ParserManquantException;
import joueur.Joueur;

public abstract class Expert {
	/*
	 * Fais un parcours du tas pour arriver à la dernière carte posée, est fait à chaque tour
	 * 
	 * Chaque expert va vérifier si le joueur peut poser sa carte
	 * 
	 * ExpertEffetEffet : le joueur peut poser ou non sa carte à effet sur une autre carte à effet
	 * ExpertEffetSimple : le joueur peut poser ou non sa carte à effet sur une carte simple
	 * ExpertSimpleEffet : le joeuur peut poser ou non sa carte simple sur une carte à effet
	 * ExpertSimpleSimple : le joueur peut poser ou non sa carte simple sur une autre carte simple
	 * 
	 * */
	
	private Expert suivant = null;
	
	public Expert(Expert suivant) {
		this.suivant = suivant;
	}
	//on doit vérifier que c'est le joueur courant dans tous les tests
	public void traiter(Carte carte, Joueur joueur) throws Exception{
		if(saitExaminer(carte))
			examiner(carte, joueur);
		else if(aUnSuivant())
			getSuivant().traiter(carte, joueur);
		else
			throw new ParserManquantException();
	}
	
	private Expert getSuivant() {
		return suivant;
	}
	
	private boolean aUnSuivant() {
		return suivant != null;
	}
	
	public abstract boolean examiner(Carte carte, Joueur joueur) throws Exception;
	
	public abstract boolean saitExaminer(Carte carte);
	
}
