package ruinsofaljabarfinal;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Gate extends MazeObject {

	private boolean statusGate = false;
	private int num1;
	private int num2;
	private char operator;
	
	public Gate(int x, int y) {
		super(x, y);
		
		Random rand = new Random();
		this.setNum1(rand.nextInt(10) + 1);
		this.setNum2(rand.nextInt(10) + 1);
		
		int cOperator = rand.nextInt(4);
		
		if(cOperator == 0)
			this.setOperator('+');
		else if(cOperator == 1)
			this.setOperator('-');
		else if(cOperator == 2)
			this.setOperator('*');
		else if(cOperator == 3)
			this.setOperator('/');
	}
	
	public void draw(Graphics g) {
		if(this.getStatusGate())
			g.setColor(Color.WHITE);
		else
			g.setColor(Color.DARK_GRAY);
		g.fillRect(getX(), getY(), this.getPanelSize(), this.getPanelSize());
	}
	
	public void openGate() {
		this.statusGate = true;
	}
	
	public boolean getStatusGate() {
		return this.statusGate;
	}
	
	public int getNum1() {
		return this.num1;
	}
	
	public void setNum1(int num1) {
		this.num1 = num1;
	}
	
	public int getNum2() {
		return this.num2;
	}
	
	public void setNum2(int num2) {
		this.num2 = num2;
	}
	
	public char getOperator() {
		return this.operator;
	}
	
	public void setOperator(char operator) {
		this.operator = operator;
	}
	
	public int getAnswer() {
		int answer = 0;
		
		if(operator == '+')
			answer = this.getNum1() + this.getNum2();
		else if(operator == '-')
			answer = this.getNum1() - this.getNum2();
		if(operator == '*')
			answer = this.getNum1() * this.getNum2();
		else if(operator == '/')
			answer = this.getNum1() / this.getNum2();
		
		return answer;
	}
	
	public String getQuestion() {
		return String.format("%d %c %d = ?", this.getNum1(), this.getOperator(), this.getNum2());
	}
	
	public boolean checkAnswer(int answer) {
		if(answer == this.getAnswer())
			return true;
		else
			return false;
	}
}
