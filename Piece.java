package yaka;

/*
 * @author Adhémar Wissocq
 * 
 */
public abstract class Piece {

	public static final int NOIR = -1;
	public static final int BLANC = 1;
	public static final int VIDE = 0;
	public static Mouvement[] move;
	private int x, y;
	private int couleur;

	public Piece(int x, int y, int couleur) {
		this.x = x;
		this.y = y;
		this.setCouleur(couleur);
	}

	public char getCode() {
		return '.';
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		String str;

		str = "[" + this.x + ", " + this.y + "] : ";
		str += this.getCode() + " ";

		if (this.couleur == Piece.BLANC) {

			str += "Blanc";
		} else if (this.couleur == Piece.NOIR) {
			str += "Noir";
		}
		return str;
	}

	public int getCouleur() {
		return couleur;
	}

	public void setCouleur(int couleur) {
		this.couleur = couleur;
	}

	private void clear(int x, int y) {
		Plateau.plateau[x][y] = new PieceVide(x, y, 0);
	}

	public void move(Mouvement m) {
		int couleur;
		int tmpX, tmpY;
		if (this.allowedMove(m)) {
			tmpX = this.x + this.couleur * m.getDeltaX();
			tmpY = this.y + this.couleur * m.getDeltaY();
			couleur = this.getCouleur();
			System.out.println(Plateau.plateau[6][2]);
			if (this.getCode() == 'r' || this.getCode() == 'R') {

				clear(this.x, this.y);
				Plateau.plateau[tmpX][tmpY] = new PieceRonde(tmpX, tmpY,
						couleur);
				return;
			} else if (this.getCode() == 'c' || this.getCode() == 'C') {
				System.out.println(this.x + ", " + this.y + "     " + tmpX
						+ ", " + tmpY);
				clear(this.x, this.y);
				Plateau.plateau[tmpX][tmpY] = new PieceCarree(tmpX, tmpY,
						couleur);
				return;
			}
			System.out.println("mouvement permis");
			return;
		}
		System.out.println("rien");
	}

	public boolean allowedMove(Mouvement m) {
		if (priseAdversaire(m) || pieceDeplacable(m)) {
			return true;
		}
		return false;
	}

	private boolean pieceDeplacable(Mouvement m) {

		int tmpX = this.x + (this.couleur * m.getDeltaX());
		int tmpY = this.y + (this.couleur * m.getDeltaY());
		if (tmpX < 0 || tmpX > 7 || tmpY < 0 || tmpY > 7) {
			return false;
		} else if (Plateau.plateau[tmpX][tmpY].getCode() == this.getCode()) {
			return false;
		}

		return true;
	}

	private boolean sortie(Mouvement m) {
		int tmpX = this.getX() + this.couleur * m.getDeltaX();
		int tmpY = this.getY() + this.couleur * m.getDeltaY();

		return false;
	}

	private boolean priseAdversaire(Mouvement m) {
		int tmpX = this.getX() + (this.couleur * m.getDeltaX());
		int tmpY = this.getY() + (this.couleur * m.getDeltaY());
		if (tmpX >= 0 && tmpX < 8 && tmpY >= 0 && tmpY < 7) {
			if (Plateau.plateau[tmpX][tmpY].getCode() != this.getCode()
					&& Plateau.plateau[tmpX][tmpY].getCode() != '.') {
				return true;
			}
		}
		return false;
	}
}
