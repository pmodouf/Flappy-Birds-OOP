import java.awt.Image;

public class Bird {
    private int x, y, width, height, velocityY;
    private Image img;
    private int gravity = 1;

    public Bird(int x, int y, int width, int height, Image img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
        this.velocityY = 0;
    }

    public void move() {
        velocityY += gravity;
        y += velocityY;
        y = Math.max(y, 0);
    }

    public void flap() {
        velocityY = -9;
    }

    public boolean isOffScreen(int screenHeight) {
        return y > screenHeight;
    }

    public void reset(int y) {
        this.y = y;
        this.velocityY = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getImage() {
        return img;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }
}

