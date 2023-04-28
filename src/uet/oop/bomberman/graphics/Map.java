package uet.oop.bomberman.graphics;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static uet.oop.bomberman.BombermanGame.enemies;
import static uet.oop.bomberman.BombermanGame.stillObjects;

public class Map {

    public static void createMap() {
        try {
            File file = new File("res/levels/Level" + BombermanGame.level + ".txt");
            // tạo đối tượng để quét file txt
            Scanner scanner = new Scanner(file);
            int level = scanner.nextInt();
            // Tiếp tục đọc để bỏ qua chỉ số level
            int height = scanner.nextInt();
            int width = scanner.nextInt();
            scanner.nextLine();
            Entity object;

            BombermanGame.HEIGHT = height;
            BombermanGame.WIDTH = width;

            for(int i = 0; i < height; i++) {
                String line = scanner.nextLine();
                for(int j = 0; j < width; j++) {
                    //Tạo entity mới để dễ add vào map hơn
                    Entity entity;
                    if(line.charAt(j) == '#') {
                        entity = new Wall(j, i, Sprite.wall.getFxImage());
                        stillObjects.add(entity);
                    } else {
                        entity = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(entity);
                        switch(line.charAt(j)) {
                            case 'x':
                                entity = new Portal(j, i, Sprite.portal.getFxImage());
                                stillObjects.add(entity);
                                //Đè brick lên portal
                                entity = new Brick(j, i, Sprite.brick.getFxImage());
                                stillObjects.add(entity);
                                break;
                            case '*':
                                entity = new Brick(j, i, Sprite.brick.getFxImage());
                                stillObjects.add(entity);
                                break;
                            case '1':
                                Enemy enemy = new Balloom(j, i, Sprite.balloom_left1.getFxImage());
                                enemies.add(enemy);
                                break;
                            case '2':
                                Enemy enemy1 = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                                enemies.add(enemy1);
                                break;
                        }
                    }
                }
            }
            //Sắo xếp entity theo layer của nó
            for(int i = 0; i < stillObjects.size() - 1; i++) {
                for(int j = i + 1; j < stillObjects.size(); j++){
                    if(stillObjects.get(i).getLayer() < stillObjects.get(j).getLayer()) {
                        Entity temp = stillObjects.get(i);
                        stillObjects.set(i, stillObjects.get(j));
                        stillObjects.set(j, temp);
                    }
                }
            }
        }
        catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }
}