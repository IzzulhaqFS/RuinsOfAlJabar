package ruinsofaljabar.ruinsobjects;

import java.awt.Graphics;

public abstract class RuinsObject {

	private int x;
	private int y;
	private static int panelSize;
	
	public RuinsObject(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public static int getPanelSize() {
		return panelSize;
	}
	
	public static void setPanelSize(int panelSize) {
		RuinsObject.panelSize = panelSize;
	}
	
	public boolean checkTopCollission(RuinsObject rObject) {
		return this.getX() == rObject.getX() && this.getY() - panelSize == rObject.getY();
	}
	
	public boolean checkBottomCollission(RuinsObject rObject) {
		return this.getX() == rObject.getX() && this.getY() + panelSize == rObject.getY();
	}
	
	public boolean checkRightCollission(RuinsObject rObject) {
		return this.getX() + panelSize == rObject.getX() && this.getY() == rObject.getY();
	}
	
	public boolean checkLeftCollission(RuinsObject rObject) {
		return this.getX() - panelSize == rObject.getX() && this.getY() == rObject.getY();
	}
	
	public abstract void draw(Graphics g);
}
