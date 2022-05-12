package tas;

import java.util.ArrayList;
import java.util.Collections;

import carte.Carte;
import exception.TasException;
import pioche.Pioche;

/*
 * Je sais pas si les primitives que tu as faites sont vraiment utile
 * -> addCarte, removeCarte, getSize, getTop... 
 * Car tu peux tout faire directement dans la fonction concern�e
 * A toi de voir, car �a peut allourdir le code m�me si �a peut peut-�tre facilit� sa compr�hension
 * M�me commentaire pour pioche
 * */

public class Tas {

	public ArrayList<Carte> tas;

	public Tas() {

	}

	public boolean addCarte(Carte carte) {
		return tas.add(carte);
	}

	/*
	 * Possiblement a ?
	 * Changer par index ou par carte
	 * */

	public boolean removeCarte(Carte carte) {
		return tas.remove(carte);
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

	public Pioche melangerTas() {
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
}
