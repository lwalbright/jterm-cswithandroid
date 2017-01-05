package com.lwalbright.scarne;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

    public void rollDice(View v){
        Random rand = new Random();
        //int result = rand.nextInt(6)+1;
        int result = 1;
        if(turn&&result!=1){
            userTurnScore+=result;
        }
        if(!turn&&result!=1){
            compTurnScore+=result;
        }
        if(turn&&result==1){
            turn = false;
            userTurnScore=0;
        }
        if(!turn&&result==1){
            turn = true;
            compTurnScore=0;
        }

        TextView tview = (TextView) findViewById(R.id.one);
        tview.setText("Your score: "+userOverallScore+" Computer score: "+compOverallScore
                +" \nYour turn score "+userTurnScore+" Computer turn score "+compTurnScore+turn+result);

        ImageView view = (ImageView) findViewById(R.id.two);
        if(result==1){
            view.setImageDrawable(getResources().getDrawable(R.drawable.dice1)); }
        if(result==2){
            view.setImageDrawable(getResources().getDrawable(R.drawable.dice2)); }
        if(result==3){
            view.setImageDrawable(getResources().getDrawable(R.drawable.dice3)); }
        if(result==4){
            view.setImageDrawable(getResources().getDrawable(R.drawable.dice4)); }
        if(result==5){
            view.setImageDrawable(getResources().getDrawable(R.drawable.dice5)); }
        if(result==6){
            view.setImageDrawable(getResources().getDrawable(R.drawable.dice6)); }




    }
}
