package com.androidclass.kodible;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelGame1 extends AppCompatActivity {
    Button level1,level2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        level1=findViewById(R.id.game1lvl1);
        level2=findViewById(R.id.game1lvl2);

        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerRegIntent = new Intent(LevelGame1.this, Game1lvl1.class);
                startActivity(registerRegIntent);
            }
        });
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerRegIntent = new Intent(LevelGame1.this, Game1lvl2.class);
                startActivity(registerRegIntent);
            }
        });
    }
}