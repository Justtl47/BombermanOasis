package uet.oop.bomberman;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class StartMenu {
    public static void createStartMenu(Stage stage) {
        //Đọc ảnh background và button
        Image image1 = new Image("bg.jpg");
        ImageView background = new ImageView(image1);
        background.setScaleX(0.75);
        background.setScaleY(0.75);
        background.setX(-145);
        background.setY(-100);


        Image image2 = new Image("start_button.png");
        ImageView button = new ImageView(image2);
        button.setScaleX(0.45);
        button.setScaleY(0.45);
        button.setX(150);
        button.setY(300);

        //Tạo root chứa 2 ảnh
        AnchorPane root = new AnchorPane();
        root.getChildren().add(background);
        root.getChildren().add(button);

        Scene startScene = new Scene(root);
        Stage startStage = new Stage();
        startStage.setScene(startScene);
        startStage.setHeight(550);
        startStage.setWidth(855);
        startStage.setTitle("BOMBERMAN_GAME");
        startStage.show();

        button.setOnMouseClicked(event -> {
            button.setScaleX(0.35);
            button.setScaleY(0.35);
            startStage.close();
            stage.show();
        });
    }
}