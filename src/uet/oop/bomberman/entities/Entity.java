package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

import javax.sound.midi.Receiver;
import java.awt.*;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected int layer;
    int time = 0;
    protected boolean alive;
    public int centerX = x + Sprite.SCALED_SIZE / 2;
    public int centerY = y + Sprite.SCALED_SIZE / 2;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    protected Image img;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }


    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();
    public Rectangle getBounds() {
        return new Rectangle(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }
}
