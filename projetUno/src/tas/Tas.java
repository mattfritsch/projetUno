package tas;

import java.util.ArrayList;
import java.util.Collections;

import carte.Carte;
import exception.TasException;
import pioche.Pioche;

/*
 * Je sais pas si les primitives que tu as faites sont vraiment utile
 * -> addCarte, removeCarte, getSize, getTop... 
 * Car tu peux tout faire directement dans la fonction concernée
 * A toi de voir, car ça peut allourdir le code même si ça peut peut-être facilité sa compréhension
 * Même commentaire pour pioche
 * */

public class Tas {

	public ArrayList<Carte> tas;

	public Tas() {

	}

	public void addCarte(Carte carte) {
		tas.add(carte);
	}

	/*
	 * Possiblement a ?
	 * Changer par index ou par carte
	 * */

	public void removeCarte(int index) {
		tas.remove(index);
	}

	public int getSize() {
		return tas.size();
	}

	public Carte getTop() {
		if (getSize() > 0) {
			return tas.get(getSize()-1);
		} else {
			return null;
		}
	}

	/*public Pioche melangerTas() {
		Carte derniereCarte = getTop();
		// removeCarte(derniereCarte); -> fonctionne pas car tu passes une carte en paramètre et removeCarte prend un int
		Collections.shuffle(tas);
		//Pioche pioche = (Pioche) tas; -> Tu peux pas cast une arrayList
		for (Carte carte : tas) {
			//removeCarte(getTop()); -> pareil que pour la ligne 53
		}
		//addCarte(derniereCarte) ne fonctionne pas tas.add(derniereCarte);
		//return pioche;
	}*/
}
