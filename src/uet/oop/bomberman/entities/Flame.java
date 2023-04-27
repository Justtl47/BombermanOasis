package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Flame extends Entity {

    public Flame(int x, int y, Image img) {
        super(x, y, img);
    }

    public Flame(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {

    }
}