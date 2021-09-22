package graphics;

import dictionary.WordList;
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
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logic.Board;

/**
 *
 * @author Ali
 */
public class WordInput extends VBox {

    private PasswordField wordTextField;
    private Label prompt, invalid, secret;
    private Button executeButton, showWord;
    private GridPane root = new GridPane();

    /**
     *
     * @param b since we need information from the board
     * @param back  to pass through the ability to go back
     */
    public WordInput(final Board b, EventHandler back, EventHandler quit) {
        Button backButton = new Button("Go Back");
        backButton.setOnAction(back);
        Button quitB = new Button("Quit");
        quitB.setOnAction(quit);
        wordTextField = new PasswordField();
        executeButton = new Button("Submit");
        showWord = new Button("Show secret word");
        prompt = new Label("Input your word to guess");
        invalid = new Label("");
        secret = new Label("");
        root.setVgap(20);
        root.setHgap(10);
        root.add(prompt, 55, 20);
        root.add(showWord, 55, 22);
        root.add(invalid, 55, 23);
        root.add(wordTextField, 55, 21);
        root.add(executeButton, 56, 21);
        root.add(secret, 56, 22);
        root.add(backButton, 60, 25);
        root.add(quitB,20,25);
        this.getChildren().add(root);
        showWord.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                secret.setText(wordTextField.getText());
            }
        });
        EventHandler<ActionEvent> press = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //formerly of secretWordInput (written by Usama)
                // checks if word to be guessed is a valid entry and if so goes to next scene
                if (!wordTextField.getText().isEmpty() && wordTextField.getText().matches("[a-zA-Z]+") && WordList.isWord(wordTextField.getText())) {
                    b.setWord(wordTextField.getText().toUpperCase());
                    wordTextField.setText("");
                    invalid.setText("");
                    nextScene();
                } else {
                    invalid.setText("Your word is not valid.");
                }

            }
        };

        wordTextField.setOnAction(press);
        executeButton.setOnAction(press);

    }

    public void update() {
        invalid.setText("");
        secret.setText("");
        wordTextField.setText("");
    }
    
    public void nextScene() {
    }
}
