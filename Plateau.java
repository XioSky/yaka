package yaka;

public class Plateau {
	public static Piece[][] plateau;

	public Plateau() {
		plateau = new Piece[8][8];
	}

	public void initialize() {
		boolean tracerRond = true;
		int couleur = 1;

		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau.length; j++) {
				if (i > 5) {
					couleur = -1;
				}
				if (tracerRond == true && (i < 2 || i > 5)) {
					plateau[i][j] = new PieceRonde(i, j, couleur);
					tracerRond = false;
				} else if (tracerRond == false && (i < 2 || i > 5)) {
					plateau[i][j] = new PieceCarree(i, j, couleur);
					tracerRond = true;
				} else {
					plateau[i][j] = new PieceVide(i, j, 0);
				}
			}
			tracerRond = !tracerRond;
		}
	}

	@Override
	public String toString() {
		String bordureHorizontale = "  _________________________________________"
				+ "\n";
		String tmp = bordureHorizontale;

		for (int i = 7; i >= 0; --i) {

			tmp += (i + 1) + "| ";

			for (int j = 0; j < plateau.length; ++j) {

				tmp += "  " + Plateau.plateau[i][j].getCode() + "  ";
			}

			tmp += " |" + "\n";
		}

		tmp += bordureHorizontale;
		tmp += "   ";
		for (int i = 0; i < plateau.length; i++) {

			tmp += "  " + (char) ('a' + i) + "  ";
		}

		return tmp;
	}
}
