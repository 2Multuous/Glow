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

        scene = "menu";

        objects = new ArrayList<GameObject>();
        mouseX = 0;
        mouseY = 0;

        cube = new Cube(0, 0, 0.0, 20, new Color(5, 252, 248), WIDTH, HEIGHT);
        beam = new Beam(cube.getDirection(), new Color(200, 255, 250, 60), WIDTH, HEIGHT);

        for (int i = 0; i < 100; i++) {
            objects.add(new Firefly((int)(Math.random() * 10000 + 1000), (int)(Math.random() * 10000 + 1000), 5, 5));
        }

        for (int i = 0; i < 200; i++) {
            objects.add(new Firefly((int)(Math.random() * WIDTH), -(int)(Math.random() * (HEIGHT)), 5, 5));
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
            if(scene.equals("game")) {
                background(g);
    
                beam.setDirection(cube.getDirection());
                beam.setWidth(cube.getWidth());
                beam.draw(g);
                cube.drawCube(mouseX, mouseY, mouseDown, g);
    
                for (GameObject object : objects) {
    //                if (isInBeam(object)) {
    //                    object.draw(cube.getX(), cube.getY(), g);
    ////                    glowEffect(object, 10, 4, 40);
    //                }
                    if (isInBeam(object)) {
                        object.draw(cube.getX(), cube.getY(), g);
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
            repaint();
        }
    }

    public boolean isInBeam(GameObject object) {
        double slope = Math.tan(cube.getDirection());
        for (int i = 0; i < WIDTH/2; i++) {
            double x = 0;
            double y = 0;
            if (cube.getDirection() > -Math.PI/2 && cube.getDirection() < Math.PI/2) {
                x = (double)WIDTH/2 + i + cube.getX();
                y = (double)HEIGHT/2 + slope * i + cube.getY();
            } else {
                x = (double)WIDTH/2 - i + cube.getX();
                y = (double)HEIGHT/2 - slope * i + cube.getY();
            }
            if (Math.hypot(object.getX() - x, object.getY() - y) < beam.getWidth()/2) {
                return true;
            }
        }
        return false;
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
