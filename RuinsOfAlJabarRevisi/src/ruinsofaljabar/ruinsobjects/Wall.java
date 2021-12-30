package ruinsofaljabar.ruinsobjects;

import java.awt.Color;
import java.awt.Graphics;

public class Wall extends RuinsObject {

	public Wall(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(getX(), getY(), getPanelSize(), getPanelSize());
	}

}
