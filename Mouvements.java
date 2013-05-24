package yaka;

public class Mouvements {
	public final static Mouvement RIGHT = new Mouvement(0, 1); // 1,0
	public final static Mouvement LEFT = new Mouvement(0, -1); // -1,0
	public final static Mouvement AHEAD = new Mouvement(-1, 0); // 0,1
	public final static Mouvement RIGHT_AHEAD = new Mouvement(-1, 1); // 1,1
	public final static Mouvement LEFT_AHEAD = new Mouvement(-1, -1); // -1,1

	public final static Mouvement[] NO_MOVE = {};
	public final static Mouvement[] SQUARE_MOVES = { RIGHT, LEFT, AHEAD };
	public final static Mouvement[] ROUND_MOVES = { RIGHT_AHEAD, LEFT_AHEAD };

	public final static Mouvement[] ALL_MOVES = { LEFT, LEFT_AHEAD, AHEAD,
			RIGHT_AHEAD, RIGHT };

	public static Mouvement getMouvement(int codeMouvement) {
		return ALL_MOVES[codeMouvement];
	}

	@Override
	public String toString() {
		return "";
	}
}
