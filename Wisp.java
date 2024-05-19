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

	public Wisp(double x, double y, double width, double height) {
		super(x, y, width, height);
		setThreshold(200);
		setSpeed(2);
	}
	
	public double calcDistance(double cubeX, double cubeY) {
		double xDistance = Math.abs(cubeX - getX());
		double yDistance = Math.abs(cubeY - getY());
		double out = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
		return out;
	}

	@Override
	public void draw(double cubeX, double cubeY, Graphics g) {
		move(cubeX, cubeY);
		
		if(calcDistance(cubeX, cubeY) > threshold) {
			double percentage = Math.min(calcDistance(cubeX, cubeY) / threshold, 1);
			setColor(new Color(255, (int) (255 * percentage), 0));
		}
		g.setColor(getColor());
		g.fillRect((int)(getX() - cubeX - getWidth()/2 + GlowAnimation.WIDTH), (int) (getY() - cubeY - getHeight() / 2), (int) (getWidth()), (int) (getHeight()));
	}
	
	public void move(double cubeX, double cubeY) {
		if(calcDistance(cubeX, cubeY) >= threshold) {
			if(cubeX > getX()) {
				setX(getX() - speed / 2);
			}
			else if(cubeX < getX()) {
				setX(getX() + speed / 2);
			}
			if(cubeY > getY()) {
				setY(getY() - speed / 2);
			}
			else if(cubeY < getY()) {
				setY(getY() + speed / 2);
			}

			// This can be shrunk with math
			// But for now

			// I will allow it
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
