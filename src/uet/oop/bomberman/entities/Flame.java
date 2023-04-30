package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

import static uet.oop.bomberman.BombermanGame.flameList;

public class Flame extends Entity {
    private int left;
    private int right;
    private int top;
    private int down;

    private int radius;
    private int direction;
    private int time = 0;
    private int size = Sprite.SCALED_SIZE;

    public Flame(int x, int y) {
        super(x, y);
        setLayer(1);
    }

    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.radius = 1;
        setLayer(1);
    }

    public Flame(int xUnit, int yUnit, Image img, int direction) {
        super(xUnit, yUnit, img);
        this.direction = direction;
        setLayer(1);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    // ket thuc vu no
    @Override
    public void update() {
        if (time < 20) {
            time++;
            getImg();
        }
        else {
            flameList.remove(this);
        }
    }

    public void renderExplode() {
        rightOfFlame();
        leftOfFlame();
        topOfFlame();
        downOfFlame();
        explode();
    }

    public void explode(){
        Flame f = new Flame(x, y);
        f.img = Sprite.bomb_exploded.getFxImage();
        f.direction = 0;
        flameList.add(f);
        for(int i = 0; i < right; i++){
            Flame flame = new Flame(x + size*(i + 1), y);
            if(i == right - 1) {
                flame.img = Sprite.explosion_horizontal_right_last.getFxImage();
                flame.direction = 2;
            } else{
                flame.img = Sprite.explosion_horizontal.getFxImage();
                flame.direction = 1;
            }
            BombermanGame.flameList.add(flame);
        }

        for(int i = 0; i < left; i++){
            Flame flame = new Flame(x - size*(i + 1), y);
            if(i == left - 1) {
                flame.img = Sprite.explosion_horizontal_left_last.getFxImage();
                flame.direction = 3;
            } else{
                flame.img = Sprite.explosion_horizontal.getFxImage();
                flame.direction = 1;
            }
            BombermanGame.flameList.add(flame);
        }

        for(int i = 0; i < top; i++){
            Flame flame = new Flame(x , y - size*(i + 1));
            if(i == top - 1) {
                flame.img = Sprite.explosion_vertical_top_last.getFxImage();
                flame.direction = 5;
            } else{
                flame.img = Sprite.explosion_vertical.getFxImage();
                flame.direction = 4;
            }
            BombermanGame.flameList.add(flame);
        }

        for(int i = 0; i < down; i++){
            Flame flame = new Flame(x, y + size*(i + 1));
            if(i == right - 1) {
                flame.img = Sprite.explosion_vertical_down_last.getFxImage();
                flame.direction = 6;
            } else{
                flame.img = Sprite.explosion_vertical.getFxImage();
                flame.direction = 4;
            }
            BombermanGame.flameList.add(flame);
        }
    }

    @Override
    public Rectangle getBounds() {
        Rectangle bound = new Rectangle(x, y, Sprite.SCALED_SIZE * 3/4, Sprite.SCALED_SIZE * 3/4);
        return bound;
    }

    private static Object typeOfCollision (Rectangle rectangle) {
        for (Entity entity : BombermanGame.stillObjects) {
            Rectangle r = entity.getBounds();
            if (rectangle.intersects(r)) return entity;
        }

        return rectangle;
    }

    private void rightOfFlame() {
        for (int i=0; i<radius; i++) {
            Rectangle rectangle = new Rectangle(x + size * (i + 1), y, size, size);
            if (typeOfCollision(rectangle) instanceof Wall) {
                right = i;
                return;
            }
            else if (typeOfCollision(rectangle) instanceof Brick) {
                right = i + 1;
                return;
            }
            right = i + 1;
        }
    }

    private void leftOfFlame() {
        for (int i=0; i<radius; i++) {
            Rectangle rectangle = new Rectangle(x - size * (i + 1), y, size, size);
            if (typeOfCollision(rectangle) instanceof Wall) {
                left = i;
                return;
            }
            else if (typeOfCollision(rectangle) instanceof Brick) {
                left = i + 1;
                return;
            }
            left = i + 1;
        }
    }

    private void topOfFlame() {
        for (int i=0; i<radius; i++) {
            Rectangle rectangle = new Rectangle(x, y - size * (i + 1), size, size);
            if (typeOfCollision(rectangle) instanceof Wall) {
                top = i;
                return;
            }
            else if (typeOfCollision(rectangle) instanceof Brick) {
                top = i + 1;
                return;
            }
            top = i + 1;
        }
    }

    private void downOfFlame() {
        for (int i=0; i<radius; i++) {
            Rectangle rectangle = new Rectangle(x, y + size * (i + 1), size, size);
            if (typeOfCollision(rectangle) instanceof Wall) {
                down = i;
                return;
            }
            else if (typeOfCollision(rectangle) instanceof Brick) {
                down = i + 1;
                return;
            }
            down = i + 1;
        }
    }

    private void getImg() {
        switch (direction) {
            case 0:
                img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1,
                        Sprite.bomb_exploded2, time, 20).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1
                        ,Sprite.explosion_horizontal2,time,20).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1
                        ,Sprite.explosion_horizontal_right_last2, time,20).getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1
                        ,Sprite.explosion_horizontal_left_last2, time,20).getFxImage();
                break;
            case 4:
                img = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1
                        ,Sprite.explosion_vertical2, time,20).getFxImage();
                break;
            case 5:
                img = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1
                        ,Sprite.explosion_vertical_top_last2, time,20).getFxImage();
                break;
            case 6:
                img = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1
                        ,Sprite.explosion_vertical_down_last2, time,20).getFxImage();
                break;
        }
    }
}
