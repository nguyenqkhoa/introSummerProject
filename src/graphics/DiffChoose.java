package graphics;

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
import logic.Game;

/**
 *
 * @author Usama
 */
public class DiffChoose extends VBox {

    private final Label Difficulty = new Label("Please choose Difficulty");
    private GridPane root = new GridPane();

    /**
     *
     * @param g so that we can access the information and logic based methods in game.
     * @param back to pass through the ability to go back
     */
    public DiffChoose(final Game g, EventHandler back, EventHandler quit) {
        Button backButton = new Button("Go back");
        Button rapid = new Button("Rapid");
        Button quitb = new Button("Quit");
        backButton.setOnAction(back);
        Button easy = new Button("Easy");
        Button med = new Button("Medium");
        Button hard = new Button("Hard");
        Button smart = new Button ("Smart");
        
        root.setVgap(10);
        root.setHgap(50);
        root.add(smart, 10, 48);
        root.add(rapid, 10, 46);
        root.add(Difficulty, 10, 38);
        root.add(quitb, 5,46);
        root.add(easy, 10, 40);
        root.add(med, 10, 42);
        root.add(hard, 10, 44);
        root.add(backButton,14,46);
        this.getChildren().add(root);

        easy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                g.enableAI("easy");
                nextScene("");
            }
        });
        med.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                g.enableAI("medium");
                nextScene("");
            }
        });
        hard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                g.enableAI("hard");
                nextScene("");
            }

        });
        rapid.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                g.enableAI("rapid");
                nextScene("rapid");
            }
        });
        smart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nextScene("smart");
            }
        });
        
        
       

    }
	
	 /**
     *
     * @param rapid tells us if it is rapid or not
     */
    public void nextScene(String type) {
    }
}
