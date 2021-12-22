package ruinsofaljabarfinal;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameDisplay {

	private JPanel cards;
	private JFrame mainFrame;
	
	public GameDisplay() {
		createAndShowGUI();
	}
	
	private void createAndShowGUI() {
		
		mainFrame = new JFrame("Ruins of Al-Jabar");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cards = new JPanel(new CardLayout());
		
		cards.add(new MainMenu(this), "MainMenu");
		cards.add(new LevelSelector(this), "LevelSelector");
//		cards.add(new MazeBoard(this));
		swapPanel("MainMenu");
		
		mainFrame.getContentPane().add(cards);
		mainFrame.pack();
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
	}
	
	public void swapPanel(String panelName) {
		CardLayout cl = (CardLayout)(cards.getLayout());
		
		cl.show(cards, panelName);
		
		for(Component c : cards.getComponents()) {
			if(c.isVisible()) {
				c.setFocusable(true);
				c.requestFocusInWindow();
				cards.setPreferredSize(c.getPreferredSize());
				mainFrame.pack();
			}
		}
		System.out.println("Switching to " + panelName);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				GameDisplay g = new GameDisplay();
			}
		});
	}
}
