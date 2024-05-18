import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

@SuppressWarnings("serial")
//TODO: Change the name of the class from AnimationShell to the name of your class
public class GlowAnimation extends JPanel {

    //TODO: set the initial width and height of your image
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    //required global variables
    private BufferedImage image;
    private Graphics g;
    private Timer timer;
    private GameObject obj; //TODO: change this to whatever object(s) you are animating
    private String string;

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

            g.setColor(Color.cyan);
            g.fillRect(200, 200, 10, 10);

            repaint(); //leave this alone, it MUST  be the last thing in this method
        }

    }

    public void glowEffect(GameObject o, int intensity, int radius, Graphics g) {
        g.setColor(o.getColor().brighter());
        for (int i = 0; i < intensity; i++) {
            g.setColor(g.getColor().darker());
            g.fillOval((int) (o.getX() - (o.getWidth() / 2) - (radius / 2)), (int) (o.getY() - (o.getHeight() / 2) - (radius / 2)), (int) o.getWidth() + radius, (int) o.getHeight() + (radius / 2));
        }
    }
    public static void background(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.setPaint (Color.black);
        g2D.fillRect(0, 0, 1600, 1000);
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
