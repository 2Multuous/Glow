
public abstract class GameObject {
	private int width;
	private int x;
	private int y;
	private double direction;
	private int brightness;
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public double getDirection() {
		return direction;
	}
	
	public void setDirection(double direction) {
		this.direction = direction;
	}
	
	public int getBrightness() {
		return brightness;
	}
	
	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}
}
