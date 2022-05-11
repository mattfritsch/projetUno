package pioche;

import java.util.ArrayList;

import carte.Carte;
import exception.PiocheException;

public class Pioche {

	public ArrayList<Carte> pioche;

	public Pioche() {

	}

	public void addCarte(Carte carte) {
		pioche.add(carte);
	}

	public void removeCarte(int index) {
		pioche.remove(index);
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
					removeCarte(getSize() - 1);
				}
			}
			else {
				throw new PiocheException("TODO");
			}
			
			return cartesPiochees;
		}
}
