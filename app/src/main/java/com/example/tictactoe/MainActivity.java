package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 0}, {2, 4, 6}};
    boolean gameActive = true;
    public void dropIn(View view) {
        
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {
                activePlayer = 1;
                counter.setImageResource(R.drawable.unnamed);
            } else {
                activePlayer = 0;
                counter.setImageResource(R.drawable.unnamed1);
            }

            counter.setTranslationY(-1000);
            counter.animate().translationYBy(1000).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    gameActive = false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Green";
                    } else {
                        winner = "Red";
                    }

                    TextView winningMessage = (TextView) findViewById(R.id.winningtext);
                    Button playAgain = (Button) findViewById(R.id.playAgain);
                    if (activePlayer == 1) {
                        winningMessage.setTextColor(this.getResources().getColor(R.color.colorGreen));
                    } else {
                        winningMessage.setTextColor(this.getResources().getColor(R.color.colorRed));
                    }
                    winningMessage.setText(winner + " has won!");
                    winningMessage.setVisibility(view.VISIBLE);
                    playAgain.setVisibility(view.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view) {
        //Toast.makeText(this, "triggered", Toast.LENGTH_SHORT).show();
        TextView winningMessage = (TextView) findViewById(R.id.winningtext);
        Button playAgain = (Button) findViewById(R.id.playAgain);
        winningMessage.setVisibility(view.INVISIBLE);
        playAgain.setVisibility(view.INVISIBLE);
        androidx.gridlayout.widget.GridLayout myGridLayout = findViewById(R.id.myGridLayout);
        for(int i = 0; i < myGridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) myGridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        activePlayer = 0;
        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
