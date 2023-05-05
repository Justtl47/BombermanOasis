package uet.oop.bomberman;

import GiaoDien.Sound;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class StartMenu {
    public static void createStartMenu(Stage stage) {
        //Đọc ảnh background và button
        Image image1 = new Image("Menu/backViet.jpg");
        ImageView background = new ImageView(image1);
        background.setScaleX(0.25);
        background.setScaleY(0.25);

        Image image2 = new Image("Menu/buttonViet.png");
        ImageView button = new ImageView(image2);
        button.setScaleX(0.35);
        button.setScaleY(0.35);

        //Tạo root chứa 2 ảnh
        AnchorPane root = new AnchorPane();
        root.getChildren().add(background);
        root.getChildren().add(button);

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
        button.setLayoutX(100);
        button.setLayoutY(265);

        // Show start menu
        startStage.show();

        button.setOnMouseClicked(event -> {
            Sound.MainMenu.stop();
            Sound.BG.play();
            Sound.BG.loop();
            startStage.close();
            stage.show();
        });
    }
}