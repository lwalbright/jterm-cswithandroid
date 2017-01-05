package com.lwalbright.scarne;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    protected int userOverallScore =0;
    protected int compOverallScore =0;
    protected int userTurnScore=0;
    protected int compTurnScore=0;
    protected boolean turn=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void roll(){
        Random rand = new Random();
        int result = rand.nextInt(6)+1;
        if(turn==true&&result!=1){
            userTurnScore+=result;
        }
        if(turn==false)&&result!=1){
            compTurnScore+=1;
        }
        ImageView view = (ImageView) findViewById(R.id.two);


    }
}
