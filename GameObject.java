import java.awt.Color;
import java.awt.Graphics;

public abstract class GameObject {
	private double width;
	private double height;
	private double x;
	private double y;
	private double direction;
	private double brightness;
	private Color color;
	
	public double getWidth() {
		return width;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getDirection() {
		return direction;
	}
	
	public void setDirection(double direction) {
		this.direction = direction;
	}
	
	public double getBrightness() {
		return brightness;
	}
	
	public void setBrightness(double brightness) {
		this.brightness = brightness;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public abstract void draw(double cubeX, double cubeY, Graphics g);
}
