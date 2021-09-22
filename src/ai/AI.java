package ai;

import java.util.*;

/**
 *
 * @author Ali
 */
public class AI {

    /**
     * declaration of lists and variables
     */
    protected String[] wordList;
    private String[] hintList;
    private int index;
    private String[] easyWordList, mediumWordList, hardWordList, easyHints, mediumHints, hardHints;

    /**
     * @param diff variable and sets the difficulty of the game
     *
     * Establishes the variables of the word lists and difficulties Establishes
     * the hints of the word lists Sets the difficulty of the game depending on
     * the input from the user
     */
    public AI(String diff) {

        easyWordList = new String[5];
        easyWordList[0] = "banana";
        easyWordList[1] = "ivy";
        easyWordList[2] = "jelly";
        easyWordList[3] = "socks";
        easyWordList[4] = "puppy";

        mediumWordList = new String[5];
        mediumWordList[0] = "zigzag";
        mediumWordList[1] = "elbow";
        mediumWordList[2] = "banjo";
        mediumWordList[3] = "canary";
        mediumWordList[4] = "blizzard";

        hardWordList = new String[5];
        hardWordList[0] = "mississippi";
        hardWordList[1] = "hippopotamus";
        hardWordList[2] = "xylophone";
        hardWordList[3] = "zombie";
        hardWordList[4] = "quadruple";

        easyHints = new String[5];
        easyHints[0] = "I’m fruity and yellow";
        easyHints[1] = "I’m a very irritating plant";
        easyHints[2] = "I’m squishy and edible";
        easyHints[3] = "I’ll keep you warm";
        easyHints[4] = "I bark";

        mediumHints = new String[5];
        mediumHints[0] = "I’m not straight, but I have straight lines";
        mediumHints[1] = "I’m humorous. Pun intended.";
        mediumHints[2] = "Pull my strings and I’ll make music";
        mediumHints[3] = "I can fly and I’m yellow";
        mediumHints[4] = "I can blow you away, but also make you freeze";

        hardHints = new String[5];
        hardHints[0] = "I’m formed by the Gulf of Mexico";
        hardHints[1] = "I’m a little overweight, but I love to swim";
        hardHints[2] = "You can make music with me";
        hardHints[3] = "I’m dead, but I’m alive";
        hardHints[4] = "When you multiply me by four";

        setWordList(diff);
//        if (diff.equalsIgnoreCase("easy")) {
//            wordList = easyWordList;
//            hintList = easyHints;
//        } else if (diff.equalsIgnoreCase("medium")) {
//            wordList = mediumWordList;
//            hintList = mediumHints;
//        //} else if (diff.equalsIgnoreCase("rapid")){
//            
//        } else {
//            wordList = hardWordList;
//            hintList = hardHints;
//        }

    }
    
    /**
     * default unused constructor
     */
    public AI(){
        
    }

    /**
     * Randomly picks a word from selected list to guess
     *
     * @return the word that was selected
     */
    public String pickWord() {
        Random generator = new Random();
        int x = generator.nextInt(wordList.length);
        setIndex(x);
        return wordList[index].toUpperCase();
    }

    /**
     *
     * @param idx changes the index to a specific in the list of words
     */
    protected void setIndex(int idx) {
        if (idx >= 0 && idx < wordList.length) {
            index = idx;
        } else {
            index = 0;
        }
    }

    /**
     *
     * @param diff to set the difficulty of the words
     */
    protected void setWordList(String diff) {
        if (diff.equalsIgnoreCase("easy")) {
            wordList = easyWordList;
            hintList = easyHints;
        } else if (diff.equalsIgnoreCase("medium")) {
            wordList = mediumWordList;
            hintList = mediumHints;
        } else {
            wordList = hardWordList;
            hintList = hardHints;
        }
    }

    /**
     * @return the hint for corresponding word from list
     */
    public String getHint() {
        return hintList[index];
    }

    
}
