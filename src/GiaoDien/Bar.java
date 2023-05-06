package GiaoDien;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import uet.oop.bomberman.BombermanGame;
import static javafx.scene.paint.Color.WHITE;

public class Bar extends AnchorPane {
    public Label labelLevel;
    public Label labelTime;
    public Label labelScore;
    public Label labelRemain;
    public Label labelLife;

    public Bar() {
        labelLevel = new Label("Level: ");
        labelLevel.setLayoutX(32);
        labelLevel.setLayoutY(5);
        labelLevel.setFont(Font.font(15));
        labelLevel.setTextFill(WHITE);

        labelTime = new Label("Time: ");
        labelTime.setLayoutX(200);
        labelTime.setLayoutY(5);
        labelTime.setFont(Font.font(15));
        labelTime.setTextFill(WHITE);

        labelScore = new Label("Score: ");
        labelScore.setLayoutX(400);
        labelScore.setLayoutY(5);
        labelScore.setFont(Font.font(15));
        labelScore.setTextFill(WHITE);

        labelRemain = new Label("Enemies Remain: ");
        labelRemain.setLayoutX(600);
        labelRemain.setLayoutY(5);
        labelRemain.setFont(Font.font(15));
        labelRemain.setTextFill(WHITE);

        labelLife = new Label("Life: ");
        labelLife.setLayoutX(800);
        labelLife.setLayoutY(5);
        labelLife.setFont(Font.font(15));
        labelLife.setTextFill(WHITE);
    }

    public void setBoard() {
        BombermanGame.board.getChildren().addAll(labelTime, labelLevel, labelScore, labelRemain, labelLife);

    }
    public void setLabelTime(int time) {
        labelTime.setText("Time: " + time);
    }
    public void setLabelLevel(int level) {
        labelLevel.setText("Level: " + level);
    }

    public void setLabelScore(int score) {
        labelScore.setText("Score: " + score);
    }
    public void setLabelLife(int life) {
        labelLife.setText("Life: " + life / 20);
    }

    public void setLabelRemain(int remain) {
        labelRemain.setText("Enemies Remain: " + remain);
    }
}
