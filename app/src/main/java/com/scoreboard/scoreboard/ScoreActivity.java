package com.scoreboard.scoreboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreActivity extends AppCompatActivity {
    int scoreplayerA, scoreplayerB = 0;
    int lastone = 20;
    int game = 21;
    Button plusA, plusB, minA, minB, reset;
    TextView playerA, playerB;
    String get_playerA, get_playerB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        playerA = (TextView) findViewById(R.id.playerATextView);
        playerB = (TextView) findViewById(R.id.playerBTextView);

        Bundle b = getIntent().getExtras();

        get_playerA = b.getString("parsePlayerA");
        get_playerB = b.getString("parsePlayerB");

        playerA.setText(get_playerA);
        playerB.setText(get_playerB);

        plusA = (Button) findViewById(R.id.plusAButton);
        plusB = (Button) findViewById(R.id.plusBButton);
        minA = (Button) findViewById(R.id.minAButton);
        minB = (Button) findViewById(R.id.minBButton);
        reset = (Button) findViewById(R.id.resetButton);

        plusA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreplayerA = scoreplayerA + 1;
                display(scoreplayerA, scoreplayerB);
            }
        });

        plusB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreplayerB = scoreplayerB + 1;
                display(scoreplayerA, scoreplayerB);
            }
        });

        minA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scoreplayerA == 0){
                    scoreplayerA = 0;
                } else{
                    scoreplayerA = scoreplayerA - 1;
                }
                display(scoreplayerA, scoreplayerB);
            }
        });

        minB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scoreplayerB == 0){
                    scoreplayerB = 0;
                } else{
                    scoreplayerB = scoreplayerB - 1;
                }
                display(scoreplayerA, scoreplayerB);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreplayerA = 0;
                scoreplayerB = 0;
                display(0, 0);
            }
        });
    }
    private void display(int scoreA, int scoreB){
        if (scoreA ==  30){
            alertwin(get_playerA);
        } else if (scoreB ==  30) {
            alertwin(get_playerB);
        } else if (scoreA == lastone && scoreB < lastone ){
            Toast.makeText(this, get_playerA + " Last One", Toast.LENGTH_SHORT).show();
        } else if (scoreB == lastone && scoreA < lastone ){
            Toast.makeText(this, get_playerB + " Last One", Toast.LENGTH_SHORT).show();
        } else if (scoreA == game && scoreB < lastone ){
            alertwin(get_playerA);
        } else if (scoreB == game && scoreA < lastone ){
            alertwin(get_playerB);
        } else if (scoreA == lastone && scoreB == lastone ){
            lastone = lastone + 1;
            game = game + 1;
            Toast.makeText(this, "Jusssss", Toast.LENGTH_SHORT).show();
        }

        TextView viewScoreA = (TextView) findViewById(R.id.scoreATextView);
        viewScoreA.setText("" + scoreA);
        TextView viewScoreB = (TextView) findViewById(R.id.scoreBTextView);
        viewScoreB.setText("" + scoreB);
    }

    public void alertwin(String player){
        final AlertDialog.Builder builder = new AlertDialog.Builder(ScoreActivity.this);
        builder.setMessage("Selamat " + player + " memenangkan pertandingan!");
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
