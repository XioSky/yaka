package yaka;

import java.util.Scanner;

public class JoueurHumain extends Joueur {

	private Scanner sc = new Scanner(System.in);
	private Coup coupChoisi;

	public JoueurHumain(int couleur) {
		super(couleur);

	}

	/**
	 * @return Les coordonnées de la pièce si elles sont valides
	 */
	public int[] entrerCoordonnees() {
		int pos[] = new int[2];
		String coordonnees;
		System.out.println("Entrez la pièce à bouger (ex : 2e ou e2, etc) ");
		coordonnees = sc.next();
		if (coordonnees.length() != 2) {
			return entrerCoordonnees();
		}
		if (coordonnees.length() == 2) {
			if (coordonnees.charAt(0) >= 'a') {
				pos[1] = coordonnees.charAt(0) - 'a';
				pos[0] = coordonnees.charAt(1) - '1';
			} else {
				pos[1] = coordonnees.charAt(1) - 'a';
				pos[0] = coordonnees.charAt(0) - '1';
			}
		}
		while (Plateau.plateau[pos[0]][pos[1]].getCouleur() != this.getCouleur()) {

			pos = entrerCoordonnees();
			System.out
					.println("piece selectionnée invalide, veuillez entrer des coordonnées correctes ");
			return entrerCoordonnees();
		}

		return pos;

	}

	@Override
	public void jouerUnTour(Joueur j1, Joueur j2, Plateau p) {
		boolean joueur1 = true;

		if (joueur1) {

			j1.coupChoisi().doIt();
			j1.setListePiece();
			System.out.println(p);
			joueur1 = false;
		}
		if (!joueur1) {

			j2.coupChoisi().doIt();
			j2.setListePiece();
			System.out.println(p);
			joueur1 = true;
		}

	}

	/**
	 * @return Le mouvement s'il est valides
	 */
	public Mouvement entrerMouvement() {
		Mouvement m;
		int codeMouvement;

		System.out.println("Entrez code mouvement :  ");
		// ajouter ici un try catch, pour gérer si l'utilisateur est tellement
		// con, qu'il rentre une lettre et que ça plante !
		// nom de l'exception : InputMismatchException
		codeMouvement = sc.nextInt();

		if (codeMouvement < 0 || codeMouvement > 4) {
			System.out.println("code mouvement impossible, veuillez en selectionner un autre");
			return entrerMouvement();
		}
		m = new Mouvement(Mouvements.getMouvement(codeMouvement).getDeltaX(), Mouvements
				.getMouvement(codeMouvement).getDeltaY());

		return m;
	}

	@Override
	public Coup coupChoisi() {

		int[] pos;
		Mouvement m;
		Piece p;
		System.out.println(this.getListeCoups());
		pos = entrerCoordonnees();

		p = Plateau.plateau[pos[0]][pos[1]];

		m = entrerMouvement();
		coupChoisi = new Coup(p, m);
		System.out.println(coupChoisi);
		if (coupInvalide(coupChoisi)) {

			return coupChoisi();
		}

		return coupChoisi;
	}

	/**
	 * @param cp
	 *            Un coup
	 * @return Vrai si le coup n'est pas contenu dans la liste des coups permis
	 */
	//    .
	//   /!\ méthode foireuse : meme en jouant un coup contenu dans la liste, il ne le reconnait pas !!
	//  /___\
	//
	private boolean coupInvalide(Coup cp) {

		if (!this.getListeCoups().contains(coupChoisi)) {

			System.out.println("Coup invalide, veuillez recommencer");
			return true;
		}
		return false;
	}

	@Override
	public Coup getCoupChoisi() {

		return coupChoisi;
	}

}
