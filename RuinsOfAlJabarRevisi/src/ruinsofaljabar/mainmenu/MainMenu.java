package ruinsofaljabar.mainmenu;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu {

	private JFrame menuFrame;
	
	public MainMenu() {
		menuFrame = new JFrame("Ruins of Al-Jabar v0.1.0");
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.getContentPane().add(new MainMenuPanel());
		menuFrame.pack();
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setResizable(false);
		menuFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				MainMenu menu = new MainMenu();
			}
		});
	}
}
