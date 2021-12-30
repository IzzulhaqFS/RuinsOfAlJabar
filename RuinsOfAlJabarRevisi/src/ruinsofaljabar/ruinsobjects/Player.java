package ruinsofaljabar.ruinsobjects;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends RuinsObject {

	public Player(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(getX(), getY(), getPanelSize(), getPanelSize());
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
