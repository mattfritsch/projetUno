package tas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import carte.*;
import exception.TasException;
import partie.Partie;
import pioche.Pioche;

/**
 * 
 * @author Aurelien FAGIOLI - Matthieu FRITSCH - Nathan GUSATTO
 */

public class Tas {

	private ArrayList<Carte> tas = new ArrayList<Carte>();

	/**
	 * Creation d'un tas
	 */
	public Tas() {

	}

	/**
	 * Ajoute une carte dans un tas
	 * @param carte Carte
	 * @return boolean
	 */
	public boolean addCarte(Carte carte) {
		return tas.add(carte);
	}
	
	/**
	 * Ajoute une liste de carte dans un tas
	 * @param cartes ArrayList
	 * @return boolean
	 */
	public boolean addListeDeCarte(ArrayList<Carte> cartes) {
		return tas.addAll(cartes);
	}

	/**
	 * Retire une carte du tas
	 * @param carte  Carte
	 * @return boolean
	 */
	public boolean removeCarte(Carte carte) {
		return tas.remove(carte);
	}

	/**
	 * Retourne le nombre de cartes dans le tas
	 * @return int
	 */
	public int getNbCartes() {
		return tas.size();
	}

	/**
	 * Retourne la carte qui est face montree dans le jeu
	 * @return Carte
	 */
	public Carte getTop() {
		if (getNbCartes() > 0) {
			return tas.get(getNbCartes()-1);
		} else {
			return null;
		}
	}

	/**
	 * Methode qui melange le tas
	 * @param partie Partie
	 * @return Pioche
	 */
	public Pioche melangerTas(Partie partie) {
		Carte derniereCarte = getTop();
		this.removeCarte(derniereCarte);
		Collections.shuffle(tas);
		
		Pioche pioche = new Pioche();
		for (Carte carte : tas) {
			pioche.addCarte(carte);
			this.removeCarte(carte); 
		}
		this.addCarte(derniereCarte);
		return pioche;
	}
	
	@Override
	public String toString() {
		String ch = "Tas [\n";
		for (Carte carte : tas) {
			ch+=carte.toString();
		}
		return ch + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(tas);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this==obj) return true;
		if (obj==null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Tas other = (Tas) obj;
		return tas == other.tas;
	}
}
