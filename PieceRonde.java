package yaka;

public class PieceRonde extends Piece {

	/**
	 * @param args
	 */

	private Mouvement move[];

	public PieceRonde(int x, int y, int couleur) {
		super(x, y, couleur);
		setMove(Mouvements.ROUND_MOVES);
	}

	public Mouvement[] getMove() {
		return move;
	}

	public void setMove(Mouvement move[]) {
		this.move = move;
	}

	@Override
	public char getCode() {
		if (this.getCouleur() == Piece.BLANC) {
			return 'r';
		} else {
			return 'R';
		}
	}

}
