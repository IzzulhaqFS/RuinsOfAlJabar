package ruinsofaljabarfinal;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainMenu extends JPanel {

	private GameDisplay displayController;
	private BufferedImage backgroundImage;
	private final JButton play = new JButton("PLAY GAME");
	private final JButton credit = new JButton("CREDIT");
	private final JButton quit = new JButton("QUIT");
	
	public MainMenu(GameDisplay displayController) {
		setFocusable(true);
        setLayout(null);

        int preferredWidth = 500;
        int preferredHeight = 500;

        System.out.println(preferredHeight + ", " + preferredWidth);
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));

        try {                
            this.backgroundImage = ImageIO.read(new File("./MainMenu.png"));
        } catch (IOException ex) {
            System.exit(1);
        }

        addButtons();
        this.displayController = displayController;
	}
	
	private void addButtons(){

        Dimension menuDimension = this.getPreferredSize();
        int buttonHeight = (int)(menuDimension.getHeight()/10);
        int buttonWidth = (int)(menuDimension.getWidth()/4);
        
        play.setBounds(155, 300, buttonWidth*3/2, buttonHeight);
        play.setSize(buttonWidth*3/2, buttonHeight);
        play.setFont(new Font("Arial", Font.BOLD, 20));
        
        play.setVisible(true);
        
        credit.setBounds(200, 370, buttonWidth, buttonHeight);
        credit.setSize(buttonWidth*3/4, buttonHeight*3/4);
        credit.setFont(new Font("Arial", Font.BOLD, 15));
        credit.setVisible(true);
        
        quit.setBounds(184, 410, buttonWidth, buttonHeight);
        quit.setSize(buttonWidth, buttonHeight);
        quit.setFont(new Font("Arial", Font.BOLD, 16));
        quit.setVisible(true);

        
        play.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                displayController.swapPanel("LevelSelector");
            }
        });
        
        credit.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                displayController.swapPanel("Credits");
                JOptionPane.showMessageDialog(null, "Created by :\n"
                		+ "Izzulhaq Fawwaz Syauqiy\n"
                		+ "Ahmad Hafizh Assa'ad", "Credits", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        quit.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                System.exit(1);
            }
        });
        
        add(play);
        add(credit);
        add(quit);
    }
	
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
        
    }
}
