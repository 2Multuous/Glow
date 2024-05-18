import java.awt.Color;
import java.awt.Graphics;

public class FlareFly extends Firefly {
	private boolean consumed;
	
	public FlareFly(double x, double y, double width, double height) {
		super(x, y, width, height);
		setColor(Color.ORANGE);
	}
	
	public void draw(double cubeX, double cubeY, Graphics g) {
		if(calcDistance(cubeX, cubeY) < 10) {
			setConsumed(true);
			setColor(Color.BLACK);
		}
		else if(!isConsumed()){
			g.fillRect((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
		}
	}
	
	public double calcDistance(double cubeX, double cubeY) {
		double xDistance = Math.abs(cubeX - getX());
		double yDistance = Math.abs(cubeY - getY());
		double out = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
		return out;
	}

	public boolean isConsumed() {
		return consumed;
	}

	public void setConsumed(boolean consumed) {
		this.consumed = consumed;
	}
}
