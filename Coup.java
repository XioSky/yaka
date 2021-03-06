package yaka;


/**
 * @author Skyrider
 * 
 */
public class Coup {

	private Piece piece;
	private Mouvement m;

	public Coup(Piece piece, Mouvement m) {
		this.piece = piece;
		this.m = m;

	}

	public Piece getPiece() {
		return this.piece;
	}

	public Mouvement getMouvement() {
		return this.m;
	}

	/**
	 * Effectue un mouvement
	 */
	public void doIt() {

		if (!coupGagnant()) {
			System.out.println("Au joueur " + this.piece.getJoueur());

			this.piece.move(m);
		}
	}

	/**
	 * @return true si le mouvement donne lieu � la capture d'une pi�ce adverse,
	 *         false sinon
	 */
	public boolean prise() {

		int tmpX, tmpY;
		tmpX = piece.getX() + piece.getCouleur() * m.getDeltaX();
		tmpY = piece.getY() + piece.getCouleur() * m.getDeltaY();

		if (this.piece.pieceDeplacable(m) && !this.piece.gagne(m)) {

			if (Plateau.plateau[tmpX][tmpY].getCouleur() != piece.getCouleur()
					&& Plateau.plateau[tmpX][tmpY].getCode() != '.') {

				return true;
			}
		}
		return false;
	}

	/**
	 * @return Si le coup effectu� donnera suite � une victoire.
	 */
	public boolean coupGagnant() {
		int tmpX = piece.getX() + piece.getCouleur() * m.getDeltaX();
		if (tmpX < 0 || tmpX > 7) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String tmp = "";
		tmp += piece + " : move  " + m;

		return tmp;
	}
}
