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
        g2d.drawLine(WIDTH/2, HEIGHT/2, (int)(WIDTH/2 + 2000 * Math.cos(direction)), (int)(HEIGHT/2 + 2000 * Math.sin(direction)));
        g2d.setStroke(new BasicStroke(1));

        // Cool looking but non-revealing beam
//        Graphics2D g2d = (Graphics2D) g;
//
//        g2d.setColor(color);
//        Color c = new Color(color.getRGB());
//        int red = color.getRed();
//        int green = color.getGreen();
//        int blue = color.getBlue();
//        g2d.setStroke(new BasicStroke((int) width));
//        g2d.drawLine(WIDTH/2, HEIGHT/2, (int) (WIDTH/2 + 2000 * Math.cos(direction)), (int) (HEIGHT/2 + 2000 * Math.sin(direction)));
//        g2d.setColor(new Color(red + (255 - red) / 8 * 2, green + (255 - green) / 8 * 2, blue + (255 - blue) / 8 * 2));
//        g2d.setStroke(new BasicStroke((int) width / 2));
//        g2d.drawLine(WIDTH/2, HEIGHT/2, (int) (WIDTH/2 + 2000 * Math.cos(direction)), (int) (HEIGHT/2 + 2000 * Math.sin(direction)));
//        g2d.setColor(new Color(red + (255 - red) / 8 * 4, green + (255 - green) / 8 * 4, blue + (255 - blue) / 8 * 4));
//        g2d.setStroke(new BasicStroke((int) width / 4));
//        g2d.drawLine(WIDTH/2, HEIGHT/2, (int) (WIDTH/2 + 2000 * Math.cos(direction)), (int) (HEIGHT/2 + 2000 * Math.sin(direction)));
//        g2d.setColor(new Color(red + (255 - red) / 8 * 8, green + (255 - green) / 8 * 8, blue + (255 - blue) / 8 * 8));
//        g2d.setStroke(new BasicStroke((int) width / 8));
//        g2d.drawLine(WIDTH/2, HEIGHT/2, (int) (WIDTH/2 + 2000 * Math.cos(direction)), (int) (HEIGHT/2 + 2000 * Math.sin(direction)));
//        g2d.setStroke(new BasicStroke(1));
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
