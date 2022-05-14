package pioche;

import java.util.ArrayList;
import java.util.Objects;

import carte.Carte;
import carte.CarteChangement;
import exception.PiocheException;

public class Pioche {

	public static ArrayList<Carte> pioche = new ArrayList<Carte>();

	public Pioche() {
	}

	public boolean addCarte(Carte carte) {
		return pioche.add(carte);
	}

	public boolean removeCarte(Carte carte) {
		return pioche.remove(carte);
	}

	public int getSize() {
		return pioche.size();
	}

	public Carte getTop() throws PiocheException {
		if (pioche.size() > 0) {
			return pioche.get(getSize() - 1);
		} else {
			throw new PiocheException("Pioche vide");
		}
	}

	public ArrayList<Carte> piocher(int nbCartes) throws PiocheException{
		ArrayList<Carte> cartesPiochees = new ArrayList<Carte>();
		if (nbCartes<=getSize()){
			for (int i=0; i<nbCartes; i++) {
				Carte carte = getTop();
				cartesPiochees.add(carte);
				this.removeCarte(this.getTop());
			}
		}
		else {
			throw new PiocheException("TODO");
		}
			
		return cartesPiochees;
	}
	
	@Override
	public String toString() {
		String ch = "Pioche [";
		for (Carte carte : pioche) {
			ch+=carte.toString();
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
