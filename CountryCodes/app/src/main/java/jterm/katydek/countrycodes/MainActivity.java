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

package jterm.katydek.countrycodes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private HashMap<String, String> mCountryCodes = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readCountryCodesFile();
    }

    /**
     * Called when the user clicks the submit button. Set in XML.
     * @param view
     */
    public void submitQuery(View view) {
        EditText queryEditText = (EditText) findViewById(R.id.query);
        String query = queryEditText.getText().toString();
        String result = findCountryCode(query);
        TextView resultTextView = (TextView) findViewById(R.id.result);
        resultTextView.setText(result);
    }

    private String findCountryCode(String query) {

        query = query.toUpperCase();
        String result = mCountryCodes.get(query);
        if(result!=null) {
            return result;
        }
        return query+" is not a valid country code.";
    }

    private void readCountryCodesFile() {
        try {
            InputStream inputStream = getApplicationContext().getResources().openRawResource(
                    R.raw.countrycodes);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            // The first line is the header.
            reader.readLine();
            String line = reader.readLine();
            while (line != null) {
               // Log.d("Reading",line);
                Log.d("parsing",line);
                String[] parts = line.split(",");

                //
                // Now we need to populate the mCountryCodes HashMap so we can use it for lookup!
                // Your code here.
                //
                String key = parts[2];
                String value = parts[1];

                mCountryCodes.put(key,value);
                line = reader.readLine();
            }
            inputStream.close();
            reader.close();
        } catch (Exception e) {
            Log.d(TAG, "Reading country codes");
        }
        Log.d(TAG, "Success!");
    }

}
