package ruinsofaljabar.ruinsobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Gate extends RuinsObject {

	private boolean gateStatus = false;
	
	private int num1;
	private int num2;
	
	private char operator;
	
	public Gate(int x, int y) {
		super(x, y);
		
		Random rand = new Random();
		setNum1(rand.nextInt(10) + 1);
		setNum2(rand.nextInt(10) + 1);
		
		int cOperator = rand.nextInt(4);
		
		if(cOperator == 0)
			setOperator('+');
		else if(cOperator == 1)
			setOperator('-');
		else if(cOperator == 2)
			setOperator('*');
		else if(cOperator == 3)
			setOperator('/');
	}
	
	@Override
	public void draw(Graphics g) {
		if(isGateStatus())
			g.setColor(Color.WHITE);
		else
			g.setColor(Color.DARK_GRAY);
		
		g.fillRect(getX(), getY(), getPanelSize(), getPanelSize());
	}

	public boolean isGateStatus() {
		return gateStatus;
	}

	public void setGateStatus(boolean gateStatus) {
		this.gateStatus = gateStatus;
	}

	public void openGate() {
		setGateStatus(true);
	}

	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}

	public char getOperator() {
		return operator;
	}

	public void setOperator(char operator) {
		this.operator = operator;
	}
	
	public int getAnswer() {
		int answer = 0;
		
		if(operator == '+')
			answer = getNum1() + getNum2();
		else if(operator == '-')
			answer = getNum1() - getNum2();
		else if(operator == '*')
			answer = getNum1() * getNum2();
		else if(operator == '/')
			answer = getNum1() / getNum2();
		
		return answer;
	}
	
	public String getQuestion() {
		return String.format("%d %c %d = ?", this.getNum1(), this.getOperator(), this.getNum2());
	}
	
	public boolean checkAnswer(int answer) {
		if(answer == getAnswer())
			return true;
		else
			return false;
	}
}
