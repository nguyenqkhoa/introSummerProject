/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import dictionary.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author purpl
 */
public class SmartAI {

    private WordList<String> words;
    private ArrayList<Character> letters;
    private ArrayList<Character> filtered;
    private String word;
    private String ranking = "ETAOINSHRDLCUMWFGYPBVKJXQZ";
    private Character[] vowels = new Character[]{'E', 'A', 'O', 'I', 'U'}, topCon = new Character[]{'T', 'N', 'S', 'H', 'R'}, secCon = new Character[]{'D', 'L', 'C', 'M', 'W'}, triCon = new Character[]{'F', 'G', 'Y', 'P', 'B'}, badCon = new Character[]{'V', 'K', 'J', 'X', 'Q', 'Z'};
    //private Character[] vowels = new Character[]{'e', 'a', 'o', 'i', 'u'}, cons = new Character[]{'t', 'n', 's', 'h', 'r', 'd', 'l', 'c', 'm', 'w', 'f', 'g', 'y', 'p', 'b', 'v', 'k', 'j', 'x', 'q', 'z'};

    private int guessNum = 0;

    public SmartAI(int length, String state) {
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
        letters = new ArrayList<>(length);
        filtered = new ArrayList<>();
        word = state;
    }

    public Character guessLetter() {
        Random r = new Random();
        int randIndex;
        guessNum++;
        Character guess = ' ';
        if (!hasLetter(word)) {
            // System.out.println("this one");
            randIndex = r.nextInt(vowels.length);
            if (randIndex >= vowels.length / 2) {
                randIndex = r.nextInt(vowels.length);
            }
            guess = Character.toUpperCase(vowels[randIndex]);

        } else {
            String temp = pickWord().toUpperCase();
            //System.out.println(temp);
            guess = topLetter(temp.toCharArray());
        }
        if (!letters.contains(guess)) {
            letters.add(guess);
            return guess;
        } else {
            //  System.out.println("whoops");
            return guessLetter();
        }

    }

    public boolean hasLetter(String s) {
        char[] l = s.toCharArray();
        for (int i = 0; i < l.length; i++) {
            //System.out.println("letter " + l[i] + " " + (l[i] != '_' && l[i] != ' '));
            if (l[i] != '_' && l[i] != ' ') {
                return true;
            }

        }
        return false;
    }

    public String pickWord() {
        Random generator = new Random();
        // System.out.println(words.size());

        int x = generator.nextInt(words.size());
        return (String) words.get(x);
    }

    public void updateWord(String state) {
        word = state;
        //System.out.println(word + " " + (guessNum > 0 && hasLetter(word)));
        if (guessNum > 0 && hasLetter(word)) {
            updateList();
        }
    }

    public void updateList() {
        char temp[] = word.replaceAll(" ", "").toCharArray();
        for (int i = 0; i < temp.length; i++) {
            char sect = temp[i];
            if (!filtered.contains(sect) && hasLetter(sect + "")) {
                int ind = word.indexOf(sect);
                //System.out.println(sect + "  " + i + "  " + word.replaceAll(" ", "").lastIndexOf(sect));
                onlyWith(sect, word.replaceAll(" ", "").indexOf(sect), countLetters(word, sect));
//                if (ind == word.lastIndexOf(sect)) {
//                    onlyWith(sect, i);
//                } else {
//                    onlyWith(sect, i);
//                    onlyWith(sect, word.lastIndexOf(sect));
//                }
                filtered.add(sect);
            }
        }

    }

    public char topLetter(char[] wrd) {
        char top = wrd[0];
        int j = 0;
        while (letters.contains(top) && j < wrd.length) {
            top = wrd[j++];
        }
        int ind = ranking.indexOf(top);
        for (int i = 1; i < wrd.length; i++) {
            int temp = ranking.indexOf(wrd[i]);
            //System.out.println("Letter: " + wrd[i] + " ranking: " + temp + " Top: " + top + " , " + ind);
            if (temp > ind && !letters.contains(wrd[i])) {
                top = wrd[i];
                ind = temp;
            }
        }
        return top;

    }

    public int countLetters(String wrd, char c) {
        if (wrd.length() == 0) {
            return 0;
        } else if (wrd.length() == 1 && wrd.charAt(0) == c) {

            return 1;
        } else if (wrd.indexOf(c) == -1) {

            return 0;
        } else {

            return countLetters(wrd.charAt(0) + "", c) + countLetters(wrd.substring(1), c);
        }
    }

    public void onlyWith(char c, int index, int count) {
        if (index < words.getLength()) {
            //System.out.println(count);
            //for (int i = 0; i < count; i++) {
            List<String> wlist = new ArrayList<String>(words);

            for (String wrd : wlist) {
                int ind = wrd.toUpperCase().indexOf(c, index);
                // System.out.print(wrd + "  " + ind + " " + (ind == -1 || ind != index) + " ");
                if (ind == -1 || (ind != index && index != wrd.toUpperCase().lastIndexOf(c))) {
                    words.remove(wrd);

                } else if (countLetters(wrd.toUpperCase(), c) != count) {
                    words.remove(wrd);
                }
            }
            // System.out.println(words.toString());
            // }
            // System.out.println(words.size());

        }
    }
}
