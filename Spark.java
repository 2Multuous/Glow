import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;



public class Spark {
    private int x;
    private int y;
    private int vx;
    private int vy;
    private double time;
    private int radius = 2;
    private Color color;
    private int width;
    private int height;

    public Spark(int x, int y) {
        this.x = x + (int) (Math.random() * 10000 - 5000);
        this.y = y + (int) (Math.random() * 10000 - 5000);
        this.vx = (int) (Math.random() * 9 - 4);
        this.vy = (int) (Math.random() * 9 - 4);
        this.time = (int) (Math.random() * 50) + 205;
        width = radius;
        height = radius;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public Color getColor() {
        return color;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getRadius() {
        return radius;
    }

    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
    }

    public void move() {
        x += vx;
        y += vy;
        time -= Math.random();
        if (time < 0) {
            time = 0;
        }
    }

    public double getTime() {
        return time;
    }

    public void draw(int cubeX, int cubeY, Graphics g) {
        if (time > 235) {
            color = new Color(255, (int) time, (int) time, (int) (time/255 * 85));
        } else if (time > 200) {
            color = new Color(255, (int) time, (int) time, 150 + (int) (time/255 * 85));
        } else {
            color = new Color(255, (int) time, (int) time, 100 + (int) (time/255 * 105));

        }

        g.setColor(color);
        g.fillOval(x - cubeX + (int) (Math.random() * 2) - 1, y - cubeY + (int) (Math.random() * 2) - 1, radius, radius);
    }

}
