package yaka;

public class TestYaka {

	public static void main(String[] args) {

		Plateau p = new Plateau();

		p.initialize();
		System.out.println(p);

		Joueur j1 = new JoueurHumain(1);
		Joueur j2 = new JoueurHasard(-1);

		j1.jouerPartie(j1, j2, p);

	}
}
