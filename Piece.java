package yaka;

/*
 * @authors Adhémar Wissocq, Theo Plockyn, Antoine Deranton
 * 
 */

// colision : passe tour
// mouvement non permis (diago pour carré et avant/latéral pour ronds)
public abstract class Piece {

	public static final int NOIR = -1;
	public static final int BLANC = 1;
	public static final int VIDE = 0;

	private Mouvement[] move;
	private Joueur j;
	private int x, y;
	private int couleur;

	public Piece(int x, int y, int couleur) {
		this.x = x;
		this.y = y;
		this.couleur = couleur;

	}

	public Joueur getJoueur() {
		if (this.couleur == Piece.BLANC) {
			j = new JoueurHasardPur(1);
		} else {
			j = new JoueurHasardPur(-1);
		}
		return j;
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

	public void setMove() {
		if (this instanceof PieceCarree) {
			move = Mouvements.SQUARE_MOVES;
		}

		else if (this instanceof PieceRonde) {
			move = Mouvements.ROUND_MOVES;
		}
	}

	public Mouvement[] getMove() {
		return this.move;
	}

	public Mouvement getMove(int i) {
		return this.move[i];
	}

	@Override
	public String toString() {
		String str;

		str = "[" + (this.x + 1) + ", " + (char) (this.y + 'a') + "] : ";
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

	public void move(Mouvement m) {

		int tmpX, tmpY;

		if (this.pieceDeplacable(m)) {

			tmpX = this.getX() + this.couleur * m.getDeltaX();
			tmpY = this.getY() + this.couleur * m.getDeltaY();

			Plateau.plateau[this.x][this.y] = new PieceVide(this.x, this.y);

			Plateau.plateau[tmpX][tmpY] = this;
			this.x = tmpX;
			this.y = tmpY;

		}
	}

	/**
	 * @param m
	 *            Un mouvement
	 * @return true si la pièce est déplaçable, false sinon
	 */
	public boolean pieceDeplacable(Mouvement m) {

		int tmpX = this.x + (this.couleur * m.getDeltaX());
		int tmpY = this.y + (this.couleur * m.getDeltaY());

		if (!sortie(m) && (gagne(m) || Plateau.plateau[tmpX][tmpY].getCouleur() != this.couleur)) {
			return true;

		}

		return false;
	}

	/**
	 * @param m
	 *            Un mouvement
	 * @return Si la pièce sort du plateau
	 */
	private boolean sortie(Mouvement m) {

		int tmpY = this.getY() + this.couleur * m.getDeltaY();

		if (tmpY < 0 || tmpY > 7) {

			return true;
		}

		return false;
	}

	/**
	 * @param m
	 *            Un mouvement
	 * @return Si un joueur gagne
	 */
	public boolean gagne(Mouvement m) {
		int tmpX = this.getX() + this.couleur * m.getDeltaX();

		if (tmpX > 7 || tmpX < 0) {
			return true;
		}
		return false;
	}

	/**
	 * @param m
	 *            Un mouvement
	 * @return Si il y a une prise de pièce adverse
	 */
	public boolean priseAdversaire(Mouvement m) {
		int tmpX = this.getX() + (this.couleur * m.getDeltaX());
		int tmpY = this.getY() + (this.couleur * m.getDeltaY());
		if (!sortie(m)) {
			if (Plateau.plateau[tmpX][tmpY].getCode() != '.'
					&& Plateau.plateau[tmpX][tmpY].getCouleur() != this.couleur) {
				return true;
			}
		}

		return false;
	}

}
