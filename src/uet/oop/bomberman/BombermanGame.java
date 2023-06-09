package uet.oop.bomberman;

import GiaoDien.Bar;
import GiaoDien.Map;
import GiaoDien.Sound;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import GiaoDien.Sound;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.ExitMenu;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import static javafx.scene.paint.Color.BLACK;
import static uet.oop.bomberman.StartMenu.createStartMenu;
import static uet.oop.bomberman.entities.Collisions.life;

public class BombermanGame extends Application {

    public static int WIDTH ;
    public static int HEIGHT;
    public static Bomber bomberman;
    private GraphicsContext gc;
    private Canvas canvas;
    public static AnchorPane board = new AnchorPane();
    public static Bar bar = new Bar();
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Enemy> enemies = new ArrayList<>();
    public static List<Bomb> bombList = new ArrayList<>();
    public static List<Flame> flameList = new ArrayList<>();
    public static int score = 100;

    public static int time = 0;
    public static int level = 0;
    public static boolean nextLevel = false;
    public static Group root = new Group();
    public static Scene scene;

    public static Stage stage1;
    public static Stage stage2;
    public static List<Enemy> eList = new ArrayList<>();
    public static int flag = 1;
    public static boolean restartGame = false;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        board.getChildren().addAll(new Rectangle(2,3));
        bar.setBoard();
        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        Map.createMap();
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        //Group root = new Group();
        canvas.setLayoutY(30);
        root.getChildren().add(canvas);
        root.getChildren().add(board);

        // Tao scene
        //Scene scene = new Scene(root);
        scene = new Scene(root);
        scene.setFill(BLACK);
        //  Them scene vao stage
        stage2 = stage;
        stage1 = stage;
        stage1.setTitle("Bomberman Game");
        stage1.setScene(scene);

        //Chơi nhạc nền
        Sound.MainMenu.play();

        //Tạo màn hình khởi động game
        createStartMenu(stage);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (life <= 0) {
                    if (flag == 1) {
                        flag--;
                        ExitMenu.showExitMenu(stage1);
                    }
                }
                if(nextLevel) {
                    level++;
                    resetLevel();
                }

                if(Bomber.revive) {
                    entities.clear();
                    if (eList.size() != 0) {
                        Enemy e = eList.get(0);
                        eList.remove(0);
                        e.setAlive(true);
                        enemies.add(e);
                    }
                    bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
                    entities.add(bomberman);
                    bombList = bomberman.getBombs();
                }
                try {
                    render();
                    update();
                } catch (ConcurrentModificationException e) {
                    // inevitable.
                }
            }
        };
        timer.start();
        scene.setOnKeyPressed(event -> bomberman.handleKeyPressedEvent(event.getCode()));
        scene.setOnKeyReleased(event -> bomberman.handleKeyReleasedEvent(event.getCode()));
        bombList = bomberman.getBombs();


    }

    public static void resetLevel() {
        stillObjects.clear();
        entities.clear();
        Map.createMap();
        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        bombList = bomberman.getBombs();
        nextLevel = false;
    }

    public void update() {
        if (restartGame) {
            level = 0;
            resetLevel();
            restartGame = false;
        }
        
        score = Math.max(score, 0);
        bar.setLabelLevel(level);
        bar.setLabelTime(time / 120);
        bar.setLabelRemain(enemies.size());
        bar.setLabelScore(score);
        bar.setLabelLife(life);
        time++;
        enemies.forEach(Enemy::update);
        entities.forEach(Entity::update);
        bombList.forEach(Bomb::update);
        stillObjects.forEach(Entity::update);
//        for (Entity entity : stillObjects) entity.update();
        for (Flame flame : flameList) flame.update();
        Collisions.collisionFlame();
        Collisions.collisionsHandler();
        Collisions.enemyHandler();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for(int i = stillObjects.size() - 1; i >= 0; i--) {
            stillObjects.get(i).render(gc);
        }
        bombList.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        flameList.forEach(g -> g.render(gc));
    }
}
