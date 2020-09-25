package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0-circle and 1-cross
    int activePlayer= 0;
    boolean gameIsActive = true;
    int[] positions = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;


        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(positions[tappedCounter]==2 && gameIsActive)
        {
            counter.setTranslationY(-1000f);

            //set position with active player number
            positions[tappedCounter]=activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.circle);

                //change turn for another player
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.cross);
                //change turn for another player
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(500);

            //check result
            for( int[] winningPosition : winningPositions)
            {
                //Log.i("in loop","in loop");
                if(positions[winningPosition[0]] == positions[winningPosition[1]] &&
                        positions[winningPosition[1]] == positions[winningPosition[2]] &&
                            positions[winningPosition[0]]!=2)
                {
                    gameIsActive = false;
                    //Log.i("in if","in if");
                    TextView textView = (TextView) findViewById(R.id.winnerTextView);
                    if(positions[winningPosition[0]]==0)
                    {
                        textView.setText("Player 1 has won");
                    }
                    if(positions[winningPosition[0]]==1)
                    {
                        textView.setText("Player 2 has won");
                    }

                    textView.setVisibility(View.VISIBLE);

                    Button playagainbutton = (Button) findViewById(R.id.playAgainButton);
                    playagainbutton.setVisibility(View.VISIBLE);
                }
                else
                {
                    int flag = 0;
                    for(int n: positions)
                    {
                        if(n==2)
                        {
                            flag=1;
                            break;
                        }
                    }
                    if(flag==0)
                    {
                        // gameIsActive=false;
                        TextView textView = (TextView) findViewById(R.id.winnerTextView);
                        textView.setText("Draw Match");
                        textView.setVisibility(View.VISIBLE);

                        Button playagainbutton = (Button) findViewById(R.id.playAgainButton);
                        playagainbutton.setVisibility(View.VISIBLE);
                    }
                }
            }
        }

    }

    public void playAgain(View view) {

        //Log.i("In play","in play");

        gameIsActive= true;

        TextView textView = (TextView) findViewById(R.id.winnerTextView);
        textView.setVisibility(View.INVISIBLE);

        Button playagainbutton = (Button) findViewById(R.id.playAgainButton);
        playagainbutton.setVisibility(View.INVISIBLE);

        activePlayer=0;

        for(int i=0;i<9;i++)
            positions[i]=2;

        android.support.v7.widget.GridLayout gridlayout = (android.support.v7.widget.GridLayout) findViewById(R.id.board);

        for(int i=0;i<gridlayout.getChildCount();i++)
            ((ImageView) gridlayout.getChildAt(i)).setImageResource(0);
    }
}
