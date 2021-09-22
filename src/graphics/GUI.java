package graphics;

import graphics.DiffChoose;
import logic.Board;
import logic.Game;

import java.util.Scanner;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Elijah
 */
public class GUI extends Application {

    //frontend instance variables
    //the stage of the application throughout the program
    private Stage window;
    //the variaous screens the GUI displays
    private MainMenu menu;
    private WordInput input;
    private DiffChoose diff;
    private Display disp;
    private RapidDisplay rapDisp;
    private EndScreen end;
    private SmartScreen sScreen;

    //backend instance variables
    private Game game;
    private Board board;
    private boolean smart = false;

    /**
     * The start method for this Application subclass. Uses the various
     * implementations of VBox to change the scene of the window and progress
     * through the game.
     *
     * @param stage the window that is being presented to the user
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        //take in stage
        window = stage;
        //create a new game
        game = new Game();
        game.setup();
        //store our board in the class locally because we alter the board in both game and gui (not a privacy leak this is 100% intentional)
        board = game.getBoard();
        HangmanAni.update();
        EventHandler<ActionEvent> back = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                smart = false;
                menu.update();
                window.getScene().setRoot(menu);
            }

        };

        EventHandler<ActionEvent> quit = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }

        };
        //create the screens as they will be displayed
        menu = new MainMenu(quit) { //first screen seen
            @Override
            public void nextScene(int target) {
                switch (target) {
                    //if PvP chosen then the next screen shown is the word input
                    case 1:
                        window.getScene().setRoot(input);
                        input.update();
                        input.getStylesheets().add("HangmanBlank.css");
                        break;
                    //if PvE chosen then the next screen shown is the difficulty choice
                    case 2:
                        window.getScene().setRoot(diff);

                        diff.getStylesheets().add("HangmanBlank.css");
                }
            }
        };
        //privacy leak with board is absolutely necessary because we have to alter the board information based on user input
        input = new WordInput(board, back, quit) { //possible second screen
            @Override
            public void nextScene() {
                //update the word with the chosen word before entering the game screen
                if (!smart) {
                    disp.updateWord(board.getState(), false);
                    window.getScene().setRoot(disp);
                    disp.getStylesheets().add("HangmanBlank.css");
                } else {
                    game.enableAI("smart");

                    sScreen.updateWord(game);
                    window.getScene().setRoot(sScreen);
                    sScreen.getStylesheets().add("HangmanBlank.css");
                    sScreen.doTheThing(game);

                    smart = false;

                }
            }
        };
        //privacy leak with game is absolutely necessary because we have to alter the game based on user input
        diff = new DiffChoose(game, back, quit) { //possible second screen
            @Override
            public void nextScene(String type) {
                //update the word with the randomly selected word before entering the game screen
                switch (type) {
                    case "rapid":
                        rapDisp.updateWord(game);
                        window.getScene().setRoot(rapDisp);
                        rapDisp.getStylesheets().add("HangmanBlank.css");
                        break;
                    case "smart":
                        smart = true;
                        window.getScene().setRoot(input);
                        input.update();
                        input.getStylesheets().add("HangmanBlank.css");
                        break;
                    default:
                        disp.updateWord(board.getState(), true);
                        window.getScene().setRoot(disp);
                        disp.getStylesheets().add("HangmanBlank.css");
                        break;
                }
            }
        };

        sScreen = new SmartScreen(game, back, quit) {
            @Override
            public void nextScene(boolean won) {
                //before entering the end screen, update it with the results of the game
                end.updateResult(game);
                window.getScene().setRoot(end);
                end.getStylesheets().add("HangmanBlank.css");

            }
        };

        //would be a hassel and half to be exact and not throw in a game
        disp = new Display(game, back, quit) {
            @Override
            public void nextScene(boolean won) {
                //before entering the end screen, update it with the results of the game
                end.updateResult(game);
                window.getScene().setRoot(end);
                end.getStylesheets().add("HangmanBlank.css");

            }
        };

        rapDisp = new RapidDisplay(game, quit) {
            @Override
            public void nextScene(boolean won) {
                // System.out.println("hey this was called");
                if (!won) {
                    //before entering the end screen, update it with the results of the game
                    end.updateResult(game);
                    window.getScene().setRoot(end);
                    end.getStylesheets().add("HangmanBlank.css");
                } else {

                    board.reset();
                    board.setWord(game.getAI().pickWord());
                    this.updateWord(game);
                }

            }
        };
        end = new EndScreen(quit) {
            @Override
            public void nextScene() {
                board.reset();
                menu.update();
                window.getScene().setRoot(menu);
            }
        };

        menu.getStylesheets().add("Hangman.css");
        window.setScene(new Scene(menu, 1200, 700));
        window.setTitle("HANGMAN");
        window.show();
    }

    /**
     * The main method which runs when the program begins.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
