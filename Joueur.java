package yaka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public abstract class Joueur {

	public static final int BLANC = 1;
	public static final int NOIR = -1;
	private int couleur;
	private ArrayList<Piece> listePiece;
	private ArrayList<Coup> listeDeCoups;

	// -----------Constructeur--------------------------------------------------------------------------------
	public Joueur(int couleur) {
		this.couleur = couleur;
		listePiece = new ArrayList<Piece>();

		for (int i = 0; i < Plateau.plateau.length; i++) {
			for (int j = 0; j < Plateau.plateau.length; j++) {
				if (Plateau.plateau[i][j].getCouleur() == couleur) {
					listePiece.add(Plateau.plateau[i][j]);
				}
			}
		}

	}

	// ---------------------------------------------------------------------------------------------------------

	/**
	 * @return Choisi un coup, et s'il est valide, le joue avec la méthode
	 *         doIt()
	 */
	public abstract Coup coupChoisi();

	/**
	 * @return Le coup qui a été joué
	 */
	public abstract Coup getCoupChoisi();

	/**
	 * @return La liste de pièces du joueur.
	 */
	public ArrayList<Piece> getListePiece() {
		return listePiece;
	}

	/**
	 * @return La couleur du joueur
	 */
	public int getCouleur() {
		return couleur;
	}

	/**
	 * @return La liste de tous les coups possibles pour un joueur
	 */

	public ArrayList<Coup> getListeCoups() {

		ArrayList<Coup> listeCoups;
		Iterator<Piece> itrPiece;
		Iterator<Mouvement> itrMove;
		Piece p;
		Mouvement m;

		listeCoups = new ArrayList<Coup>();
		itrPiece = this.listePiece.iterator();
		while (itrPiece.hasNext()) {
			p = itrPiece.next();
			itrMove = Arrays.asList(p.getMove()).iterator();
			while (itrMove.hasNext()) {
				m = itrMove.next();
				if (p.pieceDeplacable(m)) {
					listeCoups.add(new Coup(p, m));
				}
			}
		}
		this.listeDeCoups = listeCoups;
		return listeCoups;
	}

	public ArrayList<Coup> getListeCoupsDuJoueur() {
		return this.listeDeCoups;
	}

	/**
	 * @param j1
	 *            Un joueur
	 * @param j2
	 *            Un joueur
	 * 
	 *            Retire une pièce de la liste si elle a été prise
	 */
	public void removePiece(Joueur j1, Joueur j2) {
		Piece p;
		if (j1.getCoupChoisi().prise()) {
			p = j1.getCoupChoisi().getPiece();

			j2.listePiece.remove(p);
		} else if (j2.getCoupChoisi().prise()) {
			p = j2.getCoupChoisi().getPiece();
			j1.listePiece.remove(p);
		}
	}

	/**
	 * @return Liste des pièces restantes pour un joueur.
	 */
	public void setListePiece() {
		listePiece.clear();
		for (int i = 0; i < Plateau.plateau.length; i++) {
			for (int j = 0; j < Plateau.plateau.length; j++) {
				if (Plateau.plateau[i][j].getCouleur() == this.couleur) {
					listePiece.add(Plateau.plateau[i][j]);
				}
			}
		}

	}

	/**
	 * @param j1
	 *            Un joueur
	 * @param j2
	 *            Un joueur
	 * @param p
	 *            Le plateau de jeu
	 * 
	 *            Méthode qui joue un tour
	 */
	public void jouerUnTour(Joueur j1, Joueur j2, Plateau p) {
		boolean joueur1 = true;

		if (joueur1) {
			System.out.println(j1.getListePiece());
			System.out.println(j1.getListeCoups());

			j1.coupChoisi().doIt();
			System.out.println("Mouvement effectué : " + j1.getCoupChoisi());
			System.out.println(p);
			joueur1 = false;
		}
		if (!joueur1) {

			System.out.println(j2.getListePiece());
			System.out.println(j2.getListeCoups());

			j2.coupChoisi().doIt();
			System.out.println("Mouvement effectué : " + j2.getCoupChoisi());
			System.out.println(p);
			joueur1 = true;
		}
	}

	/**
	 * Lance une partie, qui s'arrête lorsqu'il y a un vainqueur, ou si l'un des
	 * joueurs n'a plus de pions
	 * 
	 * @param j1
	 *            Un joueur
	 * @param j2
	 *            Un joueur
	 * @param p
	 *            Le plateau de jeu
	 */
	public void jouerPartie(Joueur j1, Joueur j2, Plateau p) {
		boolean joueurGagne = false;
		Coup dernierCoup = null;

		while (!joueurGagne) {
			jouerUnTour(j1, j2, p);
			if (j1.getCoupChoisi().coupGagnant()) {
				if (j1.getCoupChoisi().prise()) {
					removePiece(j1, j2);
				}
				joueurGagne = true;
				dernierCoup = j1.getCoupChoisi();

			} else if (j2.getCoupChoisi().coupGagnant()) {
				if (j2.getCoupChoisi().prise()) {
					removePiece(j1, j2);
				}
				joueurGagne = true;
				dernierCoup = j2.getCoupChoisi();
			}
		}
		if (j1.getListePiece().size() == 0 || j2.getListePiece().size() == 0) {
			joueurGagne = true;
		}
		System.out.println("le joueur " + j1.winner(j2) + " gagne avec " + dernierCoup);
	}

	/**
	 * @param j
	 * @return Le joueur gagnant si un coup gagnant est joué
	 */
	public Joueur winner(Joueur j) {
		if (j.getCoupChoisi().coupGagnant()) {
			return j;
		}

		return this;
	}

	@Override
	public String toString() {

		if (couleur == Piece.BLANC) {
			return "Blanc";
		}
		return "Noir";
	}
}
