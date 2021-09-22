package ai;

import java.util.ArrayList;
import java.io.FileNotFoundException;

/**
 *
 * @author Elijah and Ali
 */
public class RapidAI extends AI {

    private PointClass pc;
    private ArrayList<String> prevWords;
    private String[] easyRapidList, mediumRapidList, hardRapidList;

    /**
     *  literally a useless constructor
     * @param diff 
     */
    public RapidAI(String diff) {
        this(); //rapidfire always starts in easy so even if somehow they pass in a difficulty it will just go straight to easy
    }

    /**
     * real constructor
     */
    public RapidAI() {
        easyRapidList = new String[15];
        easyRapidList[0] = "CAT";
        easyRapidList[1] = "SUN";
        easyRapidList[2] = "GHOST";
        easyRapidList[3] = "SNAKE";
        easyRapidList[4] = "APPLE";
        easyRapidList[5] = "BALL";
        easyRapidList[6] = "NOSE";
        easyRapidList[7] = "EGG";
        easyRapidList[8] = "WORM";
        easyRapidList[9] = "SPOON";
        easyRapidList[10] = "COOKIE";
        easyRapidList[11] = "BEACH";
        easyRapidList[12] = "GIRL";
        easyRapidList[13] = "WATER";
        easyRapidList[14] = "SWING";

        mediumRapidList = new String[15];
        mediumRapidList[0] = "ROUNDS";
        mediumRapidList[1] = "STATE";
        mediumRapidList[2] = "WHISTLE";
        mediumRapidList[3] = "PIRATE";
        mediumRapidList[4] = "AMERICA";
        mediumRapidList[5] = "COMPUTER";
        mediumRapidList[6] = "QUEEN";
        mediumRapidList[7] = "PALACE";
        mediumRapidList[8] = "DOMINOES";
        mediumRapidList[9] = "WHISK";
        mediumRapidList[10] = "MATTRESS";
        mediumRapidList[11] = "FROG";
        mediumRapidList[12] = "PINWHEEL";
        mediumRapidList[13] = "CIRCUS";
        mediumRapidList[14] = "COWBOY";

        hardRapidList = new String[25];
        hardRapidList[0] = "IMPORTANT";
        hardRapidList[1] = "SCREAM";
        hardRapidList[2] = "DIAGONAL";
        hardRapidList[3] = "HYDROGEN";
        hardRapidList[4] = "YOLK";
        hardRapidList[5] = "DOWNLOAD";
        hardRapidList[6] = "MACARONI";
        hardRapidList[7] = "VITAMIN";
        hardRapidList[8] = "BARGAIN";
        hardRapidList[9] = "PROFESSOR";
        hardRapidList[10] = "CHESTNUT";
        hardRapidList[11] = "PASSWORD";
        hardRapidList[12] = "CATALOG";
        hardRapidList[13] = "BLACKSMITH";
        hardRapidList[14] = "POSITIVE";
        hardRapidList[15] = "LYRICS";
        hardRapidList[16] = "CHAMPION";
        hardRapidList[17] = "SLUMP";
        hardRapidList[18] = "FLUTTER";
        hardRapidList[19] = "ARCHAEOLOGIST";
        hardRapidList[20] = "INQUISITION";
        hardRapidList[21] = "LANDFILL";
        hardRapidList[22] = "QUARANTINE";
        hardRapidList[23] = "PROTESTANT";
        hardRapidList[24] = "EXPONENTIAL";

        setWordList("easy");
        pc = new PointClass(); //so when you choose the first word you get 0 pc
        prevWords = new ArrayList<String>();
    }

    @Override
    public String pickWord() {
        //changing words based on how well the player is doing (could simply be that it randomizes from a certain index range of a rapid fire words list instead of this kind of method)
        if (pc.getPoints() <= 5) {
            pc.addPoints(1);

            setWordList("easy");
        } else if (pc.getPoints() > 5 && pc.getPoints() <= 10) {
            pc.addPoints(2);

            setWordList("medium");
        } else {
            pc.addPoints(3);

            setWordList("hard");
        }

        int jic = 0; // just in case: what if they go back to easy but have picked all the words? what if they go through all the words? 
        //this is a just in case variable to handle fringe cases and prevent an infinite loop
        String word = super.pickWord();
        while (prevWords.contains(word) && jic <= 10) {
            word = super.pickWord();
            jic++;
        }
        prevWords.add(word);
        return word;
    }

    @Override
    public void setWordList(String diff) {
        if (diff.equalsIgnoreCase("easy")) {
            wordList = easyRapidList;
        } else if (diff.equalsIgnoreCase("medium")) {
            wordList = mediumRapidList;
        } else {
            wordList = hardRapidList;
        }
    }

    /**
     *
     * @return the pointclass object
     */
    public PointClass getPointClass() {
        return pc;
    }
    
    public void callHighScore() {
	    pc.updateHighScore();
	}

    @Override
    public String toString() {
        return "Rapid";
    }

}
