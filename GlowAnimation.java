import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
//joey sucks
import javax.swing.*;

@SuppressWarnings("serial")
//TODO: Change the name of the class from AnimationShell to the name of your class
public class GlowAnimation extends JPanel {

    //TODO: set the initial width and height of your image
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;

    //required global variables
    private BufferedImage image;
    private Graphics g;
    private Timer timer;
    private GameObject obj; //TODO: change this to whatever object(s) you are animating
    private String string;
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();

    //Constructor required by BufferedImage
    public GlowAnimation() {
        //set up Buffered Image and Graphics objects
        image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = image.getGraphics();



        //set up and start the Timer
        timer = new Timer(10, new TimerListener());
        timer.start();

    }

    //TimerListener class that is called repeatedly by the timer
    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            /* TODO: Move the objects that need to be animated
             * 		 Draw your ENTIRE scene
             * 		 Don't forget to call repaint!
             */
            background(g);

            //update positions of objects
            for (int i = 0; i < objects.size(); i++) {
                objects.get(i).setX(objects.get(i).getX());
            }

            repaint(); //leave this alone, it MUST  be the last thing in this method
        }

    }

    public void glowEffect(GameObject o, int intensity, int levels, int radius, Graphics g) {
        Color color = new Color(o.getColor().getRGB());
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        for (int i = 1; i < levels + 1; i++) {
            g.setColor(new Color(red, green, blue, intensity));
            g.fillOval((int) (o.getX() - radius), (int) (o.getY() - radius), (int) (o.getWidth() + (radius * 2)), (int) (o.getHeight() + (radius * 2)));
        }
    }


    public static void background(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.setPaint (new Color(0, 0, 0, 38));
        g2D.fillRect(0, 0, 1920, 1080);
    }

    //do not modify this
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

    //main method with standard graphics code
    public static void main(String[] args) {
        JFrame frame = new JFrame("Animation Shell");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new GlowAnimation()); //TODO: Change this to the name of your class!
        frame.setVisible(true);
    }

}
