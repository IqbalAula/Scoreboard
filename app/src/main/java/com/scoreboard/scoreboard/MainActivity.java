package com.scoreboard.scoreboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText playerA, playerB;
    String set_playerA, set_playerB;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerA = (EditText) findViewById(R.id.playerAEditText);
        playerB = (EditText) findViewById(R.id.playerBEditText);
        next = (Button) findViewById(R.id.nextButton);

        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.nextButton){
            if (playerA.length() == 0 || playerB.length() == 0){
                Toast.makeText(this,"Masukkan nama player", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Selamat Bertanding", Toast.LENGTH_SHORT).show();

                set_playerA = playerA.getText().toString();
                set_playerB = playerB.getText().toString();

                Intent intent = new Intent(getApplicationContext(), ScoreActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("parsePlayerA", set_playerA);
                bundle.putString("parsePlayerB", set_playerB);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Apakah anda ingin keluar?");
        builder.setCancelable(true);
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
