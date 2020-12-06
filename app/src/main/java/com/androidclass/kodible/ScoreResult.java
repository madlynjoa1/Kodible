package com.androidclass.kodible;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import static com.androidclass.kodible.Game2lvl1.mScore;

public class ScoreResult extends AppCompatActivity {
    TextView scoreLabel;
    ImageView close,home;
    Button reset;

    Game2lvl1 mGame = new Game2lvl1();
    int score= mScore;
    String resultDisply= String.valueOf(score);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_result);
        scoreLabel=(TextView)findViewById(R.id.scorelab);
        close=(ImageView)findViewById(R.id.closeBtn);
        home=(ImageView)findViewById(R.id.homeB);
        reset=(Button)findViewById(R.id.resetResult);
        scoreLabel.setText(resultDisply);
//        try {
//            writeToInternalFile("");
//            String fileContents = readFromInternalFile();
//            scoreLabel.setText(fileContents);
//        }
//        catch (IOException ex) {
//            ex.printStackTrace();
//        } public void readData(View view) throws IOException {
//            .setText(readFromInternalFile());
//        }
//
//        public void saveData(View view) {
//
//            try {
//                writeToInternalFile( scoreLabel.getText().toString() );
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreResult.this, Game2lvl1.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreResult.this, Dashboard.class);
                startActivity(intent);
            }
        });


    }

//    private void writeToInternalFile(String s) throws FileNotFoundException {
//        StringBuilder  result= new StringBuilder();
//        FileOutputStream outputStream = openFileOutput("FileNameHere", Context.MODE_PRIVATE);
//        PrintWriter writer = new PrintWriter(outputStream);
//        result.append("Your result").append(resultDisply).toString();
//        writer.println("Your result"+ resultDisply);
//        writer.close();
//    }
//    private String readFromInternalFile() throws IOException {
//        FileInputStream inputStream = openFileInput("FileNameHere");
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        try {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                stringBuilder.append(line).append('\n');
//            }
//        }
//        finally {
//            reader.close();
//        }
//
//        return stringBuilder.toString();
//    }

}