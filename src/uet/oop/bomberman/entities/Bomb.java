package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bomberman;

public class Bomb extends Entity {
    private int time = 0;
    int radius;

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(2);
        radius = 1;
    }

    public Bomb(int xUnit, int yUnit, Image img, int radius) {
        super(xUnit, yUnit, img);
        setLayer(2);
        this.radius = radius;

    }

    @Override
    public void update() {
        bomberman.bombale = false;
        time ++;
        if (time ==  100) {
            bomberman.bombale = true;
            BombermanGame.bombList.remove(this);
        }

        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2,  time, 40).getFxImage();
    }
}
