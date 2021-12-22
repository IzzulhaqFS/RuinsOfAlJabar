package ruinsofaljabarfinal;

import javax.swing.JFrame;

public class MazeFrame extends JFrame {

	private final int OFFSET = 30;
	
	public MazeFrame(String str) {
		MazeBoard board = new MazeBoard(str);
		add(board);
		
		setTitle("Ruins of Al Jabar v1.0");
		setResizable(false);
		setSize(board.getBoardWidth() + OFFSET + 10, board.getBoardHeight() + 2 * OFFSET + 10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
