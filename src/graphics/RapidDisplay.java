/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import logic.Game;
import ai.RapidAI;

/**
 *
 * @author purpl
 */
public class RapidDisplay extends VBox {

    private TextField guessTextField;
    private Label guessedLetters, timer = new Label(""), points = new Label("");

    private Label PreviousLetters;
    private Label wordLabel;
    private Button enter = new Button("Enter guess");
    private Button hintButton = new Button("Get hint");
    private Button backB = new Button("Forfeit");
    private Label hintLabel = new Label(""), invalid = new Label("");
    private boolean rapid;
    private GridPane root = new GridPane();
    private Label hangman = new Label();
    private EventHandler press;
    private RapidAI rai;

    /**
     *
     * @param g so that we can access the information and logic based methods in
     * game.
     */
    public RapidDisplay(final Game g, EventHandler quit) {

        wordLabel = new Label("");
        guessTextField = new TextField();
        guessTextField.setPromptText("Guess a letter");
        guessedLetters = new Label("");
        PreviousLetters = new Label("Previously guessed letters:");
        Button quitB = new Button ("Quit");
      
        quitB.setOnAction(quit);
        hangman.setGraphic(HangmanAni.getHangman(6));
        press = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                invalid.setText("");
                //formerly of displayLoop (written by Riyad and Khoa)
                if (!guessTextField.getText().isEmpty()
                        && guessTextField.getText().length() == 1
                        && Character.isLetter(guessTextField.getText().charAt(0))
                        && !g.getBoard().getGuesses().contains(guessTextField.getText().toUpperCase().charAt(0))) {
                    g.isValidLetter(guessTextField.getText().toUpperCase().charAt(0));
                } else {
                    invalid.setText("Your guess was invalid.");
                }
                guessTextField.setText("");
                wordLabel.setText(g.getBoard().getState());
                guessedLetters.setText(g.getBoard().getBadLetters());
                hangman.setGraphic(HangmanAni.getHangman(g.getBoard().getLives()));
                if (g.checkWon()) {
                    points.setText(rai.getPointClass().getPoints() + "");
                    nextScene(true);
                } else if (g.getBoard().getLives() == 0 || rai.getPointClass().isDone()) {
					rai.getPointClass().gameOver();
                    nextScene(false);
                }
            }
        };
        guessTextField.setOnAction(press);
        enter.setOnAction(press);
        hintButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hintLabel.setText(g.getAI().getHint());
                hintButton.setVisible(false);
            }
        });
        root.setVgap(10);
        root.setHgap(50);
        root.add(points, 12, 4);
        root.add(timer, 12, 3);
        root.add(PreviousLetters, 12, 1);
        root.add(guessedLetters, 12, 2);
        root.add(hangman, 12, 0);
        root.add(quitB,6,4);

        root.add(guessTextField, 8, 2);
        root.add(wordLabel, 8, 1);
        root.add(enter, 8, 3);
        root.add(invalid, 8, 4);

        this.getChildren().add(root);

    }

    /**
     *
     * @param gso that we can access the information in game.
     * 
     */
    public void updateWord(Game g) {
        guessedLetters.setText("");
        wordLabel.setText(g.getBoard().getState());
        hangman.setGraphic(HangmanAni.getHangman(6));
        rai = (RapidAI) g.getAI();
        rai.getPointClass().setLabel(timer);
        points.setText(rai.getPointClass().getPoints() + "");
    }

    /**
     *
     * @param won whether the game is won or not
     */
    public void nextScene(boolean won) {
    }

}
