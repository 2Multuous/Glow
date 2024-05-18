import java.awt.Color;
import java.awt.Graphics;

public class Wisp extends Firefly{

	private double threshold;
	private double speed;
	
	public Wisp(double x, double y, double width, double height, double brightness, Color color, double threshold, double speed) {
		super(x, y, width, height, brightness, color);
		setThreshold(threshold);
		setSpeed(speed);
	}
	
	public double calcDistance(double cubeX, double cubeY) {
		double xDistance = Math.abs(cubeX - getX());
		double yDistance = Math.abs(cubeY - getY());
		double out = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
		return out;
	}

	@Override
	public void draw(double cubeX, double cubeY, Graphics g) {
		double percentage = calcDistance(cubeX, cubeY) / threshold;
		setColor(new Color(255, (int) (255 * percentage), 0));
		g.setColor(getColor());
		g.fillRect((int)(getX()), (int) (getY()), (int) (getWidth()), (int) (getHeight()));
	}
	
	public void move(double cubeX, double cubeY) {
		if(calcDistance(cubeX, cubeY) <= threshold) {
			if(cubeX > getX()) {
				setX(getX() + speed / 2);
			}
			else if(cubeX < getX()) {
				setX(getX() - speed / 2);
			}
			if(cubeY > getY()) {
				setY(getY() + speed / 2);
			}
			else if(cubeY < getY()) {
				setY(getY() - speed / 2);
			}
		}
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
}
