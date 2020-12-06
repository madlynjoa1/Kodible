package com.androidclass.kodible;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {
    Button game1, game2, game3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        game1= findViewById(R.id.dashGame1);
        game2= findViewById(R.id.dashGame2);
        game3= findViewById(R.id.dashGame3);

        game1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerRegIntent = new Intent(Dashboard.this, LevelGame1.class);
                startActivity(registerRegIntent);
            }
        });
        game2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerRegIntent = new Intent(Dashboard.this, LevelGame2.class);
                startActivity(registerRegIntent);
            }
        });
        game3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Game Locked", Toast.LENGTH_LONG).show();
//                Intent registerRegIntent = new Intent(Dashboard.this, LevelGame3.class);
//                startActivity(registerRegIntent);
            }
        });
    }
}