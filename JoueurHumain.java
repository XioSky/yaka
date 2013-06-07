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
		// evite un nullpointer exception si l'utilisateur entre quelque chose
		// comme 9n ou même 99
		if ((pos[0] < 1 || pos[0] > 7) || (pos[1] < 1 || pos[1] > 7)) {

			System.out
					.println("piece selectionnée invalide, veuillez entrer des coordonnées correctes ");
			return entrerCoordonnees();

		}
		if (Plateau.plateau[pos[0]][pos[1]].getCouleur() != this.getCouleur()
				&& Plateau.plateau[pos[0]][pos[1]].getCode() == '.') {
			System.out
					.println("piece selectionnée invalide, veuillez entrer des coordonnées correctes ");
			return entrerCoordonnees();
		}

		return pos;

	}

	/**
	 * @return Le mouvement s'il est valide
	 */
	public Mouvement entrerMouvement() {
		Mouvement m;
		String tmp;
		int codeMouvement = 0;

		System.out.println("Entrez code mouvement :  ");
		// ajout d'un try catch(InputMismatchException) inefficace pour une
		// lettre entrée dans un scanner de nextInt()
		tmp = sc.next();
		if (tmp.length() != 1 || tmp.charAt(0) < '0' || tmp.charAt(0) > '4') {
			System.out.println("code mouvement impossible, veuillez en selectionner un autre");
			return entrerMouvement();
		} else {
			codeMouvement = Integer.valueOf(tmp);
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
		if (!p.pieceDeplacable(m)) {
			m = entrerMouvement();
		}
		coupChoisi = new Coup(p, m);

		if (!coupValide(coupChoisi)) {
			System.out.println("Mouvement incorrect");

			return coupChoisi();
		}
		System.out.println(coupChoisi);

		return coupChoisi;
	}

	/**
	 * @param cp
	 *            Un coup
	 * @return Si le coup peut être joué.
	 */
	// Cette méthode n'a pas fonctionné en faisant un iterator de la liste de
	// coups, qui vérifiait si le coup joué était bien contenu dedans
	// On opte donc pour une méthode simple à comprendre, à mettre en place, et
	// qui est aussi efficace
	private boolean coupValide(Coup cp) {

		// Si la piece est un rond, on vérifie que le mouvement effectué est
		// bien contenu dans les mouvements des ronds
		if (cp.getPiece() instanceof PieceRonde) {
			for (int i = 0; i < Mouvements.ROUND_MOVES.length; i++) {
				if ((cp.getMouvement().getDeltaX() == Mouvements.ROUND_MOVES[i].getDeltaX())
						&& cp.getMouvement().getDeltaY() == Mouvements.ROUND_MOVES[i].getDeltaY()) {
					return true;
				}
			}
		}
		// idem que pour ronds
		if (cp.getPiece() instanceof PieceCarree) {
			for (int i = 0; i < Mouvements.SQUARE_MOVES.length; i++) {
				if ((cp.getMouvement().getDeltaX() == Mouvements.SQUARE_MOVES[i].getDeltaX())
						&& cp.getMouvement().getDeltaY() == Mouvements.SQUARE_MOVES[i].getDeltaY()) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public Coup getCoupChoisi() {

		return coupChoisi;
	}

}
