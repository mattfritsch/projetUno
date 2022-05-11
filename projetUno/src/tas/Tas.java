package tas;

import java.util.Arraylist;
import java.util.Collections;
import exception.TasException;

public class Tas {
	
	private:
		
		private ArrayList<Carte> tas;
	
		public Tas () {
			
		}
		
		
		public void addCarte(Carte carte) {
			tas.add(carte);
		}
		
		##Possiblement a changer (par index ou par carte)
		public void removeCarte(int index) {
			tas.remove(index);
		}
		
		public int getSize() {
			return size(tas);
		}
		
		public Carte getTop() {
			if (tas.getSize()>0) {
				return tas[tas.getSize()-1];
			}
			else {
				return null;
			}
		}
		
		public Pioche melangerTas() {
			Carte derniereCarte = tas.getTop();
			tas.removeCarte(derniereCarte);
			Collections.shuffle(tas);
			Pioche pioche = (Pioche) tas;
			for (Carte carte: tas) {
				tas.removeCarte(tas.getTop());
			}
			tas.addCarte(derniereCarte);
			return pioche;
		}
}
