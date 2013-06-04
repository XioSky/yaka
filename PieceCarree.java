package yaka;

public class PieceCarree extends Piece {

	private Mouvement move[];

	public PieceCarree(int x, int y, int couleur) {
		super(x, y, couleur);
		setMove(Mouvements.SQUARE_MOVES);
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
			return 'c';
		} else {
			return 'C';
		}
	}

}
