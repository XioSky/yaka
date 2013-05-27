package yaka;

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

	public void doIt(Coup cp) {

		if (winingMove(cp)) {
			piece.move(m);
			return;
		}
		System.out.println("erreur");
		return;
	}

	public boolean winingMove(Coup cp) {

		int tmpX, tmpY;
		tmpX = piece.getX() + piece.getCouleur() * m.getDeltaX();
		tmpY = piece.getY() + piece.getCouleur() * m.getDeltaY();

		if (Plateau.plateau[tmpX][tmpY].getCouleur() != piece.getCouleur()) {
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
