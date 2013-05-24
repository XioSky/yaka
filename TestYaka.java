package yaka;

public class TestYaka {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Plateau p = new Plateau();

		p.initialize();
		System.out.println(p);
		// System.out.println(Plateau.plateau[1][6].allowedMove(Mouvements.AHEAD));
		Plateau.plateau[1][3].move(Mouvements.RIGHT_AHEAD);
		// Plateau.plateau[5][1].move(Mouvements.RIGHT);
		System.out.println(p);

	}

}
