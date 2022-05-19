package pioche;

import java.util.ArrayList;
import java.util.Objects;

import carte.Carte;
import carte.CarteChangement;
import exception.PiocheException;
import joueur.Joueur;
import partie.Partie;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

public class Pioche {

	private ArrayList<Carte> pioche = new ArrayList<Carte>();

	/**
	 * Creation d'une pioche
	 */
	public Pioche() {
		
	}

	/**
	 * Retourne la liste de carte
	 * @return ArrayList
	 */
	public ArrayList<Carte> getPioche() {
		return pioche;
	}
	
	/**
	 * Redefini la liste de carte
	 * @param pioche ArrayList
	 */
	public void setPioche(ArrayList<Carte> pioche) {
		this.pioche = pioche;
	}
	
	/**
	 * Ajoute une carte dans la pioche
	 * @param carte Carte
	 * @return boolean
	 */
	public boolean addCarte(Carte carte) {
		return pioche.add(carte);
	}

	/**
	 * Retire une carte dans la pioche
	 * @param carte Carte
	 * @return boolean
	 */
	public boolean removeCarte(Carte carte) {
		return pioche.remove(carte);
	}
	
	/**
	 * Retire une carte a l'index de la pioche
	 * @param index int
	 * @return Carte
	 */
	public Carte removeCarte(int index) {
		return pioche.remove(index);
	}

	/**
	 * Retourne le nombre de carte dans la pioche
	 * @return int
	 */
	public int getNbCartes() {
		return pioche.size();
	}

	/**
	 * Retourne la prochaine carte qui va etre piocher
	 * @return Carte 
	 * @throws PiocheException PiocheException
	 */
	public Carte getBottom() throws PiocheException {
		if (pioche.size() > 0) {
			return pioche.get(0);
		} else {
			throw new PiocheException("Pioche vide");
		}
	}

	/**
	 * Methode pour piocher un nombre de carte dans une partie
	 * @param partie Partie
	 * @param joueur Joueur peut etre null 
	 * @param nbCartes int
	 * @return ArrayList 
	 * @throws PiocheException PiocheException
	 */
	public ArrayList<Carte> piocher(Partie partie, Joueur joueur, int nbCartes) throws PiocheException{
		ArrayList<Carte> cartesPiochees = new ArrayList<Carte>();
		if ((partie.getJoueurCourant() == joueur) || (joueur == null)) {
			if (nbCartes<=getNbCartes()){
				for (int i=0; i<nbCartes; i++) {
					cartesPiochees.add(getBottom());
					this.removeCarte(getBottom());
				}
				if (partie.getJoueurCourant() == joueur) {
					joueur.setAJouer(true);
					partie.setVientDeJouer(joueur);
				}
				partie.calculerJoueurSuivant(0);
			}
			else {
				throw new PiocheException("Nombre de cartes insuffisant dans la pioche");
			}
			
			return cartesPiochees;
		}
		if(partie.getJoueurCourant() == partie.getVientDeJouer()) {
			throw new PiocheException("Le joueur courant a deja jouer, il n'est pas possible de piocher");
		}
		if(partie.getJoueurCourant() != joueur) {
			throw new PiocheException("Le joueur pioche alors que ce n'est pas son tour");
		}
		return cartesPiochees;
	}
	
	@Override
	public String toString() {
		String ch = "Pioche [\n";
		for (Carte carte : pioche) {
			ch+=carte.toString();
			ch+="\n";
		}
		return ch + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(pioche);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this==obj) return true;
		if (obj==null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Pioche other = (Pioche) obj;
		return pioche == other.pioche;
	}
}
