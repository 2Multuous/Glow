import java.awt.*;

public class Void extends GameObject {
    private final int WIDTH;
    private final int HEIGHT;

    private boolean followingFlare;
    private double speed;

    private double goalX;
    private double goalY;

    public Void(int speed, int WIDTH, int HEIGHT) {
        this.speed = speed;

        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    public void draw(double cubeX, double cubeY, Graphics g) {
        if (!followingFlare) {
            setGoal(cubeX, cubeY);
        }
        move();

        g.setColor(getColor());
        g.drawOval((int)(getX() - cubeX), (int)(getY() - cubeY), (int)getWidth(), (int)getWidth());
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
