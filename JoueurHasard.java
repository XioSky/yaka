package yaka;

public class JoueurHasard extends Joueur {

	public JoueurHasard(int couleur) {
		super(couleur);

	}

	@Override
	public Coup coupChoisi() {
		Coup tmp = null;
		for (int i = 0; i < setListeCoups().size(); i++) {
			if (setListeCoups().get(i).coupGagnant()) {
				tmp = setListeCoups().get(i);
			} else if (setListeCoups().get(i).prise()) {
				tmp = setListeCoups().get(i);
			}
		}
		if (tmp == null) {
			tmp = setListeCoups().get((int) (Math.random() * setListeCoups().size()));
		}
		return tmp;
	}

	public void jouerUnTour(Joueur j1, Joueur j2, Plateau p) {
		boolean joueur1 = true;

		if (joueur1) {
			System.out.println(j1.getListePiece());
			System.out.println(j1.getListeCoups());
			System.out.println(j1.coupChoisi());
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
	public Coup getCoupChoisi() {

		return coupChoisi();
	}

}
