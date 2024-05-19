import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
//joey sucks
import javax.swing.*;

@SuppressWarnings("serial")
public class GlowAnimation extends JPanel {
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;

    // required global variables
    private BufferedImage image;
    private Graphics g;
    private Timer timer;

    private GameObject obj;
    private String string;
    private ArrayList<GameObject> objects;
    private Cube cube;
    private int mouseX;
    private int mouseY;
    private boolean mouseDown;
    private Mouse mouse;
    private Beam beam;
    private Firefly f;

    // Constructor required by BufferedImage
    public GlowAnimation() {
        //set up Buffered Image and Graphics objects
        image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = image.getGraphics();

        objects = new ArrayList<GameObject>();
        mouseX = 0;
        mouseY = 0;

        cube = new Cube(0, 0, 0.0, 20, new Color(5, 252, 248), WIDTH, HEIGHT);
        beam = new Beam(cube.getDirection(), new Color(200, 255, 250, 60), WIDTH, HEIGHT);

        for (int i = 0; i < 100; i++) {
            objects.add(new Firefly((int)(Math.random() * 10000 + 1000), (int)(Math.random() * 10000 + 1000), 5, 5));
        }

        timer = new Timer(10, new TimerListener());
        timer.start();

        mouse = new Mouse();
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            background(g);

            beam.setDirection(cube.getDirection());
            beam.setWidth(cube.getWidth());
            beam.draw(g);
            cube.drawCube(mouseX, mouseY, mouseDown, g);

            for (GameObject object : objects) {
                object.draw(cube.getX(), cube.getY(), g);
            }

            g.setColor(Color.RED);

            repaint();
        }
    }

    private class Mouse implements MouseListener, MouseMotionListener {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            mouseDown = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mouseDown = false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            mouseX = e.getX() - WIDTH/2;
            mouseY = -(e.getY() - HEIGHT/2);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mouseX = e.getX() - WIDTH/2;
            mouseY = -(e.getY() - HEIGHT/2);
        }
    }

    //ben was here

    public void glowEffect(GameObject o, int intensity, int levels, int radius, Graphics g) {
        Color color = new Color(o.getColor().getRGB());
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        for (int i = 1; i < levels + 1; i++) {
            g.setColor(new Color(red + (255 - red) / levels * i, green + (255 - green) / levels * i, blue + (255 - blue) / levels * i, intensity));
            g.fillOval((int) (o.getX() - radius / levels * i), (int) (o.getY() - radius / levels * i), (int) (o.getWidth() + (radius * 2 / levels * i)), (int) (o.getHeight() + (radius * 2 / levels * i)));
        }
    }


    public static void background(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.setPaint (new Color(0, 0, 0));
        g2D.fillRect(0, 0, WIDTH, HEIGHT);
    }

    //do not modify this
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

    //main method with standard graphics code
    public static void main(String[] args) {
        JFrame frame = new JFrame("Glow");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(-8, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new GlowAnimation());
        frame.setVisible(true);
    }

}
