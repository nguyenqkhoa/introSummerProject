package logicTestFiles;

import logic.Board;
import logic.Game;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *Test cases for Game
 */
public class GameTest {
    
    public GameTest() {
    }
    
    @Test
    public void testIsValidLetter_0() {
		Game g = new Game();	
		g.setup();
		Board b = g.getBoard();
		b.setWord("APEX"); 
        char letter = ('A');
        assertEquals("Tried to enter a letter, A", true, g.isValidLetter(letter));
    }
    
    @Test
    public void testIsValidLetter_1() {
		Game g = new Game();	
		g.setup();
		Board b = g.getBoard();
		b.setWord("DESTINY"); 
        char letter = ('T');
        assertEquals("Tried to enter a letter, T", true, g.isValidLetter(letter));
    }
    
    @Test
    public void testIsValidLetter_2() {
		Game g = new Game();	
		g.setup();
		Board b = g.getBoard();
		b.setWord("EPIC"); 
        char letter = ('X');
        assertEquals("Tried to enter a letter, X", false, g.isValidLetter(letter));
    }    
    
    @Test
    public void testIsValidLetter_3() {
		Game g = new Game();	
		g.setup();
		Board b = g.getBoard();
		b.setWord("NUMBER"); 
        char letter = ('5');
        assertEquals("Tried to enter a number, 5", false, g.isValidLetter(letter));
    }
    
    @Test
    public void testcheckWon_0(){
		Game g = new Game();
		g.setup();
		Board b = g.getBoard();
		b.setWord("HELP");
		g.update('H');
		g.update('E');
		g.update('P');
		assertEquals("Testing for the letters currently present", false, g.checkWon());
		char letter = ('L');
		g.update(letter);
		assertEquals("Tried to enter the last letter, L, in HELP", true, g.checkWon());	
		/**For the sake of testing if the letters show up
		String c = "";
		for (int i = 0; i < guesses.size(); i++) {
			c = c+ guesses.get(i);
		}
		System.out.print("" + c);
		* */
	}
    
    @Test
    public void testcheckWon_1(){
		Game g = new Game();
		g.setup();
		Board b = g.getBoard();
		b.setWord("PIZZA");
		g.update('P');
		g.update('E');
		g.update('I');
		char letter = ('A');
		g.update(letter);
		assertEquals("Tried to enter letter A, in PIZZA, word is still incomplete", false, g.checkWon());
		/** For the sake of testing if the letters show up
		for (int i = 0; i < guesses.size(); i++) {
			d = d+ guesses.get(i);
		}
		System.out.print("" + d);
		* */
	}
	
	

}

