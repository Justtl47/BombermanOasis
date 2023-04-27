package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public abstract class Item extends StillEntity {
    public Item(int x, int y, Image img) {
        super(x, y, img);
        setLayer(1);
    }

    public abstract void change();
}
