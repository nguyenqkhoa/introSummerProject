package logicTestFiles;

import logic.Board;
import logic.Game;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for Board
 */
public class BoardTest{
	
	public class B extends Board{
		public B(){};
		public B(String wrd){super(wrd);}
	}
    
    public BoardTest() {
    }

    /**
     * Test of setWord method, of class Board.
     */
    @Test
    public void testSetWord_0() {
		Board b = new Board("TestEmptyString");
        b.setWord("");
        assertNotSame("Setting word to empty string", "", b.getWord());
     }

	 /**
     * Test of setWord method, of class Board.
     */
    @Test
    public void testSetWord_1() {
		Board b = new Board("TestWordRegal");
        b.setWord("regal");
        assertEquals("Setting word to REGAL", "REGAL", b.getWord());
     }
     
    /**
     * Test of setWord method, of class Board.
     */
    @Test
    public void testSetWord_2() {
		Board b = new Board("TestWordUpperandLowerCase");
        b.setWord("ForGiVen");
        assertEquals("Setting word to FORGIVEN", "FORGIVEN", b.getWord());
     }
     
    /**
     * Test of setWord method, of class Board.
     */
    @Test
    public void testSetWord_3() {
		Board b = new Board("TestNumber");
        b.setWord("324");
        assertNotSame("Setting word to numbers", "", b.getWord());
     }
     
    /**
     * Test of setWord method, of class Board.
     */
    @Test
    public void testSetWord_4() {
		Board b = new Board("TestWordWithNumber");
        b.setWord("Leg1t");
        assertNotSame("Setting word to word with a number", "", b.getWord());
     }
     
       
    /**
     * Test of setWord method, of class Board.
     */
    @Test
    public void testSetWord_5() {
		Board b = new Board("TestSymbol");
        b.setWord("!@?#$#$");
        assertNotSame("Setting word to symbols", "", b.getWord());
     }



    /**
     * Test of setLives method, of class Board.
     */
    @Test
    public void testSetLives_0() {
		Board b = new Board();
        b.setLives(6);
        assertEquals("Setting lives to 6", 6, b.getLives());
    }
    
    /**
     * Test of setLives method, of class Board.
     */
    @Test
    public void testSetLives_1() {
		Board b = new Board();
        b.setLives(7);
        assertEquals("Setting lives over the limit to 7", 6, b.getLives());
    }
    
    /**
     * Test of setLives method, of class Board.
     */
    @Test
    public void testSetLives_2() {
		Board b = new Board();
        b.setLives(-3);
        assertEquals("Setting lives to a negative number -3", 6, b.getLives());
    }
    
     /**
     * Test of setLives method, of class Board.
     */
    @Test
    public void testSetLives_3() {
		Board b = new Board();
        b.setLives(4);
        assertEquals("Setting lives under the limit to 4", 4, b.getLives());
    }

    /**
     * Test of addGuess method, of class Board.
     * */
    @Test
    public void testAddGuess_0() {
        Board b = new Board();
        char guess = 'X';
        b.addGuess(guess);
        assertEquals("Adding a guess of 'X' to the guesses", 'X', (char)b.getGuesses().get(0));
    }
    
    
    /**
     * Test of addGuess method, of class Board.
     * */
    @Test
    public void testAddGuess_1() {
        Board b = new Board();
        b.addGuess('Y');
        b.addGuess('F');
        b.addGuess('P');
        ArrayList<Character> guesses = b.getGuesses();
        char guess = 'H';
        b.addGuess(guess);
        assertEquals("Adding a guess of 'H' to the guesses", 'H', (char)b.getGuesses().get(3));
    }
    
    /**
     * Test of addGuess method, of class Board.
     * */
    @Test
    public void testAddGuess_2() {
        Board b = new Board();
        ArrayList<Character> guesses = new ArrayList<Character>();
        b.addGuess('Y');
        b.addGuess('F');
        b.addGuess('P');
        char guess = 'H';
        b.addGuess(guess);
        assertEquals("Adding a guess of 'H' to the guesses, getting the second entry", 'F', (char)b.getGuesses().get(1));
    }

}
