import java.awt.*;

public class Void extends GameObject {
    private double speed;

    public Void(double x, double y, double width, double speed) {
        setX(x);
        setY(-y);
        setWidth(width);
        this.speed = speed;
        setColor(Color.BLACK);
    }

    public void drawVoid(double cubeX, double cubeY, double flareX, double flareY, boolean isFollowingFlare, double cubeSpeed, Graphics g) {
        if (!isFollowingFlare) {
            move(cubeX, cubeY, flareX, flareY, false, cubeSpeed);
        } else {
            move(cubeX, cubeY, flareX, flareY, true, 1);
        }

        g.setColor(getColor());
        if (!isFollowingFlare) {
            g.fillOval((int)(getX() - cubeX - getWidth()/2 + GlowAnimation.WIDTH/2), (int)(getY() - cubeY - getWidth()/2 + GlowAnimation.HEIGHT/2), (int)getWidth(), (int)getWidth());
        } else {
            g.fillOval((int)(getX() - flareX - cubeX - getWidth()/2 + GlowAnimation.WIDTH/2), (int)(getY() - flareY - cubeY - getWidth()/2 + GlowAnimation.HEIGHT/2), (int)getWidth(), (int)getWidth());
        }
    }

    public void move(double cubeX, double cubeY, double flareX, double flareY, boolean isFollowingFlare, double cubeSpeed) {
        if (isFollowingFlare) {
            speed = cubeSpeed * 2;
            if (cubeSpeed < 1) {
                speed = 2;
            }

            setDirection(Math.atan2(getY() - flareY, getX() - flareX));
        } else {
            speed = cubeSpeed * 1.1;
            if (cubeSpeed < 1) {
                speed = 1;
            }
            setDirection(Math.atan2(getY() - cubeY, getX() - cubeX));
        }

        setX(getX() - Math.cos(getDirection()) * speed);
        setY(getY() - Math.sin(getDirection()) * speed);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    // Dummy method
    @Override
    public void draw(double cubeX, double cubeY, Graphics g) {
        // THIS DOES NOTHING
    }
}
