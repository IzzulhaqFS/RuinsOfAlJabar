package ruinsofaljabarfinal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MazeBoard extends JPanel {

	private final int OFFSET = 30;
	private final int PANEL_SIZE = 40;
	
	private int columns, rows;
	private int playerX, playerY;
	private int finishX, finishY;
	private int[][] map;
	
	private ArrayList<Wall> walls;
	private ArrayList<Gate> gates;
	
	private Player player;
	
	boolean isCompleted = false;
	
	public MazeBoard(String str) {
		loadMap(str);
		initBoard();
	}
	
	private void loadMap(String str) {
		String file = loadFileAsString(str);
		String[] numbers = file.split("\\s+");
		
		this.columns = Integer.parseInt(numbers[0]);
		this.rows = Integer.parseInt(numbers[1]);
		this.playerX = Integer.parseInt(numbers[2]);
		this.playerY = Integer.parseInt(numbers[3]);
		this.finishX = Integer.parseInt(numbers[4]);
		this.finishY = Integer.parseInt(numbers[5]);
		this.map = new int[rows][columns];
		
		for(int row = 0; row < rows; row++) {
			for(int column = 0; column < columns; column++) {
				map[row][column] = Integer.parseInt(numbers[(column + (row * columns)) + 6]);
			}
		}
	}
	
	public static String loadFileAsString(String str) {
		File file = new File("./" + str + ".txt");
		
		String map = file.toString();
		
		StringBuilder sb = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(map));
			
			String line;
			
			while((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	private void initBoard() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		initWorld();
	}
	
	public int getBoardWidth() {
		return OFFSET + PANEL_SIZE * columns;
	}
	
	public int getBoardHeight() {
		return OFFSET + PANEL_SIZE * rows;
	}
	
	private void initWorld() {
		walls = new ArrayList<Wall>();
		gates = new ArrayList<Gate>();
		player = new Player(OFFSET + PANEL_SIZE * playerX, OFFSET + PANEL_SIZE * playerY);
		
		for(int row = 0; row < rows; row++) {
			for(int column = 0; column < columns; column++) {
				if(map[row][column] == 1) {
					walls.add(new Wall(OFFSET + PANEL_SIZE * column, OFFSET + PANEL_SIZE * row));
				}
				if(map[row][column] == 2) {
					gates.add(new Gate(OFFSET + PANEL_SIZE * column, OFFSET + PANEL_SIZE * row));
				}
			}
		}
	}
	
	private void buildWorld(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		for(Wall wall : walls) {
			wall.draw(g);
		}
		
		for(Gate gate: gates) {
			gate.draw(g);
		}
		
		player.draw(g);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		buildWorld(g);
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if(isCompleted) {
				
				return;
			}
			
			int key = e.getKeyCode();
			
			if(key == KeyEvent.VK_UP) {
				if(checkWallCollission(player, 1))
					return;
				if(checkGateCollission(player, 1))
					return;
				player.moveUp();
				repaint();
				mazeCompleted();
			}
			if(key == KeyEvent.VK_DOWN) {
				if(checkWallCollission(player, 2))
					return;
				if(checkGateCollission(player, 2))
					return;
				player.moveDown();
				repaint();
				mazeCompleted();
			}if(key == KeyEvent.VK_LEFT) {
				if(checkWallCollission(player, 3))
					return;
				if(checkGateCollission(player, 3))
					return;
				player.moveLeft();
				repaint();
				mazeCompleted();
			}if(key == KeyEvent.VK_RIGHT) {
				if(checkWallCollission(player, 4))
					return;
				if(checkGateCollission(player, 4))
					return;
				player.moveRight();
				repaint();
				mazeCompleted();
			}
		}
	}
	
	public boolean checkGateCollission(Player player, int typeCollission) {
		String answer;
		
		if(typeCollission == 1) {
			for(Gate gate : gates) {
				if(player.checkTopCollission(gate)) {
					if(gate.getStatusGate())
						return false;
					else {
						answer = JOptionPane.showInputDialog(gate.getQuestion());
						if(gate.checkAnswer(Integer.parseInt(answer))) {
							gate.openGate();
							return false;
						}
						else
							return true;
					}
				}
			}
			
			return false;
		}
		else if(typeCollission == 2) {
			for(Gate gate : gates) {
				if(player.checkBottomCollission(gate)) {
					if(gate.getStatusGate())
						return false;
					else {
						answer = JOptionPane.showInputDialog(gate.getQuestion());
						if(gate.checkAnswer(Integer.parseInt(answer))) {
							gate.openGate();
							return false;
						}
						else
							return true;
					}
				}
			}
			
			return false;
		}
		else if(typeCollission == 3) {
			for(Gate gate : gates) {
				if(player.checkLeftCollission(gate)) {
					if(gate.getStatusGate())
						return false;
					else {
						answer = JOptionPane.showInputDialog(gate.getQuestion());
						if(gate.checkAnswer(Integer.parseInt(answer))) {
							gate.openGate();
							return false;
						}
						else
							return true;
					}
				}
			}
			
			return false;
		}
		else if(typeCollission == 4) {
			for(Gate gate : gates) {
				if(player.checkRightCollission(gate)) {
					if(gate.getStatusGate())
						return false;
					else {
						answer = JOptionPane.showInputDialog(gate.getQuestion());
						if(gate.checkAnswer(Integer.parseInt(answer))) {
							gate.openGate();
							return false;
						}
						else
							return true;
					}
				}
			}
			
			return false;
		}
		else {
			return false;
		}
	}
	
	public boolean checkWallCollission(Player player, int typeCollission) {
		if(typeCollission == 1) {
			for(Wall wall : walls) {
				if(player.checkTopCollission(wall))
					return true;
			}
			
			return false;
		}
		else if(typeCollission == 2) {
			for(Wall wall : walls) {
				if(player.checkBottomCollission(wall))
					return true;
			}
			
			return false;
		}
		else if(typeCollission == 3) {
			for(Wall wall : walls) {
				if(player.checkLeftCollission(wall))
					return true;
			}
			
			return false;
		}
		else if(typeCollission == 4) {
			for(Wall wall : walls) {
				if(player.checkRightCollission(wall))
					return true;
			}
			
			return false;
		}
		else {
			return false;
		}
	}
	
	public void mazeCompleted() {
		int x = OFFSET + PANEL_SIZE * finishX;
		int y = OFFSET + PANEL_SIZE * finishY;
		
		if(player.getX() == x && player.getY() == y) {
			this.isCompleted = true;
			JOptionPane.showMessageDialog(null, "Congratulation!!! You have completed the maze.", "Game Finished", JOptionPane.INFORMATION_MESSAGE);
		}
		return;
	}
	
	public void restart() {
		walls.clear();
		gates.clear();
		
		initWorld();
		
		if(isCompleted)
			isCompleted = false;
	}
}
