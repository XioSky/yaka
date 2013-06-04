package yaka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public abstract class Joueur {

	public static final int BLANC = 1;
	public static final int NOIR = -1;
	private int couleur;
	private ArrayList<Piece> listePiece;

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

	public abstract Coup coupChoisi();

	public abstract Coup getCoupChoisi();

	public ArrayList<Piece> getListePiece() {
		return listePiece;
	}

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
		return listeCoups;
	}

	// consiste à supprimer le pion pris de la liste adverse. - NE FONCTIONNE
	// PAS -

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

	// Retourne une liste de tout les coups possibles
	/**
	 * @return Calcule à chaque tour, tous les coups possibles pour chaque pièce
	 *         d'un joueur.
	 */
	// méthode surement inutile, à voir. (méthode getListeCoups())
	public ArrayList<Coup> setListeCoups() {

		ArrayList<Coup> listing;
		Iterator<Piece> itrPiece;
		Piece tmpPiece;

		listing = new ArrayList<Coup>();
		itrPiece = this.listePiece.iterator();

		while (itrPiece.hasNext()) {

			tmpPiece = itrPiece.next();

			if (tmpPiece.getCode() != '.' && tmpPiece.getCode() == 'r' || tmpPiece.getCode() == 'R') {

				for (int j = 0; j < Mouvements.ROUND_MOVES.length; j++) {

					if (tmpPiece.pieceDeplacable(Mouvements.ROUND_MOVES[j])
							|| tmpPiece.priseAdversaire(Mouvements.ROUND_MOVES[j])) {

						listing.add(new Coup(tmpPiece, Mouvements.ROUND_MOVES[j]));
					}
				}
			} else if (tmpPiece.getCode() != '.' && tmpPiece.getCode() == 'c'
					|| tmpPiece.getCode() == 'C') {

				for (int j = 0; j < Mouvements.SQUARE_MOVES.length; j++) {

					if (tmpPiece.pieceDeplacable(Mouvements.SQUARE_MOVES[j])
							|| tmpPiece.priseAdversaire(Mouvements.SQUARE_MOVES[j])) {

						listing.add(new Coup(tmpPiece, Mouvements.SQUARE_MOVES[j]));
					}
				}
			}
		}

		return listing;
	}

	// implémentation dans Joueur, correction du tour du joueur par une
	// booléenne (pour éviter des "débordements")
	/**
	 * @param j1
	 * @param j2
	 * @param p
	 */
	public abstract void jouerUnTour(Joueur j1, Joueur j2, Plateau p);

	/**
	 * Lance une partie, qui s'arrête lorsqu'il y a un vainqueur, ou si l'un des
	 * joueurs n'a plus de pions
	 * 
	 * @param j1
	 * @param j2
	 * @param p
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
