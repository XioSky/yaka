package yaka;

import java.util.Scanner;

/*
 * @authors Adhémar Wissocq, Theo Plockyn, Antoine Deranton
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
		this.couleur = couleur;
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

	// vide une case (pour mouvements)
	private void clear(int x, int y) {
		Plateau.plateau[x][y] = new PieceVide(x, y, 0);
	}

	public static int[] location() {
		int pos[] = new int[2];
		String userEntry;
		Scanner sc = new Scanner(System.in);

		System.out.println("Quel pion désirez-vous déplacer ? ");
		// solution temporaire, que je modifierai par la suite par un
		// try{...}catch()
		do {
			userEntry = sc.nextLine();
			if (userEntry.charAt(0) > 96) {
				pos[1] = userEntry.charAt(0) - 'a';
				pos[0] = userEntry.charAt(1) - '1';
			} else {
				pos[1] = userEntry.charAt(1) - 'a';
				pos[0] = userEntry.charAt(0) - '1';
			}

		} while (!validLocation(userEntry));

		return pos;
	}

	private static boolean validLocation(String entry) {
		if (entry.length() != 2) {
			return false;
		}
		if (entry.charAt(0) >= 'a' && entry.charAt(0) <= 'h'
				&& entry.charAt(1) >= '1' && entry.charAt(1) <= '8') {
			return true;
		} else if (entry.charAt(1) >= 'a' && entry.charAt(1) <= 'h'
				&& entry.charAt(0) >= '1' && entry.charAt(0) <= '8') {
			return true;
		}
		return false;
	}

	public static Mouvement newLocation() {
		int codeMouvement;
		Mouvement m;

		Scanner sc = new Scanner(System.in);

		System.out.println("Code mouvement :  ");

		codeMouvement = sc.nextInt();

		m = new Mouvement(Mouvements.getMouvement(codeMouvement).getDeltaX(),
				Mouvements.getMouvement(codeMouvement).getDeltaY());
		sc.close();

		return m;
	}

	public void move(Mouvement m) {
		int couleur;
		int tmpX, tmpY;

		if (this.allowedMove(m)) {

			tmpX = this.getX() + this.couleur * m.getDeltaX();
			tmpY = this.getY() + this.couleur * m.getDeltaY();
			couleur = this.getCouleur();

			if (this.getCode() == 'r' || this.getCode() == 'R') {

				clear(this.getX(), this.getY());
				Plateau.plateau[tmpX][tmpY] = new PieceRonde(tmpX, tmpY,
						couleur);
				return;
			}

			else if (this.getCode() == 'c' || this.getCode() == 'C') {

				clear(this.getX(), this.getY());
				Plateau.plateau[tmpX][tmpY] = new PieceCarree(tmpX, tmpY,
						couleur);
				return;
			}

		}
	}

	public boolean allowedMove(Mouvement m) {
		if (pieceDeplacable(m) && !sortie(m)) {
			return true;
		}
		return false;
	}

	private boolean pieceDeplacable(Mouvement m) {

		int tmpX = this.x + (this.couleur * m.getDeltaX());
		int tmpY = this.y + (this.couleur * m.getDeltaY());
		if (!sortie(m)) {
			if (Plateau.plateau[tmpX][tmpY].getCode() == '.') {

				return true;
			} else if (priseAdversaire(m)) {

				return true;
			}

		}

		return false;
	}

	private boolean sortie(Mouvement m) {
		int tmpX = this.getX() + this.couleur * m.getDeltaX();
		int tmpY = this.getY() + this.couleur * m.getDeltaY();

		if (tmpX > 7 || tmpY < 0 || tmpY > 7) {

			return true;
		}

		return false;
	}

	private boolean priseAdversaire(Mouvement m) {
		int tmpX = this.getX() + (this.couleur * m.getDeltaX());
		int tmpY = this.getY() + (this.couleur * m.getDeltaY());
		if (tmpX >= 0 && tmpX < 8 && tmpY >= 0 && tmpY < 7) {
			if (Plateau.plateau[tmpX][tmpY].getCouleur() != this.getCouleur()
					&& Plateau.plateau[tmpX][tmpY].getCouleur() != Piece.VIDE) {
				return true;
			}
		}
		return false;
	}

}
