package GiaoDien;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static uet.oop.bomberman.BombermanGame.stillObjects;

public class Map {

    public static void createMap() {
        try{
            File file = new File("src/Levels/level" + BombermanGame.level + ".txt");
            // tạo đối tượng để quét file txt
            Scanner scanner = new Scanner(file);
            int height = scanner.nextInt();
            // Tiếp tục đọc để bỏ qua chỉ số level
            height = scanner.nextInt();
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
                        entity = new Grass(j, i, Sprite.wall.getFxImage());
                    } else {
                        entity = new Grass(j, i, Sprite.wall.getFxImage());
                        switch (line.charAt(j)) {
                            case '*':
                                entity = new Brick(j, i, Sprite.brick.getFxImage());
                                break;
                            case 'x':
                                entity = new Portal(j, i, Sprite.portal.getFxImage());
                                break;
                        }
                    }
                    stillObjects.add(entity);
                }
            }
        }
        catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
