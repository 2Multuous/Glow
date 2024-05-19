import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
//joey sucks
import javax.swing.*;

@SuppressWarnings("serial")
public class GlowAnimation extends JPanel {
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;

    // required global variables
    private BufferedImage image;
    private Graphics g;
    private Timer timer;

    private GameObject obj;
    private String string;
    private ArrayList<Firefly> fireflies;
    private ArrayList<FlareFly> flareflies;
    private ArrayList<Wisp> wisps;
    private ArrayList<Flare> flares;
    private Cube cube;
    private int mouseX;
    private int mouseY;
    private boolean mouseDown;
    private Mouse mouse;
    private Beam beam;
    private String scene;
    private Input input;

    // For testing
    private boolean drawAll;

    // Constructor required by BufferedImage
    public GlowAnimation() {
        //set up Buffered Image and Graphics objects
        image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = image.getGraphics();

        scene = "menu";

        fireflies = new ArrayList<>();
        flareflies = new ArrayList<>();
        wisps = new ArrayList<>();
        flares = new ArrayList<>();

        mouseX = 0;
        mouseY = 0;

        cube = new Cube(0, 0, 0.0, 20, new Color(5, 252, 248));
        beam = new Beam(cube.getDirection(), new Color(200, 255, 250, 60));

        for (int i = 0; i < 500; i++) {
            int r = (int)(Math.random() * 10000 + WIDTH/2);
            double theta = Math.random() * Math.PI * 2;
            fireflies.add(new Firefly((int)(r * Math.cos(theta)), (int)(r * Math.sin(theta)), 5, 5));
        }

        timer = new Timer(10, new TimerListener());
        timer.start();

        mouse = new Mouse();
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);

        input = new Input();
        setFocusable(true);
        this.addKeyListener(input);
    }

    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(scene.equals("game")) {
                background(g);

                // Beam
                beam.setDirection(cube.getDirection());
                beam.setWidth(cube.getWidth());
                beam.draw(g);

                // Cube
                cube.drawCube(mouseX, mouseY, mouseDown, g);

                // Fireflies
                int r = (int)(Math.random() * 10000 + WIDTH/2);
                double theta = Math.random() * Math.PI * 2;
                if (hasSpace(r * Math.cos(theta) + cube.getX(), r * Math.sin(theta) + cube.getY(), 200, new ArrayList<GameObject>(fireflies))) {
                    fireflies.add(new Firefly((int)(r * Math.cos(theta) + cube.getX()), (int)(r * Math.sin(theta) + cube.getY()), 5, 5));
                }

                // TODO: add new fireflies in random pos around player if there's no others in 500 px radius
                // TODO: remove fireflies when too far (~10000, 5000 px?)
                for (int i = fireflies.size() - 1; i >= 0; i--) {
                    if (isInBeam(fireflies.get(i)) || drawAll) {
                        fireflies.get(i).draw(cube.getX(), cube.getY(), g);
                    }
                    if (distance(cube.getX(), cube.getY(), fireflies.get(i).getX(), fireflies.get(i).getY()) > WIDTH) {
                        fireflies.remove(i);
                    }
                }


                g.setColor(Color.RED);
            }
            else if(scene.equals("menu")) {
                drawMenu(g);
            }
            else if(scene.equals("intro")) {
                drawIntro(g);
            }
            else if(scene.equals("pause")) {
                drawPause(g);
            }
            repaint();
        }
    }

    public boolean hasSpace(double x, double y, double radius, ArrayList<GameObject> objects) {
        for (GameObject object : objects) {
            if (distance(x, y, object.getX(), object.getY()) < radius) {
                return false;
            }
        }
        return true;
    }

    public boolean isInBeam(GameObject object) {
        double slope = Math.tan(cube.getDirection());
        for (int i = 0; i < WIDTH/2; i++) {
            double x = 0;
            double y = 0;
            if (cube.getDirection() > -Math.PI/2 && cube.getDirection() < Math.PI/2) {
                x = i + cube.getX();
                y = slope * i + cube.getY();
            } else {
                x = -i + cube.getX();
                y = -slope * i + cube.getY();
            }
            if (Math.hypot(object.getX() - x, object.getY() - y) < beam.getWidth()/2) {
                return true;
            }
        }
        return false;
    }

    public double distance(double x1, double y1, double x2, double y2) {
        return Math.hypot(x2 - x1, y2 - y1);
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

    // using this to manage intro stuff and pause menu
    private class Input implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub
            System.out.println("Key pressed " + e.getKeyCode());
            if(scene.equals("menu")) {
                if(e.getKeyCode() == 80) {
                    scene = "intro";
                }
            }
            else if(scene.equals("intro")) {
                if(e.getKeyCode() == 67) {
                    scene = "game";
                }
            }
            else if(scene.equals("game")) {
                if(e.getKeyCode() == 80) {
                    scene = "pause";
                }
            }
            else if(scene.equals("pause")) {
                if(e.getKeyCode() == 80) {
                    scene = "game";
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub

        }

    }

    public static void drawMenu(Graphics g) {
        background(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Comic Sans", Font.BOLD, WIDTH / 8));
        g.drawString("GLOW", HEIGHT / 2 + 25, 400);
        g.setFont(new Font("Comic Sans", Font.BOLD, WIDTH / 24));
        g.drawString("Press [p] to play", HEIGHT / 2 + 85, 600);
    }

    public static void drawIntro(Graphics g) {
        background(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Comic Sans", Font.BOLD, WIDTH / 24));
        g.drawString("Controls:", 100, 200);
        g.drawString("Left click to move", 100, 300);
        g.drawString("The player will move in the direction", 100, 400);
        g.drawString("of the cursor", 100, 500);
        g.drawString("press [p] to pause", 100, 600);
        g.drawString("press [c] to continue", 100, 700);
    }

    public static void drawPause(Graphics g) {
        background(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Comic Sans", Font.BOLD, WIDTH / 8));
        g.drawString("PAUSED", HEIGHT / 2 - 70, 400);
        g.setFont(new Font("Comic Sans", Font.BOLD, WIDTH / 24));
        g.drawString("Press [p] to resume", HEIGHT / 2 + 55, 600);
    }

//    public void glowEffect(GameObject o, int intensity, int levels, int radius) {
//        Color color = new Color(o.getColor().getRGB());
//        int red = color.getRed();
//        int green = color.getGreen();
//        int blue = color.getBlue();
//        for (int i = 1; i < levels + 1; i++) {
//            g.setColor(new Color(red + (255 - red) / levels * i, green + (255 - green) / levels * i, blue + (255 - blue) / levels * i, intensity));
//            g.fillOval((int) (o.getX() - radius / levels * i), (int) (o.getY() - radius / levels * i), (int) (o.getWidth() + (radius * 2 / levels * i)), (int) (o.getHeight() + (radius * 2 / levels * i)));
//        }
//    }


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
