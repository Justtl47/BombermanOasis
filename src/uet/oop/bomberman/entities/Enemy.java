package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.Random;

public abstract class Enemy extends MoveableEntity {

    public boolean metBomb = false;
    public Enemy(int x, int y, Image img) {
        super(x, y, img);
        setLayer(1);
    }

    public void dead() {
        img = Sprite.movingSprite(Sprite.mob_dead1,Sprite.mob_dead2, Sprite.mob_dead3, deadTime++, 90).getFxImage();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(desX + 4, desY + 4, Sprite.SCALED_SIZE * 9 / 10, Sprite.SCALED_SIZE * 9 / 10);
    }

    public int randomDirection() {
        Random randomMove = new Random();
        return randomMove.nextInt(4); // Random mot so nguyen tu 1 den 4
    }
}