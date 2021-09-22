package graphics;

import ai.PointClass;
import ai.RapidAI;
import logic.Game;
import graphics.GUI;
import logic.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import java.io.*;

/**
 *
 * @author Khoa
 */
public class MainMenu extends VBox {

    private GridPane root = new GridPane();

    private Label rapidHighScore = new Label("");
    private final Label modeLabel = new Label("Please select the mode.");

    private final Button PVP = new Button("PVP: 2 players");
    private final Button PVE = new Button("PVE: 1 player");
    private final Button quitB = new Button("Quit");

    public MainMenu(EventHandler quit) {
        PVP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nextScene(1);
            }
        });
        PVE.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nextScene(2);
            }
        });
        quitB.setOnAction(quit);
        root.setVgap(10);
        root.setHgap(50);
        root.add(modeLabel, 10, 39);
        PointClass.readHighScore();
        rapidHighScore.setText("Highscore: " + PointClass.highscore);
        root.add(rapidHighScore, 10, 35);
        root.add(quitB, 7, 42);
        root.add(PVP, 10, 40);
        root.add(PVE, 10, 42);
        this.getChildren().add(root);

    }

    public void update(){
        rapidHighScore.setText("Highscore: " + PointClass.highscore);
    }
    /**
     *
     * @param target
     */
    public void nextScene(int target) {
    }
}
