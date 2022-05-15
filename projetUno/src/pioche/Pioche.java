package pioche;

import java.util.ArrayList;
import java.util.Objects;

import carte.Carte;
import carte.CarteChangement;
import exception.PiocheException;
import partie.Partie;

public class Pioche {

	private ArrayList<Carte> pioche = new ArrayList<Carte>();

	public Pioche() {
		
	}

	public ArrayList<Carte> getPioche() {
		return pioche;
	}
	
	public void setPioche(ArrayList<Carte> pioche) {
		this.pioche = pioche;
	}
	
	public boolean addCarte(Carte carte) {
		return pioche.add(carte);
	}

	public boolean removeCarte(Carte carte) {
		return pioche.remove(carte);
	}
	
	public Carte removeCarte(int index) {
		return pioche.remove(index);
	}

	public int getNbCartes() {
		return pioche.size();
	}

	public Carte getBottom() throws PiocheException {
		if (pioche.size() > 0) {
			return pioche.get(0);
		} else {
			throw new PiocheException("Pioche vide");
		}
	}
	
	public Carte getTop() {
		if (getNbCartes() > 0) {
			return pioche.get(getNbCartes()-1);
		} else {
			return null;
		}
	}

	public ArrayList<Carte> piocher(int nbCartes) throws PiocheException{
		ArrayList<Carte> cartesPiochees = new ArrayList<Carte>();
		if (nbCartes<=getNbCartes()){
			 {
				for (int i=0; i<nbCartes; i++) {
					cartesPiochees.add(getBottom());
					this.removeCarte(getBottom());
				}
			}
		}
		else {
			throw new PiocheException("Nombre de cartes insuffisant dans la pioche");
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
