package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    int targetDirection = direction();
    int randDirection = randomDirection();
    int count = 0;
    int bombEva = 0;

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        setSpeed(2);
        setAlive(true);
    }

    @Override
    public void dead() {
        setAlive(false);
        if (time < 50) {
            img = Sprite.oneal_dead.getFxImage();
            time++;
        } else if (time < 120) {
            super.dead();
            time++;
        } else {
            BombermanGame.enemies.remove(this);
        }
    }

    @Override
    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, right++, 60).getFxImage();
    }

    @Override
    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, left++, 60).getFxImage();
    }

    @Override
    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, up++, 60).getFxImage();
    }

    @Override
    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.oneal_right1,Sprite.oneal_right2, Sprite.oneal_right3, down++, 60).getFxImage();
    }

    public int direction() {
        int vertical = (int)(Math.random() * 2);
        // Returns a double value with a positive sign, greater than or equal to 1.0 and less than 2.0.
        if (vertical == 1) {
            if (xDirection() != -1) {
                return xDirection();
            } else {
                return yDirection();
            }
        } else {
            if (yDirection() != -1) {
                return yDirection();
            } else {
                return xDirection();
            }
        }
    }

    @Override
    public void move() {
        super.move();
        count++;
        if (metBomb) {
            bombEva++;
        }
        if (count == 3 && !this.metBomb) {
            targetDirection = direction();
            count = 0;
        }
        if (bombEva == 60) {
            metBomb = false;
            count = 0;
            bombEva = 0;
        }
    }

    @Override
    public void stay() {
        super.stay();
        targetDirection = direction();
        randDirection = randomDirection();
    }

    private int xDirection() {
        if (this.getX() < BombermanGame.bomberman.getX()) {
            return 1;
        } else if (this.getX() > BombermanGame.bomberman.getX()) {
            return 0;
        }
        return -1;
    }

    private int yDirection() {
        if (this.getY() < BombermanGame.bomberman.getY()) {
            return 3;
        } else if (this.getY() > BombermanGame.bomberman.getY()) {
            return 2;
        }
        return -1;
    }

    @Override
    public void update() {
        if (isAlive()) {
            if (Math.abs(this.getY() - BombermanGame.bomberman.getY()) < 100
                    && Math.abs(this.getX() - BombermanGame.bomberman.getX()) < 100
                    && !metBomb) {
                switch (targetDirection) {
                    case 0: goLeft();
                    case 1: goRight();
                    case 2: goUp();
                    case 3: goDown();
                }
            } else {
                switch (randDirection) {
                    case 0: goLeft();
                    case 1: goRight();
                    case 2: goUp();
                    case 3: goDown();
                }
            }
        } else {
            dead();
        }
    }
}
