package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    private int timeCount = 0;

    private int radius;

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(2);
        radius = 1;
    }

    public Bomb(int xUnit, int yUnit, Image img, int radius) {
        super(xUnit, yUnit, img);
        this.radius = radius;
        setLayer(2);
    }

    @Override
    public void update() {
        BombermanGame.bomberman.bombale = false;
        if (timeCount++ == 100) {

        }
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, timeCount, 40).getFxImage();
    }

    public void explodeUpgrade() {
        Flame e = new Flame(x, y);
    }
}
