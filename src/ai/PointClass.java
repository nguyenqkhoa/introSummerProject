package ai;

import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.io.*;

public class PointClass {

    private int points;
    public static int highscore;
    private Timeline timer;

    private int seconds = 0;
    private Label time = new Label("1:00");

    /**
     * creates new pointclass and initializes timer while setting points to -1
     * so at the start it will be 0
     */
    public PointClass() {
        points = -1;
        timer = new Timeline(new KeyFrame(Duration.seconds(1), new StopwatchHandler()));

    }

    /**
     * starts the timer
     */
    public void start() {
        timer.setCycleCount(Animation.INDEFINITE);
        timer.play();
    }

    private class StopwatchHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent ae) {
            // System.out.println("countdown");
            countdown();
//            if (seconds >= 60) {
//                time.setText(seconds / 60 + ":" + (seconds - (seconds / 60) * 60));
//            } else {
            time.setText("" + seconds);
//            }

            if (seconds <= 0) {
                timer.stop();
            }
            //update time variable with one second less
        }
    }

    /**
     *
     * @return the number of points
     */
    public int getPoints() {
        return this.points;
    }

    /**
     *
     * @param x the number for points to add
     */
    public void addPoints(int x) {
        updateTimeWithCorrectGuesses();
        points += x;
    }

    /**
     *
     * @param temp to assign the reference in the GUI to this label that we can
     * mess with
     */
    public void setLabel(Label temp) {
        this.time = temp;

    }

    /**
     * subtract one second
     */
    public void countdown() {
        this.seconds--;

    }

    /**
     * add a minute to the timer
     */
    public void updateTimeWithCorrectGuesses() {
        this.seconds += 60;

    }

    public void gameOver() {
        seconds = 0;
        timer.stop();
    }

    /**
     *
     * @return if the timer is done
     */
    public boolean isDone() {
        return seconds <= 0;
    }

    public void updateHighScore() {
        if (this.points > highscore) {

            saveHighScore(this.points);

            readHighScore();
        }

    }

    public static void saveHighScore(int newHS) {
      //  System.out.println("this happened");
        try {
            PrintWriter out = new PrintWriter("hs.txt");
            out.println(newHS);
            //  System.out.print(this.highscore);

            out.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public static void readHighScore() {
        try {
            FileReader fr = new FileReader("hs.txt");
            BufferedReader br = new BufferedReader(fr);
            highscore = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
