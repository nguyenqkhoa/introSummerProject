/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author purpl
 */
public class ElevenLetterWords extends WordList {

    public ElevenLetterWords() {
        super.letters = 11;
        //8374
        String[] words = new String[8374];
        try (BufferedReader br = new BufferedReader(new FileReader("11LetterDictionary.txt"))) {
            int count = 0;
            String word = br.readLine().replaceAll("\"", "").replaceAll(",", "").trim();
            while (word != null) {
                words[count] = word;
                count++;
                word = br.readLine();
                if (word != null) {
                    word = word.replaceAll("\"", "").replaceAll(",", "").trim();
                }
            }
//            for (String word1 : words) {
//                System.out.println(word1);
//            }
//            System.out.println(count);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }

        super.addAll(Arrays.asList(words));
    }
}
