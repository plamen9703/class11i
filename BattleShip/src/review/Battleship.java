package review;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Battleship extends JFrame {

	private GameField[][] field = new GameField[10][10];
	private GameField[][] enemy = new GameField[10][10];
	private Random r = new Random();

	public Battleship() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setSize(1200, 600);
		setLocationRelativeTo(null);
		setVisible(true);

		buildField(field, 0);
		buildField(enemy, 1);
		deployRandomShips(enemy);
		deployShips(field);
	}

	private void deployRandomShips(GameField[][] enmy) {
		int[] shipSize = { 5, 4, 3, 2, 2 };

	}

	private void deployShips(GameField[][] field) {
		int[] shipSize = { 5, 4, 3, 2, 2 };

		for (int i = 0; i < shipSize.length;) {
			String p = JOptionPane.showInputDialog(null, "Enter starting position for ship size " + shipSize[i] + ":");
			int sX = p.charAt(0) - 'A';// row
			int sY = Integer.parseInt(p.split(" ")[1]) - 1;// col
			boolean h = p.charAt(p.length() - 1) == 'h';
			if (canPlaceShip(field, sX, sY, h, shipSize[i])) {
				for (int j = 0; j < shipSize[i]; j++) {
					if (h) {
						field[sX + j][sY].setShipPlaces(true);
						field[sX + j][sY].setText("S");
					} else {
						field[sX][sY + j].setShipPlaces(true);
						field[sX][sY + j].setText("S");
					}
				}
				i++;
			} else {
				JOptionPane.showMessageDialog(null, "Invalid placement!", "Error", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private boolean canPlaceShip(GameField[][] field, int sX, int sY, boolean h, int i) {
		if (sX >= 0 && sX < 10 && sY >= 0 && sY < 10 && ((h && sY + i <= 9) || (!h && sX + i <= 9))) {
			sX--;
			sY--;
			for (int j = 0; j < i + 2; i++) {
				if (h) {
					if ((isFiledFree(field,sX, sY) == 1) || (isFiledFree(field,sX + 1, sY) == 1)
							|| (isFiledFree(field,sX + 2, sY) == 1)) {
						return false;
					}
					sX++;
				} else {
					if ((isFiledFree(field,sX, sY) == 1) || (isFiledFree(field,sX, sY + 1) == 1)
							|| (isFiledFree(field,sX, sY + 2)) == 1) {
						return false;
					}
					sY++;
				}

			}
			return true;
		} else {
			return false;
		}
	}

	private int isFiledFree(GameField[][] field,int sX, int sY) {
		if (sX >= 0 && sX < 10 && sY >= 0 && sY < 10) {
			if (field[sX][sY].getShipedPlaced()) {
				return 1;
			} else {
				return 0;
			}
		}
		return -1;
	}

	private void buildField(GameField[][] field, int board) {
		for (int i = 0; i < 10; i++) {
			JLabel l = new JLabel((char) ('A' + i) + "");
			l.setBounds(50 + i * 50 + board * 600, 0, 50, 50);
			l.setHorizontalAlignment(SwingConstants.CENTER);
			add(l);
			JLabel l1 = new JLabel((i + 1) + "");
			l1.setBounds(0 + board * 600, 50 + i * 50, 50, 50);
			l1.setHorizontalAlignment(SwingConstants.RIGHT);
			add(l1);
		}
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				field[i][j] = new GameField(50 + i * (50) + board * 600, 50 + j * (50), "~");
				add(field[i][j]);
			}
		}
		repaint();
	}

	public static void main(String[] args) {
		new Battleship();
	}
}
