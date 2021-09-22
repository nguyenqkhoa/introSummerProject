package logic;

import java.util.*;

/**
 *
 * @author Elijah
 */
public class Board {

    // create variables
    private int lives;
    private boolean[] letterState;
    private String word;
    private ArrayList<Character> guesses;
    private String badLetters;

    /**
     * create the instance for the lives, guesses and badLetters
     */
    public Board() {
        this.lives = 6;
        this.guesses = new ArrayList();
        this.badLetters = "";
    }

    /**
     * set a new Board
     *
     * @param wrd the current word
     */
    public Board(String wrd) {
        this.lives = 6;
        this.setWord(wrd);
        this.guesses = new ArrayList();
        this.badLetters = "";
    }

    /**
     * a copy constructor for privacy leaks
     * @param toCopy
     */
    public Board(Board toCopy) {
        this.lives = toCopy.getLives();
        this.setWord(toCopy.getWord());
        this.guesses = toCopy.getGuesses();
        this.badLetters = toCopy.getBadLetters();
        for (int i = 0; i < toCopy.getLetterState().length; i++) {
            if (!toCopy.getLetterState()[i]) {
                this.updateState(i);
            }

        }
    }

    /**
     * getter method for the current lives
     *
     * @return current lives
     */
    public int getLives() {
        return this.lives;
    }

    /**
     * getter method of the current word
     *
     * @return current word
     */
    public String getWord() {
        return this.word;
    }

    /**
     * getter method of the letter state
     *
     * @return current letter state
     */
    public boolean[] getLetterState() {
        return this.letterState;
    }

    /**
     * getter method for the state show the current state of the word length
     * with _
     *
     * @return current state
     */
    public String getState() {
        String state = "";
        for (int i = 0; i < this.word.length(); i++) {
            if (this.letterState[i]) {
                state += this.word.substring(i, i + 1 )+ " ";
            } else {
                state += "_ ";
            }
        }
        return state;
    }

    /**
     * getter the method for the current guesses
     *
     * @return current guesses
     */
    public ArrayList<Character> getGuesses() {
        ArrayList<Character> temp = new ArrayList();
        temp.addAll(guesses);
        return temp;
    }

    /**
     * setter the method for the word, fills letterStates with false for each
     * letter in word
     *
     * @param wrd
     */
    public void setWord(String wrd) {
        this.word = wrd;
        this.letterState = new boolean[wrd.length()];
        Arrays.fill(letterState, false);
    }

    /**
     * setter method for current lives
     *
     * @param life is the value lives are set to
     */
    public void setLives(int life) {
        this.lives = life;
    }

    /**
     * mutator method for guesses
     *
     * @param guess for current guess
     */
    public void addGuess(char guess) {
        this.guesses.add(guess);
    }
    
    /**
     * a method to flip the state of a letter
     *
     * @param index of the letter
     */
    public void updateState(int index) {
        this.letterState[index] = !this.letterState[index];
    }

    /**
     * setter method for current bad letters
     *
     * @param guess for current guess
     */
    public void addBadLetter(char guess) {
        this.badLetters += guess + " ";
    }

    /**
     * getter method for current bad letters
     *
     * @return current bad letters
     */
    public String getBadLetters() {
        return this.badLetters;
    }

    /**
     * updates lives
     */
    public void updateLives() {
        this.lives--;
        if (lives <0){
        lives = 0;}
    }

    /**
     * method for resetting the game
     */
    public void reset() {
        this.lives = 6;
        this.letterState = null;
        this.word = "";
        this.badLetters = "";
        this.guesses.clear();
    }
}
    