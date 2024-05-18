import java.awt.Color;
import java.awt.Graphics;

public class Firefly extends GameObject {
	
	public Firefly(double x, double y, double width, double height, double brightness, Color color) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setBrightness(brightness);
		setColor(color);
	}
	
	public void draw(double cubeX, double cubeY, Graphics g) {
		g.setColor(getColor());
		g.fillRect((int)(getX()), (int)(getY()), (int)(getWidth()), (int)(getHeight()));
	}
}
