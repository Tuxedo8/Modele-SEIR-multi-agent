import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

public class Human {
	private char state;
	private double dE;
	private double dI;
	private double dR;
	private double dCurrent;
	private int x;
	private int y;
	private boolean tag = false;

	// Constructeur initialisé
	public Human(char state, int x, int y, MTRandom randint) {

		this.state = state;
		this.dE = randint.negExp(3);
		this.dI = randint.negExp(7);
		this.dR = randint.negExp(365);
		this.x = x;
		this.y = y;
	}

	// GETTERS
	public char getState() {
		return this.state;
	}

	public double getDE() {
		return this.dE;
	}

	public double getDI() {
		return this.dI;
	}

	public double getDR() {
		return this.dR;
	}

	public double getDCurrent() {
		return this.dCurrent;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public boolean getTag() {
		return this.tag;
	}

	// SETTERS
	public void setY(int newY) {
		if (newY >= 0 && newY < 300) {
			this.y = newY;
		} else {
			throw new IllegalArgumentException("Y negatif");
		}
	}

	public void setX(int newX) {
		if (newX >= 0 && newX < 300) {
			this.x = newX;
		} else {
			throw new IllegalArgumentException("X negatif");
		}
	}

	public void setDCurrent(double newDCurrent) {
		this.dCurrent = newDCurrent;
	}

	public void setDR(int newDR) {
		this.dR = newDR;
	}

	public void setDI(int newDI) {
		this.dI = newDI;
	}

	public void setDE(int newDE) {
		this.dE = newDE;
	}

	public void setState(char newState) {
		this.state = newState;
	}

	public void setTag(boolean newTag) {
		this.tag = newTag;
	}

}
