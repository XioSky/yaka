package yaka;

import java.util.ArrayList;

public class MonkeyIA extends Joueur {
	private ArrayList<Coup> coups;

	public MonkeyIA(int couleur) {
		super(couleur);
		coups = new ArrayList<Coup>();
	}

	@Override
	public Coup coupChoisi() {
		coups = listeCoups();
		return coups.get((int) Math.random() * coups.size());

	}

}
