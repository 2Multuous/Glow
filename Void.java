import java.awt.*;

public class Void extends GameObject {
    private boolean followingFlare;
    private double speed;

    private double goalX;
    private double goalY;

    public Void(double x, double y, double width, double speed) {
        setX(x);
        setY(-y);
        setWidth(width);
        this.speed = speed;
    }

    public void draw(double cubeX, double cubeY, Graphics g) {
        if (!followingFlare) {
            setGoal(cubeX, cubeY);
        }
        move();

        g.setColor(getColor());
        g.drawOval((int)(getX() - cubeX - getWidth()/2 + GlowAnimation.WIDTH), (int)(getY() - cubeY - getWidth()/2 + GlowAnimation.HEIGHT), (int)getWidth(), (int)getWidth());
    }

    public void move() {
        setDirection(Math.atan2(goalY, goalX));

        setX(getX() + Math.cos(getDirection()) * speed);
        setY(getY() + Math.sin(getDirection()) * speed);
    }

    public boolean isFollowingFlare() {
        return followingFlare;
    }

    public void setFollowingFlare(boolean followingFlare) {
        this.followingFlare = followingFlare;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setGoal(double x, double y) {
        goalX = x;
        goalY = y;
    }

    public double getGoalX() {
        return goalX;
    }

    public void setGoalX(double goalX) {
        this.goalX = goalX;
    }

    public double getGoalY() {
        return goalY;
    }

    public void setGoalY(double goalY) {
        this.goalY = goalY;
    }
}
