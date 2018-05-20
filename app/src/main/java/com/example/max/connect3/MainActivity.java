package com.example.max.connect3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activePlayer = 0;
    boolean isActive = true;
    int [] gameState = {9, 9, 9, 9, 9, 9, 9, 9, 9};
    int [][] winningState = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void play(View view){

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 9 && isActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.red);
                TextView playerState = (TextView) findViewById(R.id.playerState);
                playerState.setText("Player 2's Turn");
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.yellow);
                TextView playerState = (TextView) findViewById(R.id.playerState);
                playerState.setText("Player 1's Turn");
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(3600).setDuration(300);

            for (int[] winingPos : winningState) {
                if (gameState[winingPos[0]] == gameState[winingPos[1]] && gameState[winingPos[1]] == gameState[winingPos[2]]
                        && gameState[winingPos[0]] != 9) {

                    //there is a winner
                    isActive = false;

                    String winner = "Red";

                    if (activePlayer == 0) {

                        winner = "Yellow";
                    }

                    TextView message = (TextView) findViewById(R.id.winnerText);
                    LinearLayout layout = (LinearLayout) findViewById(R.id.myLinear);

                    TextView playerState = (TextView) findViewById(R.id.playerState);
                    playerState.setVisibility(View.GONE);

                    message.setText(winner + " Has Won!!");
                    layout.setVisibility(View.VISIBLE);
                }else{

                    boolean gameOver = true;
                    for (int counterState : gameState){
                        if (counterState == 9) gameOver =false;
                    }

                    if (gameOver){
                        isActive = false;
                        TextView playerState = (TextView) findViewById(R.id.playerState);
                        playerState.setVisibility(View.GONE);

                        TextView message = (TextView) findViewById(R.id.winnerText);
                        LinearLayout layout = (LinearLayout) findViewById(R.id.myLinear);

                        message.setText("It's a draw");
                        layout.setVisibility(View.VISIBLE);
                    }

                }

            }
        }

    }

    public  void playAgain(View v){
        isActive = true;

        LinearLayout layout = (LinearLayout) findViewById(R.id.myLinear);
        layout.setVisibility(View.GONE);

        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i< grid.getChildCount();i++){
            ImageView img = (ImageView) grid.getChildAt(i);
            img.setImageResource(0);
        }

        for (int i = 0; i<gameState.length;i++){

            gameState[i] = 9;
        }

        TextView playerState = (TextView) findViewById(R.id.playerState);
        playerState.setText(R.string.player1Text);
        playerState.setVisibility(View.VISIBLE);
    }
}
