package graphics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Riyad
 */
public class HangmanAni {

    //Creating a Group object  
//    private ImageView iv6;
//    private ImageView iv5;
//    private ImageView iv4;
//    private ImageView iv3;
//    private ImageView iv2;
//    private ImageView iv1;
//    private ImageView iv0;
    //CHANGE THIS TO REFLECT LOCATION OF SOURCE FILES
    static private String path = "/home/ugd/khoa.nguyen1/Desktop/Java Summer/Interactive Demo/g/src/";

    static private ImageView iv6;
    static private ImageView iv5;
    static private ImageView iv4;
    static private ImageView iv3;

    static private ImageView iv2;

    static private ImageView iv1;

    static private ImageView iv0;

    //Creating an image C:\Users\purpl\Documents\NetBeansProjects\HangmanApp\src\Animation

    public static void update() {
        try {
            iv6 = new ImageView(new Image(new FileInputStream(path + "Animation/6lives.png")));
            iv5 = new ImageView(new Image(new FileInputStream(path + "Animation/5lives.png")));
            iv4 = new ImageView(new Image(new FileInputStream(path + "Animation/4lives.png")));
            iv3 = new ImageView(new Image(new FileInputStream(path + "Animation/3lives.png")));

            iv2 = new ImageView(new Image(new FileInputStream(path + "Animation/2lives.png")));

            iv1 = new ImageView(new Image(new FileInputStream(path + "Animation/1life.png")));

            iv0 = new ImageView(new Image(new FileInputStream(path + "Animation/0lives.png")));
            //Setting the position of the image

            iv6.setX(
                    50);
            iv6.setY(
                    25);
            iv5.setX(
                    50);
            iv5.setY(
                    25);
            iv4.setX(
                    50);
            iv4.setY(
                    25);
            iv3.setX(
                    50);
            iv3.setY(
                    25);
            iv2.setX(
                    50);
            iv2.setY(
                    25);
            iv1.setX(
                    50);
            iv1.setY(
                    25);
            iv0.setX(
                    50);
            iv0.setY(
                    25);

            //setting the fit height and width of the image view
            iv6.setFitHeight(
                    500);
            iv6.setFitWidth(
                    500);
            iv5.setFitHeight(
                    500);
            iv5.setFitWidth(
                    500);
            iv4.setFitHeight(
                    500);
            iv4.setFitWidth(
                    500);
            iv3.setFitHeight(
                    500);
            iv3.setFitWidth(
                    500);
            iv2.setFitHeight(
                    500);
            iv2.setFitWidth(
                    500);
            iv1.setFitHeight(
                    500);
            iv1.setFitWidth(
                    500);
            iv0.setFitHeight(
                    500);
            iv0.setFitWidth(
                    500);
            //Setting the preserve ratio of the image view
            iv6.setPreserveRatio(
                    true);
            iv5.setPreserveRatio(
                    true);
            iv4.setPreserveRatio(
                    true);
            iv3.setPreserveRatio(
                    true);
            iv2.setPreserveRatio(
                    true);
            iv1.setPreserveRatio(
                    true);
            iv0.setPreserveRatio(
                    true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HangmanAni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param lives the current number of lives
     * @return the animation for that number of lives
     */
    public static ImageView getHangman(int lives) {
        switch (lives) {
            case 0:
                return iv0;
            case 1:
                return iv1;
            case 2:
                return iv2;
            case 3:
                return iv3;
            case 4:
                return iv4;
            case 5:
                return iv5;
            case 6:
                return iv6;
        }
        return iv6;
    }

}
