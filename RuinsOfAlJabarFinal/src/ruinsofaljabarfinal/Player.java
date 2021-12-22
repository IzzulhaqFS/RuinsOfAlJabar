package ruinsofaljabarfinal;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends MazeObject {

	public Player(int x, int y) {
		super(x, y);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(getX(), getY(), this.getPanelSize(), this.getPanelSize());
	}
	
	public void moveUp() {
		setY(getY() - getPanelSize());
	}
	
	public void moveDown() {
		setY(getY() + getPanelSize());
	}
	
	public void moveLeft() {
		setX(getX() - getPanelSize());
	}
	
	public void moveRight() {
		setX(getX() + getPanelSize());
	}
}
