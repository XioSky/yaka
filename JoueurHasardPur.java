package yaka;

public class JoueurHasardPur extends Joueur {
	private Coup coupChoisi;

	public JoueurHasardPur(int couleur) {
		super(couleur);

	}

	@Override
	public Coup getCoupChoisi() {
		return coupChoisi;
	}

	@Override
	public Coup coupChoisi() {
		coupChoisi = getListeCoups().get((int) (Math.random() * getListeCoups().size()));
		return coupChoisi;

	}

}
