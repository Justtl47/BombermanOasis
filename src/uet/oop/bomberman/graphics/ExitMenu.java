package uet.oop.bomberman.graphics;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uet.oop.bomberman.BombermanGame;

//import java.awt.*;

import static uet.oop.bomberman.BombermanGame.bomberman;
import static uet.oop.bomberman.BombermanGame.flag;

public class ExitMenu {
    public static void showExitMenu(Stage stage) {
        stage.close();
        Label scoreL = new Label("Score: " + BombermanGame.score);
        Image image1 = new Image("StartMenu/backViet.jpg");
        ImageView background = new ImageView(image1);
        background.setScaleX(0.25);
        background.setScaleY(0.25);

        //Tạo root chứa 2 ảnh
        AnchorPane root = new AnchorPane();
        root.getChildren().add(background);
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

        // Show start menu
        startStage.show();
    }
}
