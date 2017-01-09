/* Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.ghost;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import static android.R.attr.id;
import static com.google.engedu.ghost.R.id.ghostText;


public class GhostActivity extends AppCompatActivity {
    private static final String TAG = "GhostActivity";

    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private static final String KEY_USER_TURN = "keyUserTurn";
    private static final String KEY_CURRENT_WORD = "keyCurrentWord";
    private static final String KEY_SAVED_STATUS = "keySavedStatus";

    private SimpleDictionary dictionary;
    private boolean userTurn = false;
    private Random random = new Random();
    private String currentWord = "";

    private boolean winner = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost);
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("words.txt");
            dictionary = new SimpleDictionary(inputStream);
            // Initialize your dictionary from the InputStream.
        } catch (IOException e) {
            Toast toast = Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG);
            toast.show();
        }
        onStart(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //System.out.println("KeyCode: "+event.getKeyCode());
        //System.out.println("Key:      "+event.getCharacters());
        if(!winner) {
            int uni = event.getUnicodeChar();
            System.out.println("keyup called");

            if (uni >= 'a' && uni <= 'z') {
                currentWord += Character.toString((char) uni);
                ((TextView) findViewById(R.id.ghostText)).setText(currentWord);
                if (dictionary.isWord(currentWord)&&currentWord.length()>=4) {
                    ((TextView) findViewById(R.id.gameStatus)).setText(getString(R.string.compwin));
                    winner = true;
                } else {
                    computerTurn();
                    if (dictionary.isWord(currentWord)&&currentWord.length()>=4) {
                        ((TextView) findViewById(R.id.gameStatus)).setText(getString(R.string.userwin));//user
                        winner = true;
                    }
                }
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     * @param view
     * @return true
     */
    public boolean onStart(View view) {
        userTurn = random.nextBoolean();
        TextView text = (TextView) findViewById(ghostText);
        text.setText("");
        TextView label = (TextView) findViewById(R.id.gameStatus);
        if (userTurn) {
            label.setText(USER_TURN);
        } else {
            label.setText(COMPUTER_TURN);
            computerTurn();
        }
        return true;
    }

    private void computerTurn() {
        TextView label = (TextView) findViewById(R.id.gameStatus);
        // Do computer turn stuff then make it the user's turn again
        if(dictionary.isWord(currentWord)&&currentWord.length()>=4){
            label.setText(getString(R.string.compwin));//comp
            winner=true;
        }
        else if(dictionary.getAnyWordStartingWith(currentWord)==null){
            label.setText(getString(R.string.compwin));//comp
            winner=true;
        }
        else{
            String word = dictionary.getAnyWordStartingWith(currentWord);
            int len = currentWord.length();
            String letter = word.substring(len,len+1);
            currentWord+=letter;
            ((TextView) findViewById(R.id.ghostText)).setText(currentWord);

        }
        if(!winner){
            userTurn=true;
            label.setText(USER_TURN);
        }

        //userTurn = true;
        label.setText(USER_TURN);
    }

    public void challenge(View v){
        if(dictionary.isWord(currentWord)&&currentWord.length()>=4){
            ((TextView) findViewById(R.id.gameStatus)).setText(getString(R.string.userwin));
        }
        else{
            ((TextView) findViewById(R.id.gameStatus)).setText(getString(R.string.compwin));
            currentWord=dictionary.getAnyWordStartingWith(currentWord);
            ((TextView) findViewById(R.id.ghostText)).setText(currentWord);
            winner = true;
        }
        winner=true;
    }
    public void reset(View v){
        currentWord="";
        ((TextView) findViewById(R.id.gameStatus)).setText(USER_TURN);
        ((TextView) findViewById(R.id.ghostText)).setText("");
        winner=false;
    }
}
