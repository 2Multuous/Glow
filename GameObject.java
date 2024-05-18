
public abstract class GameObject {
	private double width;
	private double height;
	private double x;
	private double y;
	private double direction;
	private double brightness;
	
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
}
