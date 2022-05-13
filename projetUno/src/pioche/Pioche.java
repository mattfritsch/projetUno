package pioche;

import java.util.ArrayList;

import carte.Carte;
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
}
