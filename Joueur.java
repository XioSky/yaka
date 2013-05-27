package yaka;

import java.util.ArrayList;

public abstract class Joueur {

	public static final int BLANC = 1;
	public static final int NOIR = -1;
	private ArrayList<Piece> liste;
	private int couleur;

	// -----------Constructeur--------------------------------------------------------------------------------
	public Joueur(int couleur) {
		this.couleur = couleur;
		liste = new ArrayList<Piece>();

		boolean rond = false;

		if (couleur == 1) {

			for (int i = 1; i >= 0; i--) {

				for (int j = 0; j < 8; j++) {

					if (!rond) {
						liste.add(new PieceCarree(i, j, couleur));
					}

					if (rond) {
						liste.add(new PieceRonde(i, j, couleur));
					}
					rond = !rond;
				}
			}
		} else if (couleur == -1) {

			for (int i = 7; i >= 6; i--) {

				for (int j = 0; j < 8; j++) {

					if (!rond) {
						liste.add(new PieceCarree(i, j, couleur));
					}

					if (rond) {
						liste.add(new PieceRonde(i, j, couleur));
					}
					rond = !rond;
				}
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------------

	public abstract Coup coupChoisi();

	public int getCouleur() {
		return this.couleur;
	}

	public ArrayList<Coup> listeCoups() {
		ArrayList<Coup> listing = null;
		Mouvement[] m = Mouvements.ALL_MOVES;
		Piece tmp;
		for (int i = 0; i < liste.size(); i++) {
			tmp = liste.get(i);

		}
		return listing;
	}
}
