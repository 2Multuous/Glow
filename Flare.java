import java.awt.*;

public class Flare extends GameObject {
    public Flare(double x, double y) {
        setX(x);
        setY(-y);
        setColor(Color.YELLOW);
    }

    public void drawFlare(double cubeX, double cubeY, double cubeWidth, Graphics g) {
        g.setColor(getColor());
        setWidth(cubeWidth);
        g.fillRect((int)(getX() - cubeX - getWidth()/2 + GlowAnimation.WIDTH/2), (int)(getY() - cubeY - getWidth()/2 + GlowAnimation.HEIGHT/2), (int)getWidth(), (int)getWidth());
    }

    // Dummy method
    @Override
    public void draw(double cubeX, double cubeY, Graphics g) {
        // THIS DOES NOTHING
    }
}
