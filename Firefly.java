import java.awt.Color;
import java.awt.Graphics;

public class Firefly extends GameObject {


	public Firefly(double x, double y, double width, double height, double brightness, Color color) {
		setX(x);
		setY(-y);
		setWidth(width);
		setHeight(height);
		setBrightness(brightness);
		setColor(color);
	}

	public Firefly(double x, double y, double width, double height) {
		setX(x);
		setY(-y);
		setWidth(width);
		setHeight(height);
		// these can be changed in the future, only temporary rn
		setBrightness(3);
		setColor(Color.YELLOW);
	}
	
	public void draw(double cubeX, double cubeY, Graphics g) {
		g.setColor(getColor());
		g.fillRect((int)(getX() - cubeX - getWidth()/2 + GlowAnimation.WIDTH/2), (int)(getY() - cubeY - getHeight()/2 + GlowAnimation.HEIGHT/2), (int)(getWidth()), (int)(getHeight()));
	}
}
