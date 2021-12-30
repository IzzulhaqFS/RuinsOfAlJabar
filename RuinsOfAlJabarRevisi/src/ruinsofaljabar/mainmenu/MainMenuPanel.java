package ruinsofaljabar.mainmenu;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ruinsofaljabar.maze.MazeFrame;

public class MainMenuPanel extends JPanel {

	private BufferedImage backgroundImage;
	
	private JButton play;
	private JButton credit;
	private JButton quit;
	
	private JComboBox<String> levelListBox;
	
	private ArrayList<String> levelList = new ArrayList<String>();
	
	public MainMenuPanel() {
		int preferredWidth = 500;
		int preferredHeight = 500;
		
		setFocusable(true);
		setLayout(null);
		setPreferredSize(new Dimension(preferredWidth, preferredHeight));
		
		try {
			backgroundImage = ImageIO.read(new File("./MainMenu.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		addButtons();
	}
	
	private void addButtons() {
		Dimension menuDimension = this.getPreferredSize();
		int buttonWidth = (int)(menuDimension.getWidth()/4); //125
		int buttonHeight = (int)(menuDimension.getHeight()/10); //50
		
		getLevelList();
		levelListBox = new JComboBox<String>(levelList.toArray(new String[levelList.size()]));
		levelListBox.setBounds(120, 300, buttonWidth, buttonHeight);
		levelListBox.setVisible(true);
		
		play = new JButton("Play Game");
		play.setBounds(255, 300, buttonWidth, buttonHeight);
		play.setSize(buttonWidth, buttonHeight);
		play.setFont(new Font("Arial", Font.BOLD, 20));
		play.setVisible(true);
		play.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				new MazeFrame(levelListBox.getSelectedItem().toString());
			}
			
		});
		
		credit = new JButton("Credit");
		credit.setBounds(200, 360, buttonWidth, buttonHeight);
		credit.setSize(buttonWidth * 3 / 4, buttonHeight * 3 / 4);
		credit.setFont(new Font("Arial", Font.BOLD, 15));
		credit.setVisible(true);
		credit.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "Created by :\n"
                		+ "Izzulhaq Fawwaz Syauqiy\n"
                		+ "Ahmad Hafizh Assa'ad", "Credits", JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		
		quit = new JButton("Quit");
		quit.setBounds(188, 410, buttonWidth, buttonHeight);
		quit.setSize(buttonWidth, buttonHeight);
		quit.setFont(new Font("Arial", Font.BOLD, 16));
		quit.setVisible(true);
		quit.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int confirmation = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exit Option", JOptionPane.YES_NO_OPTION);
				if(confirmation == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
			
		});
		
		add(levelListBox);
		add(play);
		add(credit);
		add(quit);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backgroundImage, 0, 0, null);
	}
	
	private void getLevelList() {
		for(int i = 1; i <= 99; i++) {
			File map = new File("./level_" + i + ".txt");
			
			if(map.exists()) {
				levelList.add("level_" + i);
			}
		}
	}
}
