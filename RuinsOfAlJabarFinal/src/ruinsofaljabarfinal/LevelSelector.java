package ruinsofaljabarfinal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LevelSelector extends JPanel {

//	private String selectedLevel; 
	
	private GameDisplay displayController;
    private BufferedImage backgroundImage;
    final private JButton back = new JButton("Back");
    final private JButton one = new JButton("level_1");
    final private JLabel label = new JLabel("LEVEL SELECTOR");
    
	public LevelSelector(GameDisplay displayController){
        setFocusable(true);
        setLayout(null);

        int preferredWidth = 500;
        int preferredHeight = 500;

        System.out.println(preferredHeight + ", " + preferredWidth);
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));

        try {                
            this.backgroundImage = ImageIO.read(new File("./LevelSelector.png"));
        } catch (IOException ex) {
            System.exit(1);
        }
        
        addButtons();
        this.displayController = displayController;

    }
	
	private void addButtons(){
        Dimension menuDimension = this.getPreferredSize();
        int buttonHeight = (int)(menuDimension.getHeight()/10);
        int buttonWidth = (int)(menuDimension.getHeight()/4);
        
        
        back.setBounds(23, 38, 0, 0);
        back.setSize(buttonWidth*57/100, buttonHeight*8/10);
        back.setFont(new Font("Arial", Font.BOLD, 15 ));
        back.setBorder(null);
        back.setContentAreaFilled(false);
        
        one.setBounds(50, 225, 0, 0);
        one.setSize(buttonWidth*1/2, buttonHeight*2/3);
        one.setFont(new Font("Arial", Font.BOLD, 15 ));
        
        
        back.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                displayController.swapPanel("MainMenu");
            }
        });
        
        one.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
            	new MazeFrame("level_1");
//            	displayController.swapPanel(""); //swappanel untuk ke labirinnya
            }
        });
        
        add(back);
        add(one);
    }
	
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
        
        Dimension menuDimension = this.getSize();
        int titleHeight = (int)(menuDimension.getHeight()/10);
        int titleWidth = (int)(menuDimension.getWidth()/2);
        Font titleFont = new Font("arial", Font.BOLD, 26);
       
        Rectangle title = new Rectangle(130, 23, titleWidth, titleHeight);
        centreString(g, title, "S E L E C T  L E V E L", titleFont, Color.BLACK);

    }
	
	//centre string inside rectangle
    //from http://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java
	private void centreString(Graphics g, Rectangle r, String s, Font font, Color c) {
        FontRenderContext frc = new FontRenderContext(null, true, true);

        Rectangle2D r2D = font.getStringBounds(s, frc);
        int rWidth = (int) Math.round(r2D.getWidth());
        int rHeight = (int) Math.round(r2D.getHeight());
        int rX = (int) Math.round(r2D.getX());
        int rY = (int) Math.round(r2D.getY());

        int a = (r.width / 2) - (rWidth / 2) - rX;
        int b = (r.height / 2) - (rHeight / 2) - rY;
        g.setColor(c);
        g.setFont(font);
        g.drawString(s, r.x + a, r.y + b);
    }
	
//	public String getSelectedLevel() {
//		return this.selectedLevel;
//	}
//	
//	public void setSelectedLevel() {
//		this.selectedLevel = one.getText();
//	}
}
