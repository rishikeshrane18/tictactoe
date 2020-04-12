package com.example.gameconnector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

            //0= yellow and //1= red
            int activePlayer = 0;
            boolean gameIsActive = true;

            LinearLayout popUp;
            EditText winningmsg;

            int [] gameState = {2,2,2,2,2,2,2,2,2};          // 2 mean unplayed
            int [][] winningPositions ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

            public void dropIn(View view){
                GridLayout gridLayout = findViewById(R.id.gridLayout);
                popUp = findViewById(R.id.popUp);
                ImageView counter = (ImageView)view;
                int tappedCounter = Integer.parseInt(counter.getTag().toString());
                if ( gameState[tappedCounter] ==2 && gameIsActive){
                    gameState[tappedCounter] = activePlayer;
                    counter.setTranslationY(-1000f);
                    if(activePlayer == 0){
                        counter.setImageResource(R.drawable.yellow);
                        activePlayer = 1;
                    }else {
                        counter.setImageResource(R.drawable.red);
                        activePlayer = 0;
                    }
                    counter.animate().translationYBy(1000f).setDuration(1000);

                    for (int[] winningPosition : winningPositions){
                        if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[2]] != 2){
                            gameIsActive = false;
                            String winner = "Red";
                            if (gameState[winningPosition[0]] == 0){
                                winner = "Yellow";
                            }

                            popUp.setVisibility(View.VISIBLE);
                            winningmsg = findViewById(R.id.wonMessage);
                            winningmsg.setText(winner+" has won!");

                        }else {
                            boolean gameIsOver = true;
                            for (int counterState : gameState){
                                if( counterState==2){
                                    gameIsOver = false;
                                    break;
                                }
                            }
                            if (gameIsOver){
                                popUp.setVisibility(View.VISIBLE);
                                winningmsg.setText("It's a Draw!");
                                gridLayout.setVisibility(View.INVISIBLE);
                            }
                        }
                    }
                }
            }

            public void PlayAgain (View view) {
                gameIsActive = true;
                activePlayer = 0;
                for(int i = 0; i < 9;i++){
                    gameState[i]=2;
                }
                GridLayout gridLayout = findViewById(R.id.gridLayout);
                for (int i=0;i<gridLayout.getChildCount();i++){
                    ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
                }
                popUp = findViewById(R.id.popUp);
                popUp.setVisibility(View.INVISIBLE);
                gridLayout.setVisibility(View.VISIBLE);
            }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
