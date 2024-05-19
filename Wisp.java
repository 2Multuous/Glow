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
		setColor(Color.YELLOW);
	}

	@Override
	public void draw(double cubeX, double cubeY, Graphics g) {
		move(cubeX, cubeY);

		if(calcDistance(cubeX, cubeY) < threshold) {
			double percentage = Math.min(calcDistance(cubeX, cubeY) / threshold, 1);
			setColor(new Color(255, (int) (255 * percentage), 0));
		} else {
			setColor(Color.YELLOW);
		}
		g.setColor(getColor());
		g.fillRect((int)(getX() - cubeX - getWidth()/2 + GlowAnimation.WIDTH/2), (int)(getY() - cubeY - getHeight()/2 + GlowAnimation.HEIGHT/2), (int) (getWidth()), (int) (getHeight()));
	}

	public double calcDistance(double cubeX, double cubeY) {
		return Math.hypot(getX() - cubeX, getY() - cubeY);
	}

	public void move(double cubeX, double cubeY) {
		if(calcDistance(cubeX, cubeY) < threshold) {
			speed += 0.1;

			double direction = Math.atan2(getY() - cubeY, getX() - cubeX);

			setX(getX() - Math.cos(direction) * speed);
			setY(getY() - Math.sin(direction) * speed);

			if (speed > 4.5) {
				speed = 4.5;
			}
		} else {
			speed = 0;
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
