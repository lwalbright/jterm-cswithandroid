/*
 *  Copyright 2016 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.google.engedu.anagrams;

import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.util.Log;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    public ArrayList<String> wordList = new ArrayList<String>();
    public HashSet<String> wordSet = new HashSet<String>();
    private HashMap<String, ArrayList<String>> lettersToWord = new HashMap<>();

    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordSet.add(word);
            if(lettersToWord.containsKey(sortLetters(word))){
                lettersToWord.get(sortLetters(word)).add(word);
            }
            else{
                ArrayList<String> wordArray = new ArrayList<String>();
                wordArray.add(word);
                lettersToWord.put(sortLetters(word),wordArray);
            }
            //
            //  Your code here
            //
        }
    }

    public boolean isGoodWord(String word, String base) {
        if(!wordSet.contains(word)){
            return false;
        }
        if(word.toLowerCase().contains(base.toLowerCase())){ //TODO: idk if this works
            return false;
        }
        return true;
    }

    public ArrayList<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        String targetSort = sortLetters(targetWord);
        for(int i=0;i<wordList.size();i++){
            if(targetSort.equalsIgnoreCase(sortLetters(wordList.get(i)))){
                result.add(wordList.get(i));
            }
        }
        return result;
    }

    @VisibleForTesting
    static boolean isAnagram(String first, String second) {
        Log.d("Testing construct","reading test dict");
        //for(int i=0;i<words.length;i++){

       // }
        return true;
    }

    @VisibleForTesting
    static String sortLetters(String input) {
        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        String result = new String(chars);
        Log.d("results",result);
        //
        // Your code here
        //
        return result;
    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        for(int i=0;i<alphabet.length;i++){
            String sortedWord = sortLetters(word+alphabet[i]);
            ArrayList<String> anagrams = lettersToWord.get(sortedWord);
            if(anagrams!=null) {
                for (int j = 0; j < anagrams.size(); j++) {
                    result.add(anagrams.get(j));
                }
            }
        }


        return result;
    }

    public String pickGoodStarterWord() {
        //
        // Your code here
        //
        return "stop";
    }
}
