package graphics;

import ai.RapidAI;
import ai.SmartAI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
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
 * @author Usama
 */
public class SmartScreen extends VBox {

    // private TextField guessTextField;
    private Label guessedLetters;//, timer = new Label("ff"), points = new Label("");

    private Label PreviousLetters;
    private Label wordLabel;
    //private Button enter = new Button("Enter guess");
    //private Button hintButton = new Button("Get hint");
    private Button backB = new Button("Forfeit");
    //private Label hintLabel = new Label(""), invalid = new Label("");
    private Button quitB = new Button("Quit");
    // private boolean rapid;
    private GridPane root = new GridPane();
    private Label hangman = new Label();
    private EventHandler press;
    private SmartAI sai;

    /**
     *
     * @param g so that we can access the information and logic based methods in
     * game.
     * @param back so that we can call main menu if the actionevent occurs
     */
    public SmartScreen(final Game g, EventHandler back, EventHandler quit) {
        backB.setOnAction(back);
        wordLabel = new Label("");
        // guessTextField = new TextField();
        //  guessTextField.setPromptText("Guess a letter");
        guessedLetters = new Label("");
        PreviousLetters = new Label("Previously guessed letters:");
        hangman.setGraphic(HangmanAni.getHangman(6));

        // guessTextField.setOnAction(press);
        //enter.setOnAction(press);
        /**
         * adds buttons for the display screen
         *
         */
        root.setVgap(10);
        root.setHgap(50);
        root.add(backB, 12, 3);
        root.add(PreviousLetters, 12, 1);
        root.add(guessedLetters, 12, 2);
        root.add(hangman, 12, 0);
        root.add(wordLabel, 8, 1);
        root.add(quitB, 5, 6);
        quitB.setOnAction(quit);
        // root.add(guessTextField, 8, 2);

        this.getChildren().add(root);

    }

    /**
     *
     *
     * @param state
     * @param ai
     */
    public void updateWord(Game game) {
        guessedLetters.setText("");
        // hintButton.setVisible(false);
        // hintLabel.setText("");
        wordLabel.setText(game.getBoard().getState());
        hangman.setGraphic(HangmanAni.getHangman(6));
        sai = game.getSmartAI();
        //System.out.println("IT CRASHED");
        //doTheThing(game);
        //System.out.println(sai);

    }

    /**
     *
     * @param won whether the game is won or not
     */
    public void nextScene(boolean won) {
    }

    public void doTheThing(final Game g) {        
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                if (sai != null && g.isValidLetter(sai.guessLetter())) {
                    sai.updateWord(g.getBoard().getState());
                }

                wordLabel.setText(g.getBoard().getState());
                guessedLetters.setText(g.getBoard().getBadLetters());
                hangman.setGraphic(HangmanAni.getHangman(g.getBoard().getLives()));
                if (g.checkWon()) {
                    nextScene(true);
                    return;
                } else if (g.getBoard().getLives() == 0) {
                    nextScene(false);
                    return;
                }
              doTheThing(g);
            }
        });
        new Thread(sleeper).start();
//        try {
//            this.wait(10000000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(SmartScreen.class.getName()).log(Level.SEVERE, null, ex);
//        }
        

    }

}
