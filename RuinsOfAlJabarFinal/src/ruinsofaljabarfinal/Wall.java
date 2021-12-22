package ruinsofaljabarfinal;

import java.awt.Color;
import java.awt.Graphics;

public class Wall extends MazeObject {

	public Wall(int x, int y) {
		super(x, y);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(getX(), getY(), this.getPanelSize(), this.getPanelSize());
	}
}
