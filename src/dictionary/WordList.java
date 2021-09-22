/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author purpl
 */
public abstract class WordList<T> extends ArrayList {

    protected int letters;

    public int getLength() {
        return letters;
    }

    public static boolean isWord(String word) {
        WordList words;
        int length = word.length();
        switch (length) {
            case 3:
                words = new ThreeLetterWords();
                // System.out.println(words.size());
                break;
            case 4:
                words = new FourLetterWords();
                break;
            case 5:
                words = new FiveLetterWords();
                break;
            case 6:
                words = new SixLetterWords();
                break;
            case 7:
                words = new SevenLetterWords();
                break;
            case 8:
                words = new EightLetterWords();
                break;
            case 9:
                words = new NineLetterWords();
                break;
            case 10:
                words = new TenLetterWords();
                break;
            case 11:
                words = new ElevenLetterWords();
                break;
            case 12:
                words = new TwelveLetterWords();
                break;
            case 13:
                words = new ThirteenLetterWords();
                break;
            case 14:
                words = new FourteenLetterWords();
                break;
            case 15:
                words = new FifteenLetterWords();
                break;
            case 16:
                words = new SixteenLetterWords();
                break;
            default:
                words = new OtherLetterWords();
        }
        return words.contains(word);
    }
}
