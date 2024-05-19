import java.awt.*;
import java.awt.geom.AffineTransform;

public class Cube extends GameObject {
    private int numFlares;

    private double speed;

    public Cube(double x, double y, double direction, double width, Color color) {
        setX(x);
        setY(y);
        setDirection(direction);
        setWidth(width);
        setColor(color);

        numFlares = 0;
        speed = 4;
    }

    public Cube(double width) {
        setX(0);
        setY(0);
        setWidth(width);
    }

    public void drawCube(int mouseX, int mouseY, boolean mouseDown, Graphics g) {
        move(mouseX, mouseY, mouseDown);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(getColor()); // Color: 5, 252, 248 ?
        AffineTransform old = g2d.getTransform();

        Rectangle rect = new Rectangle((int)(GlowAnimation.WIDTH/2 - getWidth()/2), (int)(GlowAnimation.HEIGHT/2 - getWidth()/2), (int)getWidth(), (int)getWidth());

        g2d.rotate(-Math.atan2(mouseY, mouseX), (double)GlowAnimation.WIDTH/2, (double)GlowAnimation.HEIGHT/2);

        g2d.draw(rect);
        g2d.fill(rect);

        g2d.setTransform(old);
    }

    public void move(int mouseX, int mouseY, boolean mouseDown) {
        setDirection(-Math.atan2(mouseY, mouseX));

        if (mouseDown) {
            speed += 0.4;
            if (speed > 7) {
                speed = 7;
            }
        } else {
            speed *= 0.79;
        }
        setX(getX() + Math.cos(getDirection()) * speed);
        setY(super.getY() - Math.sin(getDirection()) * speed);
        System.out.println((int)getX() + ", " + (int)super.getY());
    }

    public void grow(int widthAdded) {
        setWidth(getWidth() + widthAdded);
    }

    public void shrink(int widthTaken) {
        setWidth(getWidth() - widthTaken);
    }

    public void pickupFlare() {
        numFlares++;
    }

    public void dropFlare() {
        numFlares--;
    }

    @Override
    public double getY() {
        return -super.getY();
    }

    // Dummy method
    @Override
    public void draw(double cubeX, double cubeY, Graphics g) {
        // THIS DOES NOTHING
    }
}
