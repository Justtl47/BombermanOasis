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

        Label scoreL = new Label("Your Score: " + BombermanGame.score);

        Image image1 = new Image("StartMenu/backViet.jpg");
        ImageView background = new ImageView(image1);

        Image image2 = new Image("StartMenu/ResizeRestartButton.png");
        ImageView restart = new ImageView(image2);

        Image image3 = new Image("StartMenu/ResizeExitButton.png");
        ImageView exit = new ImageView(image3);

        scoreL.setTextFill(Color.WHITE);
        scoreL.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        scoreL.setLayoutX(270);
        scoreL.setLayoutY(240);

        restart.setScaleX(0.35);
        restart.setScaleY(0.35);

        exit.setScaleX(0.35);
        exit.setScaleY(0.35);

        background.setScaleX(0.25);
        background.setScaleY(0.25);

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
        background.setLayoutX(-1120);
        background.setLayoutY(-690);

        restart.setLayoutX(-60);
        restart.setLayoutY(265);

        exit.setLayoutX(260);
        exit.setLayoutY(265);

        // Show start menu
        startStage.show();

        restart.setOnMouseClicked(event -> {
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
            Collisions.life = 60;
            restartGame = true;
        });


        exit.setOnMouseClicked(event -> {
            startStage.close();
        });
    }
}
