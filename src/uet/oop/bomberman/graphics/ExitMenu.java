package uet.oop.bomberman.graphics;

import GiaoDien.Map;
import GiaoDien.Sound;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Collisions;

//import java.awt.*;
import static uet.oop.bomberman.BombermanGame.*;


public class ExitMenu {
    public static void showExitMenu(Stage stage) {
        stage.close();

        Sound.GameOver.play();
        Sound.GameOver.loop();
        Sound.BG.stop();

        Label scoreL = new Label("Your Score: " + BombermanGame.score);

        Image image1 = new Image("StartMenu/exitBackground-02.png");
        ImageView background = new ImageView(image1);

        Image image2 = new Image("StartMenu/ResizeRestartButton.png");
        ImageView restart = new ImageView(image2);

        Image image3 = new Image("StartMenu/ResizeExitButton.png");
        ImageView exit = new ImageView(image3);

        scoreL.setTextFill(Color.WHITE);
        scoreL.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        scoreL.setLayoutX(270);
        scoreL.setLayoutY(140);

        restart.setScaleX(0.2);
        restart.setScaleY(0.2);

        exit.setScaleX(0.2);
        exit.setScaleY(0.2);

        background.setFitWidth(1481/2);
        background.setFitHeight(877/2);

        //Tạo root chứa 2 ảnh
        AnchorPane root = new AnchorPane();
        root.getChildren().add(background);
        root.getChildren().add(restart);
        root.getChildren().add(exit);
        root.getChildren().add(scoreL);

        Scene startScene = new Scene(root);
        Stage startStage = new Stage();
        startStage.setScene(startScene);
        startStage.setTitle("BOMBERMAN_GAME");

        //_____________________________ALIGN___________________________________
        // Lấy kích thước hình ảnh
        double imageWidth = 1481 / 2;
        double imageHeight = 877 / 2;

        // Đặt kích thước mới cho cửa sổ
        startStage.setWidth(imageWidth);
        startStage.setHeight(imageHeight);

        // Đặt vị trí mới cho hình ảnh và nút
        background.setLayoutX(0);
        background.setLayoutY(0);

        restart.setLayoutX(-210);
        restart.setLayoutY(100);

        exit.setLayoutX(10);
        exit.setLayoutY(100);

        // Show start menu
        startStage.show();

        restart.setOnMouseClicked(event -> {
            Sound.ButtonClick.play();
            Sound.MainMenu.stop();
            Sound.BG.play();
            Sound.BG.loop();
            startStage.close();
//            BombermanGame.resetLevel();
            stage.show();
            entities.clear();
            enemies.clear();
            level = 0;
//            Map.createMap();
            flag = 1;
            score = 100;
            Collisions.life = 60;
            restartGame = true;
        });

        exit.setOnMouseClicked(event -> {
            Sound.ButtonClick.play();
            startStage.close();
        });
    }
}
