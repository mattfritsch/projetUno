package pioche;

import java.util.Arraylist;
import exception.PiocheException;

public class Pioche {
	
	private:
		
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
			return size(pioche);
		}
		
		public Carte getTop() throws PiocheException{
			if (pioche.getSize()>0) {
				return pioche[pioche.getSize()-1];
			}
			else {
				throw new PiocheException("Pioche vide");
			}
		}
		
		public ArrayList<Carte> piocher(int nbCartes) throws PiocheException{
			ArrayList<Carte> cartesPiochees;
			if (nbCartes<=pioche.getSize(){
				for (int i=0; i<nbCartes; i++) {
					Carte carte = pioche.getTop();
					cartesPiochees.add(carte);
					pioche.removeCarte(pioche.getSize()-1);
				}
			}
			else {
				throw new PiocheException("TODO");
			}
			
			return cartePiochees;
		}
}
