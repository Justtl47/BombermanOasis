package uet.oop.bomberman.graphics;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static uet.oop.bomberman.BombermanGame.enemies;

public class Map {
    public static void createMap() {
        try {
            File file = new File("res/levels/Level1.txt");
            Scanner sc = new Scanner(file);
            int height = sc.nextInt();
            int width = sc.nextInt();
            sc.nextLine();
            Entity obj;
            BombermanGame.HEIGHT = height;
            BombermanGame.WIDTH = width;
            for (int i = 0; i < height; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < width; j++) {
                    if (line.charAt(j) == 'b') {
                        BombermanGame.stillObjects.add(new BombItem(j, i, Sprite.powerup_bombs.getFxImage()));
                    }
                    if (line.charAt(j) == '1') {
                        BombermanGame.enemies.add(new Balloom(j, i, Sprite.balloom_left1.getFxImage()));
                    }
                    if (line.charAt(j) == '2') {
                        enemies.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage()));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
