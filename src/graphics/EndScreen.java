/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import ai.RapidAI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import logic.Board;
import logic.Game;

/**
 *
 * @author Elijah
 */
public class EndScreen extends VBox {

    private GridPane root = new GridPane();
    private Label result = new Label(""), hangman = new Label();
    private Button yes = new Button("Play again?");
    private Button quitB = new Button("Quit");

    public EndScreen(EventHandler quit) {
        quitB.setOnAction(quit);
        yes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nextScene();
            }
        });
        root.setVgap(3);
        root.setHgap(50);
        root.add(result, 7, 30);
        root.add(yes, 7, 32);
        root.add(hangman, 6, 30);
        root.add(quitB, 5, 30);
        this.getChildren().add(root);

    }

    /**
     *
     * @param b since we need information from the board
     */
    public void updateResult(Game g) {
        int lives = g.getBoard().getLives();
        String word = g.getBoard().getWord();
        hangman.setGraphic(HangmanAni.getHangman(lives));
        if (g.getAI() != null && g.getAI().toString().equals("Rapid")) {
            result.setText("Game over! You got " + ((RapidAI) g.getAI()).getPointClass().getPoints() + " points!\nThe word you couldn't get was " + word);
            ((RapidAI) g.getAI()).callHighScore();
        } else if (g.getSmartAI() != null) {
            if (lives == 0) {
                result.setText("Oops! Smart AI not very smart. \nWas unable to guess " + word);
            } else {

                result.setText("Got it! Your word was " + word);
            }
        } else if (lives == 0) {
            result.setText("You lost! The word was " + word);
        } else {

            result.setText("You won! The word was " + word);
        }
    }

    public void nextScene() {
    }
}
