package com.example.gato1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int [][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6},
            {1, 4, 7}, {2, 5, 8},{6, 4, 2}, {0, 4, 8}};
    int activePlayer = 0;
    boolean gameActive = true;

    public void dropin(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
            if (gameState[tappedCounter] == 2 && gameActive) {
                gameState[tappedCounter] = activePlayer;
                counter.setTranslationY(-1500);
                if (activePlayer == 0) {
                    counter.setImageResource(R.drawable.kaori);
                    activePlayer = 1;
                } else {
                    counter.setImageResource(R.drawable.cat);
                    activePlayer = 0;
                }

                counter.animate().translationYBy(1500).rotationYBy(3600).setDuration(500);
                for (int[] winningPosition : winningPositions) {
                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                            && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                            && gameState[winningPosition[0]] != 2) {
                        // someone has won!!
                        gameActive = false;
                        String winner;
                        if (activePlayer == 1) {
                            winner = "Kaori";
                        } else {
                            winner = "Gatito";
                        }
                        Toast.makeText(this, "Ganaste " + winner, Toast.LENGTH_LONG).show();

                        Button playAgainButton = findViewById(R.id.playAgainButton);
                        TextView winnerTextView = findViewById(R.id.winnerTextView);
                        winnerTextView.setText("Ganaste "+ winner);
                        winnerTextView.setVisibility(view.VISIBLE);
                        playAgainButton.setVisibility(view.VISIBLE);

                    }

                }

            }

        }

        public void playAgain(View view){
            Button playAgainButton = findViewById(R.id.playAgainButton);
            TextView winnerTextView = findViewById(R.id.winnerTextView);
            winnerTextView.setVisibility(view.INVISIBLE);
            playAgainButton.setVisibility(view.INVISIBLE);

            GridLayout gridLayout = findViewById(R.id.Grid_layout);
            for(int i=0; i<gridLayout.getChildCount(); i++) {
                ImageView counter = (ImageView) gridLayout.getChildAt(i);
                // do stuff with child view
                counter.setImageDrawable(null);
            }

            for (int i=0; i< gameState.length; i++) {
                gameState[i] = 2;
            }
            activePlayer = 0;
            gameActive = true;

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
