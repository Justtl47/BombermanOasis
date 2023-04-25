package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Bomber extends MoveableEntity {

    private KeyCode direction = null;
    private boolean placeBombCommand = false;
    private final java.util.List<Bomb> bombList = new ArrayList<>();
    protected int radius;
    protected int die = 0;
    public static boolean revive;
    boolean bombale;
    boolean infibomb;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        setLayer(1);
        setSpeed(2);
        setRadius(1);
        bombale = true;
        infibomb = false;
        revive = false;
        setAlive(true);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void update() {
        this.move();
        if (direction == javafx.scene.input.KeyCode.LEFT) {
            goLeft();
        }
        if (direction == javafx.scene.input.KeyCode.RIGHT) {
            goRight();
        }
        if(direction == javafx.scene.input.KeyCode.UP) {
            goUp();
        }
        if(direction == javafx.scene.input.KeyCode.DOWN) {
            goDown();
        }

        if (placeBombCommand) {
            if (bombale || infibomb) {
                placeBomb();
            }
        }
        if (!isAlive()) {
            die++;
            bomberDie();
        }

    }

    public void handleKeyPressedEvent(KeyCode keyCode) {
        if (keyCode == javafx.scene.input.KeyCode.LEFT || keyCode == javafx.scene.input.KeyCode.RIGHT || keyCode == javafx.scene.input.KeyCode.UP || keyCode == javafx.scene.input.KeyCode.DOWN) {
            this.direction = keyCode;
        }

        if (keyCode == javafx.scene.input.KeyCode.SPACE) {
            placeBombCommand = true;
        }
    }

    public void handleKeyReleasedEvent(KeyCode keyCode) {
        if (direction == keyCode) {
            if (direction == javafx.scene.input.KeyCode.LEFT) {
                img = Sprite.player_left.getFxImage();
            }
            else if (direction == javafx.scene.input.KeyCode.RIGHT) {
                img = Sprite.player_right.getFxImage();
            }
            else if (direction == javafx.scene.input.KeyCode.UP) {
                img = Sprite.player_up.getFxImage();
            }
            else if (direction == javafx.scene.input.KeyCode.DOWN) {
                img = Sprite.player_down.getFxImage();
            }
            direction = null;
        }
        if (keyCode == javafx.scene.input.KeyCode.SPACE) {
            placeBombCommand = false;
        }
    }

    public void bomberDie() {
        BombermanGame.score -= 1;
        this.stay();
        if (die <= 60) {
            img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, die, 20).getFxImage();
        } else revive = true;
    }


    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, left++, 20).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, right++, 20).getFxImage();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, up++, 20).getFxImage();
    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, down++, 20).getFxImage();
    }

    public void placeBomb() {
        int xB = (int) Math.round((x + 4) / (double) Sprite.SCALED_SIZE);
        int yB = (int) Math.round((y + 4) / (double) Sprite.SCALED_SIZE);

        bombList.add(new Bomb(xB, yB, Sprite.bomb.getFxImage(), radius));
    }

    public Rectangle getBounds() {
        return new Rectangle(desX + 2, desY + 2, Sprite.SCALED_SIZE - 10, Sprite.SCALED_SIZE * 3/4);
    }

    public List<Bomb> getBombs() {
        return bombList;
    }

}
