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

	public void jouerUnTour(Joueur j1, Joueur j2, Plateau p) {
		boolean joueur1 = true;

		if (joueur1) {
			System.out.println(j1.getListePiece());
			System.out.println(j1.getListeCoups());
			System.out.println(j2.coupChoisi());
			j1.coupChoisi().doIt();
			System.out.println(p);
			joueur1 = false;
		}
		if (!joueur1) {
			System.out.println(j2.getListePiece());
			System.out.println(j2.getListeCoups());
			System.out.println(j2.coupChoisi());

			j2.coupChoisi().doIt();
			System.out.println(p);
			joueur1 = true;
		}
	}

	@Override
	public Coup coupChoisi() {
		coupChoisi = setListeCoups().get((int) (Math.random() * setListeCoups().size()));
		return coupChoisi;

	}

}
