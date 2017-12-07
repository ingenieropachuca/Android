package com.netcode.kickackoe;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int activePlayer=0;
    int [] gameState={2,2,2,2,2,2,2,2,2};
    int [][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void poner(View view) {
        ImageView cat5 = (ImageView) view;
        int contadorTag = Integer.parseInt(cat5.getTag().toString());

        if (gameState[contadorTag] == 2) {
            gameState[contadorTag]=activePlayer;
            cat5.setTranslationY(-1000f);
            if (activePlayer == 0) {
                cat5.setImageResource(R.drawable.orange);
                activePlayer = 1;
            } else {
                cat5.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }
            cat5.animate().translationYBy(1000f).setDuration(300);

            for (int[] winningPosition:winningPositions) {
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]]&&
                   gameState[winningPosition[1]]==gameState[winningPosition[2]]&&
                   gameState[winningPosition[0]]!=2) {

                    String winner="Yellow Cat";

                    if(gameState[winningPosition[0]]==0)
                    {
                        winner="Orange Cat";
                    }
                    TextView winnerMessage =(TextView) findViewById(R.id.winText);

                    winnerMessage.setText(winner+" has Won!!");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }
                else{
                    boolean gameOver=true;
                    for(int counterState:gameState){
                        if(counterState==2) gameOver=false;
                    }
                    if (gameOver){
                        TextView winnerMessage =(TextView) findViewById(R.id.winText);

                        winnerMessage.setText("Draw");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }

            }
        }
    }

    // BOTON PARA JUGAR DE NUEVO
    public void playAgain(View view){
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        int activePlayer=0;

        for (int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        GridLayout gridlayout= (GridLayout) findViewById(R.id.lay);
        for(int i=0; i<gridlayout.getChildCount();i++)
        {
            ((ImageView) gridlayout.getChildAt(i)).setImageResource(0);
        }

    }

}
