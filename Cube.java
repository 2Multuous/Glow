import java.awt.*;
import java.awt.geom.AffineTransform;

public class Cube extends GameObject {
    private final int WIDTH;
    private final int HEIGHT;

    private int numFlares;

    public Cube(double x, double y, double direction, double width, Color color, int WIDTH, int HEIGHT) {
        setX(x);
        setY(y);
        setDirection(direction);
        setWidth(width);
        setColor(color);

        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

        numFlares = 0;
    }

    public Cube(double width, int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

        setX(0);
        setY(0);
        setWidth(width);
    }

    public void drawCube(int mouseX, int mouseY, boolean mouseDown, Graphics g) {
        move(mouseX, mouseY, mouseDown);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(getColor()); // Color: 5, 252, 248 ?
        AffineTransform old = g2d.getTransform();

        Rectangle rect = new Rectangle((int)(WIDTH/2 - getWidth()/2), (int)(HEIGHT/2 - getWidth()/2), (int)getWidth(), (int)getWidth());

        g2d.rotate((Math.atan2(mouseY, mouseX) + Math.PI/2), (double)WIDTH/2, (double)HEIGHT/2);

        g2d.draw(rect);
        g2d.fill(rect);

        g2d.setTransform(old);
    }

    public void move(int mouseX, int mouseY, boolean mouseDown) {
        setDirection(Math.atan2(mouseY, mouseX));

        if (mouseDown) {
            setX(getX() + Math.cos(getDirection()) * 10);
            setY(getY() + Math.sin(getDirection()) * 10);
        }
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

    // Dummy method
    @Override
    public void draw(double cubeX, double cubeY, Graphics g) {
        // THIS DOES NOTHING
    }
}
