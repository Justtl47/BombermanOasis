package uet.oop.bomberman.entities;

import java.awt.*;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.BombermanGame.bomberman;

public class Collisions {
    public static int life = 60;
    public static void collisionFlame() {
        for (Flame flame : flameList) {
            Rectangle rectangle = flame.getBounds();
            Rectangle rectangle1 = bomberman.getBounds();

            if (rectangle.intersects(rectangle1)) {
                life--;
                System.out.println(life);
                bomberman.setAlive(false);
                break;
//                ExitMenu.showExitMenu(BombermanGame.stage1);
            }


            for (Entity stillObject : stillObjects) {
                Rectangle rectangle2 = stillObject.getBounds();
                if (rectangle.intersects(rectangle2) && (stillObject instanceof Brick)) {
                    stillObject.setAlive(false);
                }
            }

            for (Enemy enemy : enemies) {
                Rectangle rectangle3 = enemy.getBounds();
                if (rectangle.intersects(rectangle3)) {
                    score += 5;
                    enemy.setAlive(false);
                }
            }
        }
    }

    public static void collisionsHandler() {
        Rectangle rectangle = bomberman.getBounds();
        for (Entity stillObject : stillObjects) {
            Rectangle rectangle1 = stillObject.getBounds();
            if (rectangle.intersects(rectangle1)) {
                if (stillObject instanceof Portal && enemies.size() == 0) {
                    nextLevel = true;
                }
                if (bomberman.getLayer() >= stillObject.getLayer()) {
                    bomberman.move();
                }
                else {
                    bomberman.stay();
                }

                if (stillObject instanceof Item) {
                    score += 50;
                    ((Item) stillObject).change();
                    stillObjects.remove(stillObject);
                }

                //item, portal
                break;
            }

        }
    }
    
    public static void enemyHandler() {
        for(Enemy enemy : enemies) {
            Rectangle r1 = enemy.getBounds();
            for (Entity stillObject : stillObjects) {
                Rectangle r2 = stillObject.getBounds();
//                if (stillObject instanceof Brick) {
//                    enemy.move();
//                }

                if (r1.intersects(r2)) {
                    if (enemy.getLayer() >= stillObject.getLayer()) {
                        enemy.move();
                    } else {
                        enemy.stay();
                    }
                    break;
                }
            }
            for (Entity bomb : bombList) {
                Rectangle r3 = bomb.getBounds();
                if (r1.intersects(r3)) {
                    if (enemy.getLayer() < bomb.getLayer()) {
                        enemy.metBomb = true;
                        enemy.stay();
                    }
                }
            }
            Rectangle r4 = bomberman.getBounds();
            if (r1.intersects(r4) && enemy.isAlive()) {
                bomberman.setAlive(false);
                life -= 20;
                System.out.println(life);
                eList.add(enemy);
                enemy.setAlive(false);
//                ExitMenu.showExitMenu(stage1);
                break;
            }
        }
    }
}