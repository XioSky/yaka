package yaka;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ComponentLocation implements ActionListener {
	JButton[][] buttons = new JButton[8][8];
	String tmps;
	static JFrame f;
	Piece piece;
	Mouvement m;
	boolean deplace = false;

	/**
	 * renvoie les coordonn�s des boutons
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		Point gridLoc = getArrayLocation(button);

		tmps = gridLoc.x + " " + gridLoc.y;
		System.out.println(tmps);
		click(gridLoc.x, gridLoc.y);
		piece.move(m);
		// reste a revoir le calcul de moveX et moveY

		rafraichir();

	}

	// recupere les cordonn�s pour la methode
	private Point getArrayLocation(JButton target) {
		Point p = new Point(-1, -1);
		for (int j = 0; j < buttons.length; j++) {
			for (int k = 0; k < buttons.length; k++) {
				if (buttons[j][k] == target) {
					p.setLocation(j, k);
					return p;
				}
			}
		}
		return p;
	}

	private JPanel getContent() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weighty = 1.0;
		gbc.weightx = 1.0;
		for (int j = 7; j >= 0; j--) {
			for (int k = 0; k < buttons[j].length; k++) {
				buttons[j][k] = new JButton();
				buttons[j][k].setPreferredSize(new Dimension(41, 41));
				// affiche en fonction du plateau j'ai pas encore mis des images
				// pour le moment juste des couleurs
				if (Plateau.plateau[j][k].getCode() == 'c') {
					buttons[j][k].setBackground(Color.green);
				} else {
					if (Plateau.plateau[j][k].getCode() == 'C') {
						buttons[j][k].setBackground(Color.RED);
					} else {
						if (Plateau.plateau[j][k].getCode() == 'r') {
							buttons[j][k].setBackground(Color.WHITE);
						} else {
							if (Plateau.plateau[j][k].getCode() == 'R') {
								buttons[j][k].setBackground(Color.PINK);
							} else {
								buttons[j][k].setBackground(Color.YELLOW);
							}
						}
					}
				}
				buttons[j][k].setOpaque(true);
				buttons[j][k].addActionListener(this);
				gbc.gridwidth = (k < buttons[j].length - 1) ? 1 : GridBagConstraints.REMAINDER;
				panel.add(buttons[j][k], gbc);
			}
		}
		return panel;
	}

	// Cr�e la JFrame ne pas toucher !
	public static JFrame newTab() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(new ComponentLocation().getContent());
		f.setSize(400, 400);
		f.setLocation(200, 200);
		f.setVisible(true);
		return f;
	}

	// A appeller pour mettre a jour le graphique
	public static void rafraichir() {

		f.getContentPane().removeAll();
		f.getContentPane().add(new ComponentLocation().getContent());
		f.revalidate();
	}

	public void click(int to, int tu) {
		if (!deplace) {
			piece = Plateau.plateau[to][tu];
			System.out.println(piece);
		}

		if (deplace) {
			int moveX = to - piece.getCouleur() * piece.getX();
			int moveY = tu - piece.getCouleur() * piece.getY();
			m = new Mouvement(1, 0);

		}
		deplace = !deplace;
	}
}