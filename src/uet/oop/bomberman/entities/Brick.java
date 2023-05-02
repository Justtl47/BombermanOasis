package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {

    public Brick(int x, int y, Image img) {
        super(x, y, img);
        setLayer(5);
        alive = true;
        time = 0;
    }

    @Override
    public void update() {
        if (!isAlive()) {
            if (time < 45) {
                time++;
                img = Sprite.movingSprite(Sprite.brick_exploded,
                        Sprite.brick_exploded1, Sprite.brick_exploded2, time, 45).getFxImage();
            }
            else {
                BombermanGame.stillObjects.remove(this);
            }
        }

    }
}