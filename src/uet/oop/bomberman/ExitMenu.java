package uet.oop.bomberman;

import GiaoDien.Sound;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//import java.awt.*;


public class ExitMenu {
    public static void showExitMenu(Stage stage) {
        stage.close();
        Label scoreL = new Label("Score: " + BombermanGame.score);
        Image image1 = new Image("Menu/exitBg.png");
        ImageView exitBg = new ImageView(image1);
        exitBg.setFitHeight(877 / 2);
        exitBg.setFitWidth(1481 / 2);

        StackPane root = new StackPane();
        root.getChildren().add(exitBg);

        Scene startScene = new Scene(root);
        Stage startStage = new Stage();
        startStage.setScene(startScene);
        startStage.setTitle("BOMBERMAN_GAME");

        //_____________________________ALIGN___________________________________
        // Đặt kích thước mới cho cửa sổ
        startStage.setWidth(1481 / 2);
        startStage.setHeight(877 / 2);

        Sound.BG.stop();
        Sound.GameOver.play();

        // Show start menu
        startStage.show();
    }
}
