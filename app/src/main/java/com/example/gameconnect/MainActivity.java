package com.example.gameconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int color = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;
    public void dropIn(View view){
        ImageView counter = (ImageView)view;
        if(gameState[Integer.parseInt(counter.getTag().toString())] == 2 && gameActive) {
            gameState[Integer.parseInt(counter.getTag().toString())] = color;

            counter.setTranslationY(-1500f);
            if (color == 0) {
                counter.setImageResource(R.drawable.red);
                color += 1;
            } else if (color == 1) {
                color -= 1;
                counter.setImageResource(R.drawable.yellow);
            }
            counter.animate().translationYBy(1500f).setDuration(300);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[1]] != 2) {
                    //someone has won
                    gameActive = false;
                    String player = "";
                    if (color == 1) {
                        player = "Red";
                    } else {
                        player = "Yellow";
                    }
                    TextView winner = (TextView)findViewById(R.id.winnerText);
                    Button playAgain = (Button)findViewById(R.id.playAgainButton);


                    winner.setVisibility(View.VISIBLE);
                    winner.setText(player + " has won!");

                    playAgain.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){

        TextView winner = (TextView)findViewById(R.id.winnerText);
        Button playAgain = (Button)findViewById(R.id.playAgainButton);
        winner.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i++){

            ImageView counter2 = (ImageView)gridLayout.getChildAt(i);
            counter2.setImageDrawable(null);
        }

        color = 0;


        for(int i = 0; i < gameState.length; i++){
        gameState[i] = 2;}


        gameActive = true;

    }
}
