package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.bomberman;

public class SpeedItem extends Item{
    public SpeedItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
    }

    @Override
    public void update() {
    }

    @Override
    public void change() {
        bomberman.setSpeed(bomberman.getSpeed() + 1);
    }
}
