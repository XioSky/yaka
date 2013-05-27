package yaka;

public class TestYaka {

	public static void main(String[] args) {
		int[] pos = new int[2];

		Plateau p = new Plateau();

		Mouvement m;
		System.out.println(Mouvements.getMouvement(2));
		p.initialize();
		System.out.println(p);
		pos = Piece.location();
		m = Piece.newLocation();
		// System.out.println("main display :    " + (pos[0] + 1) + ", "
		// + (char) (pos[1] + 'a') + " : " + m);

		Plateau.plateau[pos[0]][pos[1]].move(m);

		System.out.println(p);

	}

}
