package com.androidclass.kodible;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;

public class Game1lvl2 extends AppCompatActivity {
    TextView scoreGame;
    MyStrtDrggngLstnr mStrtDrg;
    MyEndDrgLstnr mEndDrg;
    Button image1,image2,image3, image4;
    Button compostTrash, recycleTrash;
    Button play, back,reset;
    private MediaPlayer audio;
    private SoundMini sound;
    static  int mScore=0;
    int totalAnswers=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1lvl2);
        ButterKnife.bind(this);
        sound= new SoundMini((Game1lvl2.this));
        mStrtDrg=new MyStrtDrggngLstnr();
        mEndDrg=new MyEndDrgLstnr();
        play=(Button)findViewById(R.id.btn_play);
        reset=(Button)findViewById(R.id.btn_reset);
        back=(Button)findViewById(R.id.btn_back);
        scoreGame=(TextView)findViewById(R.id.scoreLabGame);
        scoreGame.setText("Score"+ mScore);

        findViewById(R.id.btn_bck).setOnLongClickListener(mStrtDrg);
        findViewById(R.id.btn_dwn).setOnLongClickListener(mStrtDrg);
        findViewById(R.id.btn_fwd).setOnLongClickListener(mStrtDrg);
        findViewById(R.id.btn_up).setOnLongClickListener(mStrtDrg);
        findViewById(R.id.btn_waterbottle).setOnLongClickListener(mStrtDrg);
        findViewById(R.id.btn_apple).setOnLongClickListener(mStrtDrg);

        findViewById(R.id.btn_input2).setOnDragListener(mEndDrg);
        audio=MediaPlayer.create(Game1lvl2.this,R.raw.netherplace);
        audio.start();

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play.setVisibility(View.GONE);
                mScore=0;
                scoreGame.setText("Score: "+mScore);

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScore=0;
                scoreGame.setText("Score: "+mScore);
                play.setVisibility(View.VISIBLE);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game1lvl2.this, Dashboard.class);
                startActivity(intent);
                mScore=0;
                scoreGame.setText("Score"+mScore);
            }
        });



    }

    private class MyStrtDrggngLstnr implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
            WithDragShadow shadow= new WithDragShadow(v);
            ClipData data= ClipData.newPlainText("","");;
            v.startDrag(data,shadow,v,0);
            return false;
        }

        private class WithDragShadow extends View.DragShadowBuilder{
            public WithDragShadow(View v){ super(v);  }

            @Override public void onDrawShadow(Canvas canvas) { super.onDrawShadow(canvas);}

            @Override
            public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
                super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);
            }
        }
    }
    private void resetscore() {
        mScore=0;
    }

    private class MyEndDrgLstnr implements View.OnDragListener{
        @Override
        public boolean onDrag(View v, DragEvent event) {
            if (event.getAction() == DragEvent.ACTION_DROP) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        //no action necessary
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        //no action necessary
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        //no action necessary
                        break;
                    case DragEvent.ACTION_DROP:
                        //handle the dragged view being dropped over a drop view - asta de deasupra
                        //handle the dragged view being dropped over a target view -asta de jos
                        View view = (View) event.getLocalState();
                        //stop displaying the view where it was before it was dragged
                        //view dragged item is being dropped on
                        String tagDropTarget = "recycle";
                        Button dropped = (Button) view;
                        String tagDroppedImage = (String) dropped.getTag();
                        if(!(totalAnswers<=0)){
                        if ((tagDropTarget != null) && (tagDropTarget.equals(tagDroppedImage))) {
                            //score +
                            mScore+=500;
                            scoreGame.setText("Score: " + mScore);
                            sound.happySoundRun();
                            totalAnswers-=1;
                            if(totalAnswers<=0){
                                Toast.makeText(Game1lvl2.this, "Your total Score is " + mScore, Toast.LENGTH_LONG).show();
                                Intent registerRegIntent = new Intent(Game1lvl2.this, LevelGame1.class);
                                startActivity(registerRegIntent);
                                resetscore();
                            }

                        } else  {
                            mScore-=100;
                            if(mScore!=0){ scoreGame.setText("Score: " + mScore);}
                            sound.wrongSoundRun();

                        }}


                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        //no action necessary
                        break;
                    default:
                        break;
                }

            }
            return true;
        }
    }

}