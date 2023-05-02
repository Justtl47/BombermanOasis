package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.bomberman;

public class SpeedItem extends Item{
    public SpeedItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        bomberman.setSpeed(bomberman.getSpeed() + 1);
    }

    @Override
    public void change() {

    }
}
