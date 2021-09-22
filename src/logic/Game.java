package logic;

import ai.RapidAI;
import ai.AI;
import ai.SmartAI;

/**
 *
 * @author Elijah
 */
public class Game {

    private Board board;
    private AI ai;
    private RapidAI rai;
    private SmartAI sai;

    /**
     * Create board.
     *
     */
    public void setup() {
        this.board = new Board();
    }

    /**
     * To quickly enable the AI and set it's difficulty
     *
     * @param diff is the difficulty of the AI
     */
    public void enableAI(String diff) {
        if (diff.equalsIgnoreCase("rapid")) {
            rai = new RapidAI();
            ai = rai;
            board.setWord(ai.pickWord());
            rai.getPointClass().start();
        } else if (diff.equalsIgnoreCase("smart")) {
            enableSmartAI();
        } else {
            ai = new AI(diff);
            board.setWord(ai.pickWord());
        }

    }

    /**
     *
     * @return the board instance variable
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * To get AI from game.
     *
     * @return the ai instance variable
     */
    public AI getAI() {
        return ai;
    }

    /**
     * Checks if all the letters are revealed and if they are then method
     * returns true
     *
     * @return whether the game has been won or not
     */
    public boolean checkWon() {
        for (boolean b : this.board.getLetterState()) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the letter was guessed before If it hasn't then adds it to the
     * list of guessed letters Then checks to see whether the letter is within
     * the secret word If it is then we update the board's state and return
     * true, If not then we reduce the lives and add the guess to badLetters.
     *
     * @param letter a letter the user guessed
     * @return whether the letter is a correct guess or not
     */
    public boolean isValidLetter(char letter) {
        //System.out.println(letter);
        this.board.addGuess(letter);
        if (this.board.getWord().indexOf(letter) != -1) {
            update(letter);
            return true;
        } else {
            this.board.updateLives();
            this.board.addBadLetter(letter);
            return false;
        }

    }

    /**
     * Updates the letter states within board, flipping them if the letter has
     * been revealed.
     *
     * @param letter a letter the user guessed
     */
    public void update(char letter) {
        for (int i = 0; i < this.board.getWord().length(); i++) {
            if (this.board.getWord().charAt(i) == letter) {
                this.board.updateState(i);
            }
        }

    }

    private void enableSmartAI() {
       // System.out.println(board.getWord().length());
        sai = new SmartAI(board.getWord().length(),board.getState());
    }

    public SmartAI getSmartAI() {
        return sai;
    }
}
