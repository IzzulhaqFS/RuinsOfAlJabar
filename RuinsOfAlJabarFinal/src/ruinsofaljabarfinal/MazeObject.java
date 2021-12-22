package ruinsofaljabarfinal;

//import javax.swing.JPanel;

public class MazeObject {

	private final int PANEL_SIZE = 40;
	//private final int OFFSET = 30;
	
	private int x, y;
	
	public MazeObject(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getPanelSize() {
		return this.PANEL_SIZE;
	}
	
	/*public int getOffset() {
		return this.OFFSET;
	}*/
	
	public boolean checkTopCollission(MazeObject mObject) {
		return this.getX() == mObject.getX() && this.getY() - PANEL_SIZE == mObject.getY();
	}
	
	public boolean checkBottomCollission(MazeObject mObject) {
		return this.getX() == mObject.getX() && this.getY() + PANEL_SIZE == mObject.getY();
	}
	
	public boolean checkLeftCollission(MazeObject mObject) {
		return this.getX() - PANEL_SIZE == mObject.getX() && this.getY() == mObject.getY();
	}
	
	public boolean checkRightCollission(MazeObject mObject) {
		return this.getX() + PANEL_SIZE == mObject.getX() && this.getY() == mObject.getY();
	}
}
