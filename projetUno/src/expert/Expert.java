package expert;

import carte.Carte;
import exception.ParserManquantException;
import joueur.Joueur;
import partie.Partie;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

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
	
	/**
	 * Creation d'un expert
	 * @param suivant Expert
	 */
	public Expert(Expert suivant) {
		this.suivant = suivant;
	}
	//on doit vérifier que c'est le joueur courant dans tous les tests
	/**
	 * Methode qui permet de traiter une carte placer par un joueur dans une partie
	 * @param partie Partie
	 * @param carte Carte
	 * @param joueur Joueur
	 * @throws Exception Exception
	 */
	public void traiter(Partie partie, Carte carte, Joueur joueur) throws Exception{
		if(saitExaminer(carte))
			examiner(partie,carte, joueur);
		else if(aUnSuivant())
			getSuivant().traiter(partie,carte, joueur);
		else
			throw new ParserManquantException();
	}
	
	/**
	 * Retourne le suivant de l'expert
	 * @return Expert
	 */
	private Expert getSuivant() {
		return suivant;
	}
	
	/**
	 * Retourne si l'expert a un suivant
	 * @return boolean
	 */
	private boolean aUnSuivant() {
		return suivant != null;
	}
	
	/**
	 * Methode qui examine une carte placer par un joueur dans une partie
	 * @param partie Partie
	 * @param carte Carte
	 * @param joueur Joueur
	 * @return boolean
	 * @throws Exception Exception
	 */
	public abstract boolean examiner(Partie partie, Carte carte, Joueur joueur) throws Exception;
	
	/**
	 * Retourne si un expert sait examiner une situation
	 * @param carte Carte
	 * @return boolean
	 */
	public abstract boolean saitExaminer(Carte carte);
	
}
