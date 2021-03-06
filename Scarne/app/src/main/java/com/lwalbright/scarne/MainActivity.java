package com.lwalbright.scarne;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    protected int userOverallScore =0;
    protected int compOverallScore =0;
    protected int userTurnScore=0;
    protected int compTurnScore=0;
    protected boolean turn=true;
    protected int result = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void computerTurn(){
        Button holdButton = (Button) findViewById(R.id.four);
        Button rollButton = (Button) findViewById(R.id.three);
        holdButton.setClickable(false);
        rollButton.setClickable(false);
        while(compTurnScore<20&&turn==false){
            rollHelper();
            viewUpdate(null);
        }
        compOverallScore+=compTurnScore;
        compTurnScore=0;
        turn=true;
        viewUpdate(null);
        holdButton.setClickable(true);
        rollButton.setClickable(true);
    }

    private void rollHelper(){
        Random rand = new Random();
        result = rand.nextInt(6)+1;
        if(turn&&result!=1){
            userTurnScore+=result;
        }
        if(!turn&&result!=1){
            compTurnScore+=result;
        }
        if(result==1){
            if(turn){
                userTurnScore=0;
                turn=false;
            }
            else{
                compTurnScore=0;
                turn=true;
            }
        }
    }

    private void viewUpdate(View v){
        TextView tview = (TextView) findViewById(R.id.one);
        if(turn) {
            tview.setText("Your score: " + userOverallScore + " Computer score: " + compOverallScore
                    + " \nYour turn score " + userTurnScore + " Computer turn score " + compTurnScore + "\nYour turn!");
        }
        if(!turn) {
            tview.setText("Your score: " + userOverallScore + " Computer score: " + compOverallScore
                    + " \nYour turn score " + userTurnScore + " Computer turn score " + compTurnScore + "\nComputer's turn!");
        }
        ImageView view = (ImageView) findViewById(R.id.two);
        if(result==1){view.setImageDrawable(getResources().getDrawable(R.drawable.dice1));
            view.setContentDescription("Dice 1");
        }
        if(result==2){view.setImageDrawable(getResources().getDrawable(R.drawable.dice2));
            view.setContentDescription("Dice 2");
        }
        if(result==3){view.setImageDrawable(getResources().getDrawable(R.drawable.dice3));
            view.setContentDescription("Dice 3");
        }
        if(result==4){view.setImageDrawable(getResources().getDrawable(R.drawable.dice4));
            view.setContentDescription("Dice 4");
        }
        if(result==5){view.setImageDrawable(getResources().getDrawable(R.drawable.dice5));
            view.setContentDescription("Dice 5");
        }
        if(result==6){view.setImageDrawable(getResources().getDrawable(R.drawable.dice6));
            view.setContentDescription("Dice 6");
        }
    }

    public void rollDice(View v){
        rollHelper();
        if(result==1&&!turn){
            computerTurn();
        }
        viewUpdate(null);
    }

    public void reset(View v){
        userOverallScore=0;
        userTurnScore=0;
        compTurnScore=0;
        compOverallScore=0;
        turn=true;
        TextView tview = (TextView) findViewById(R.id.one);
        viewUpdate(null);
    }

    public void hold(View V){
        userOverallScore+=userTurnScore;
        compOverallScore+=compTurnScore;
        userTurnScore=0;
        compTurnScore=0;
        turn=!turn;
        if(!turn) {
            computerTurn();
        }
        viewUpdate(null);
    }
}
