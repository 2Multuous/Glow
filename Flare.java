import java.awt.*;

public class Flare extends GameObject {
    private boolean consumed;

    public Flare(int x, int y) {
        setX(x);
        setY(-y);
        setWidth(4);
        consumed = false;
    }

    @Override
    public void draw(double cubeX, double cubeY, Graphics g) {
        g.setColor(getColor());
        g.drawRect((int)(getX() - cubeX - getWidth()/2 + GlowAnimation.WIDTH/2), (int)(getY() - cubeY - getWidth()/2 + GlowAnimation.HEIGHT/2), (int)getWidth(), (int)getWidth());
    }

    public void consume() {
        consumed = true;
    }
}
