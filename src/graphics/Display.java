package graphics;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import logic.Game;


/**
 *
 * @author Elijah
 */
public class Display extends VBox {

    private TextField guessTextField;
    private Label guessedLetters;//, timer = new Label("ff"), points = new Label("");

    private Label PreviousLetters;
    private Label wordLabel;
    private Button enter = new Button("Enter guess");
    private Button hintButton = new Button("Get hint");
    private Button backB = new Button("Forfeit");
    private Label hintLabel = new Label(""), invalid = new Label("");
    private Button quitB = new Button("Quit");
    // private boolean rapid;
    private GridPane root = new GridPane();
    private Label hangman = new Label();
    private EventHandler press;

    /**
     *
     * @param g so that we can access the information and logic based methods in
     * game.
     * @param back so that we can call main menu if the actionevent occurs
     */
    public Display(final Game g, EventHandler back, EventHandler quit) {
        backB.setOnAction(back);
        hintButton.setVisible(false);
        wordLabel = new Label("");
        guessTextField = new TextField();
        guessTextField.setPromptText("Guess a letter");
        guessedLetters = new Label("");
        PreviousLetters = new Label("Previously guessed letters:");
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
                    nextScene(true);
                } else if (g.getBoard().getLives() == 0) {
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
        /** adds buttons for the display screen
        **/
        root.setVgap(10);
        root.setHgap(50);
       // root.add(points, 12, 4);
       // root.add(timer, 12, 5);
        root.add(backB, 12, 3);
        root.add(PreviousLetters, 12, 1);
        root.add(guessedLetters, 12, 2);
        root.add(hangman, 12, 0);

        root.add(quitB,5,6);
        quitB.setOnAction(quit);
        root.add(guessTextField, 8, 2);
        root.add(wordLabel, 8, 1);
        root.add(enter, 8, 3);
        root.add(invalid, 8, 4);
        root.add(hintLabel, 8, 5);
        root.add(hintButton, 8, 6);

        this.getChildren().add(root);

    }

    /**
     *
     *
     * @param state
     * @param ai
     */
    public void updateWord(String state, boolean ai) {
        guessedLetters.setText("");
        hintButton.setVisible(false);
        hintLabel.setText("");
        wordLabel.setText(state);
        hangman.setGraphic(HangmanAni.getHangman(6));
        if (ai) {
            hintButton.setVisible(true);
        }
    }

    /**
     *
     * @param won whether the game is won or not
     */
    public void nextScene(boolean won) {
    }

}
