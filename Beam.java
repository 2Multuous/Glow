import java.awt.*;

public class Beam {
    private final int WIDTH;
    private final int HEIGHT;

    private double direction;
    private double width;
    private Color color;

    public Beam(double direction, Color color, int WIDTH, int HEIGHT) {
        this.direction = direction;
        this.color = color;

        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(color);
        g2d.setStroke(new BasicStroke((int)width));
        g2d.drawLine(WIDTH/2, HEIGHT/2, (int)(WIDTH/2 + 2000 * Math.cos(Math.toRadians(direction))), (int)(HEIGHT/2 + 2000 * Math.sin(Math.toRadians(direction))));
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
