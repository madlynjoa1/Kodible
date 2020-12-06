package com.androidclass.kodible;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelGame2 extends AppCompatActivity {
    Button level1,level2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_game2);
        level1=findViewById(R.id.game2lvl1);
        level2=findViewById(R.id.game2lvl2);

        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerRegIntent = new Intent(LevelGame2.this, Game2lvl1.class);
                startActivity(registerRegIntent);
            }
        });
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerRegIntent = new Intent(LevelGame2.this, Game2lvl2.class);
                startActivity(registerRegIntent);
            }
        });
    }
}