package yaka;

public class JoueurHasard extends Joueur {

	public JoueurHasard(int couleur) {
		super(couleur);

	}

	@Override
	public Coup coupChoisi() {
		Coup tmp = null;
		for (int i = 0; i < getListeCoups().size(); i++) {
			if (getListeCoups().get(i).coupGagnant()) {
				tmp = getListeCoups().get(i);
			} else if (getListeCoups().get(i).prise()) {
				tmp = getListeCoups().get(i);
			}
		}
		if (tmp == null) {
			tmp = getListeCoups().get((int) (Math.random() * getListeCoups().size()));
		}
		return tmp;
	}

	@Override
	public Coup getCoupChoisi() {

		return coupChoisi();
	}

}
