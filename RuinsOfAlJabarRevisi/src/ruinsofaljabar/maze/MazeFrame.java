package ruinsofaljabar.maze;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MazeFrame {

	private JFrame frame;
	
	private JButton mainMenu;
	private JButton restart; 
	private JButton roll;
	
	private JPanel menuBox;
	private JPanel menuBoxPadding;
	
	private Maze maze;
	
	private Random random;
	
	public MazeFrame(String str) {
		frame = new JFrame("Ruins of Al-Jabar v0.1.0");
		
		random = new Random();
		
		maze = new Maze(str);
		
		mainMenu = new JButton("Main Menu");
		mainMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		restart = new JButton("Restart");
		restart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				maze.restart();
			}
		});
		
		roll = new JButton("Roll");
		roll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int diceNumber = random.nextInt(5) + 1;
				System.out.println(diceNumber);
				maze.moveForward(diceNumber);
			}
		});
		
		menuBox = new JPanel();
		menuBox.setLayout(new GridLayout(1, 1, 1, 1));
		menuBoxPadding = new JPanel();
		menuBoxPadding.setLayout(new FlowLayout(FlowLayout.LEADING, 1, 1));

		menuBox.add(mainMenu);
		menuBox.add(restart);
		menuBox.add(roll);
		menuBoxPadding.add(menuBox);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(menuBoxPadding, BorderLayout.NORTH);
		frame.getContentPane().add(maze, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	/*
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				MazeFrame maze = new MazeFrame("level_1");
			}
		});
	}*/
}
