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

	@Override
	public Mouvement[] getMove() {
		return move;
	}

	public void setMove(Mouvement move[]) {
		this.move = move;
	}

	public void getMove(Mouvement move[]) {
		this.move = move;
	}

	@Override
	public Mouvement getMove(int i) {
		return this.move[i];
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
