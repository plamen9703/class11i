package review;

import javax.swing.JButton;

public class GameField extends JButton {

	private boolean shipPlaced;
	
	public GameField(int x, int y, String text) {
		setText(text);
		setBounds(x,y,50,50);
	}
	
	public void setShipPlaces(boolean shipPlaced) {
		this.shipPlaced=shipPlaced;
	}
	
	public boolean getShipedPlaced() {
		return this.shipPlaced;
	}
}
