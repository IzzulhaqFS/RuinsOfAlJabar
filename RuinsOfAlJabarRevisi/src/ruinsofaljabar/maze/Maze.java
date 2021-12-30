package ruinsofaljabar.maze;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import ruinsofaljabar.ruinsobjects.Gate;
import ruinsofaljabar.ruinsobjects.Player;
import ruinsofaljabar.ruinsobjects.RuinsObject;
import ruinsofaljabar.ruinsobjects.Wall;

public class Maze extends JPanel {

	private final int OFFSET = 30;
	private final int PANEL_SIZE = 40;
	
	private int columns, rows;
	private int playerX, playerY;
	private int finishX, finishY;
	private int[][] map;
	
	private ArrayList<Wall> walls;
	private ArrayList<Gate> gates;
	
	private Player player;
	
	private boolean isCompleted = false;
	
	public Maze(String str) {
		RuinsObject.setPanelSize(PANEL_SIZE);
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
		int preferredWidth = getMazeWidth();
		int preferredHeight = getMazeHeight();
		
		setPreferredSize(new DimensionUIResource(preferredWidth, preferredHeight));
		setFocusable(true);
		initWorld();
	}
	
	private void initWorld() {
		walls = new ArrayList<Wall>();
		gates = new ArrayList<Gate>();
		player = new Player(PANEL_SIZE * playerX, PANEL_SIZE * playerY);
		
		for(int row = 0; row < rows; row++) {
			for(int column = 0; column < columns; column++) {
				if(map[row][column] == 1) {
					walls.add(new Wall(PANEL_SIZE * column, PANEL_SIZE * row));
				}
				if(map[row][column] == 2) {
					gates.add(new Gate(PANEL_SIZE * column, PANEL_SIZE * row));
				}
			}
		}
	}
	
	public int getMazeWidth() {
		return PANEL_SIZE * columns;
	}
	
	public int getMazeHeight() {
		return PANEL_SIZE * rows;
	}
	
	private void buildWorld(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		for(Wall wall : walls) {
			wall.draw(g);
		}
		
		for(Gate gate : gates) {
			gate.draw(g);
		}
		
		player.draw(g);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		buildWorld(g);
	}
	
	public void moveForward(int number) {
		for(int i = 0; i < number; i++) {
			System.out.println(player.getX() / PANEL_SIZE + ", " + player.getY() / PANEL_SIZE);
			if(map[player.getY() / PANEL_SIZE - 1][player.getX() / PANEL_SIZE] != 1 && map[player.getY() / PANEL_SIZE - 1][player.getX() / PANEL_SIZE] != -1) {
				player.moveUp();
				repaint();
				System.out.println("move up");
				if(map[player.getY() / PANEL_SIZE][player.getX() / PANEL_SIZE] == 2) {
					String answer = JOptionPane.showInputDialog(getQuestion());
					if(checkAnswer(Integer.parseInt(answer))) {
						map[player.getY() / PANEL_SIZE][player.getX() / PANEL_SIZE] = 1;
						map[player.getY() / PANEL_SIZE + 1][player.getX() / PANEL_SIZE] = -1;
						repaint();
					}
					else {
						player.moveDown();
						repaint();
						break;
					}
				}
				else {
					map[player.getY() / PANEL_SIZE + 1][player.getX() / PANEL_SIZE] = -1;
				}
				if(mazeCompleted()) {
					JOptionPane.showMessageDialog(null, "Congratulation!!! You have completed the maze.", "Game Finished", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			}
			else if(map[player.getY() / PANEL_SIZE + 1][player.getX() / PANEL_SIZE] != 1 && map[player.getY() / PANEL_SIZE + 1][player.getX() / PANEL_SIZE] != -1) {
				player.moveDown();
				repaint();
				System.out.println("move down");
				if(map[player.getY() / PANEL_SIZE][player.getX() / PANEL_SIZE] == 2) {
					String answer = JOptionPane.showInputDialog(getQuestion());
					if(checkAnswer(Integer.parseInt(answer))) {
						map[player.getY() / PANEL_SIZE][player.getX() / PANEL_SIZE] = 1;
						map[player.getY() / PANEL_SIZE - 1][player.getX() / PANEL_SIZE] = -1;
						repaint();
					}
					else {
						player.moveUp();
						repaint();
						break;
					}
				}
				else {
					map[player.getY() / PANEL_SIZE - 1][player.getX() / PANEL_SIZE] = -1;
				}
				if(mazeCompleted()) {
					JOptionPane.showMessageDialog(null, "Congratulation!!! You have completed the maze.", "Game Finished", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			}
			else if(map[player.getY() / PANEL_SIZE][player.getX() / PANEL_SIZE + 1] != 1 && map[player.getY() / PANEL_SIZE][player.getX() / PANEL_SIZE + 1] != -1) {
				player.moveRight();
				repaint();
				System.out.println("move right");
				if(map[player.getY() / PANEL_SIZE][player.getX() / PANEL_SIZE] == 2) {
					String answer = JOptionPane.showInputDialog(getQuestion());
					if(checkAnswer(Integer.parseInt(answer))) {
						map[player.getY() / PANEL_SIZE][player.getX() / PANEL_SIZE] = 1;
						map[player.getY() / PANEL_SIZE][player.getX() / PANEL_SIZE - 1] = -1;
						repaint();
					}
					else {
						player.moveLeft();
						repaint();
						break;
					}
				}
				else {
					map[player.getY() / PANEL_SIZE][player.getX() / PANEL_SIZE - 1] = -1;
				}
				if(mazeCompleted()) {
					JOptionPane.showMessageDialog(null, "Congratulation!!! You have completed the maze.", "Game Finished", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			}
			else if(map[player.getY() / PANEL_SIZE][player.getX() / PANEL_SIZE - 1] != 1 && map[player.getY() / PANEL_SIZE][player.getX() / PANEL_SIZE - 1] != -1) {
				player.moveLeft();
				repaint();
				System.out.println("move left");
				if(map[player.getY() / PANEL_SIZE][player.getX() / PANEL_SIZE] == 2) {
					String answer = JOptionPane.showInputDialog(getQuestion());
					if(checkAnswer(Integer.parseInt(answer))) {
						map[player.getY() / PANEL_SIZE][player.getX() / PANEL_SIZE] = 1;
						map[player.getY() / PANEL_SIZE][player.getX() / PANEL_SIZE + 1] = -1;
						repaint();
					}
					else {
						player.moveRight();
						repaint();
						break;
					}
				}
				else {
					map[player.getY() / PANEL_SIZE][player.getX() / PANEL_SIZE + 1] = -1;
				}
				if(mazeCompleted()) {
					JOptionPane.showMessageDialog(null, "Congratulation!!! You have completed the maze.", "Game Finished", JOptionPane.INFORMATION_MESSAGE);
					break;
				}
			}
		}
	}
	/*
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
	*/
	public boolean mazeCompleted() {
		int x = PANEL_SIZE * finishX;
		int y = PANEL_SIZE * finishY;
		
		if(player.getX() == x && player.getY() == y) {
			return true;			
		}
		
		return false;
	}
	
	public void restart() {
		walls.clear();
		gates.clear();
		
		initWorld();
		
		repaint();
		
		if(isCompleted)
			isCompleted = false;
	}
	
	private int answer = 0;
	
	public int getAnswer() {
		return answer;
	}
	
	public void setAnswer(int num1, int num2, char operator) {
		if(operator == '+')
			this.answer = num1 + num2;
		if(operator == '-')
			this.answer = num1 - num2;
		if(operator == '*')
			this.answer = num1 * num2;
		if(operator == '/')
			this.answer = num1 / num2;
	}
	
	public String getQuestion() {
		Random rand = new Random();
		
		int num1 = rand.nextInt(9) + 1;
		int num2 = rand.nextInt(9) + 1;
		int cNum = rand.nextInt(3) + 1;
		
		char operator = ' ';
		
		if(cNum == 1)
			operator = '+';
		else if(cNum == 2)
			operator = '-';
		else if(cNum == 3)
			operator = '*';
		else if(cNum == 4)
			operator = '/';
		
		setAnswer(num1, num2, operator);
		
		return String.format("%d %c %d = ?", num1, operator, num2);
	}
	
	public boolean checkAnswer(int answer) {
		return getAnswer() == answer;
	}
}
