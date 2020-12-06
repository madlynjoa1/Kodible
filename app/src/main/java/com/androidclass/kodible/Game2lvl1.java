package com.androidclass.kodible;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

import butterknife.ButterKnife;

public class Game2lvl1 extends AppCompatActivity {

    ImageView box;

    //position box
    int boxX;
    //size
    int count=0,numberInputs=0,frameWidth, boxSize,screenWidth,screenHeight;
    MyStrtDrggngLstnr mStrtDrg;
    MyEndDrgLstnr mEndDrg;

    Button start;
    TextView scoreGame;
    Button play;
    Button back;
    Button reset;
    Button drag;

    private MediaPlayer audio;
    private SoundMini sound;
    static  int mScore=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2lvl1);
        sound= new SoundMini((Game2lvl1.this));
        ButterKnife.bind(this);
        mStrtDrg=new MyStrtDrggngLstnr();
        mEndDrg=new MyEndDrgLstnr();


        start= (Button) findViewById(R.id.start);
        box=(ImageView) findViewById(R.id.box);
        drag=(Button)findViewById(R.id.btn_drop);
        scoreGame=(TextView) findViewById(R.id.scorelabel);
        back=(Button)findViewById(R.id.home);

        scoreGame.setText("Score: "+mScore);

        findViewById(R.id.btn_bck).setOnLongClickListener(mStrtDrg);
        findViewById(R.id.btn_dwn).setOnLongClickListener(mStrtDrg);
        findViewById(R.id.btn_fwd).setOnLongClickListener(mStrtDrg);
        findViewById(R.id.btn_up).setOnLongClickListener(mStrtDrg);
        reset=findViewById(R.id.btn_reset);

        findViewById(R.id.btn_drop).setOnDragListener(mEndDrg);
        //music
        audio=MediaPlayer.create(Game2lvl1.this,R.raw.netherplace);
        audio.start();


       start.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               start.setVisibility(View.GONE);
               animate();

           }
       });
       reset.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               mScore=0;
               scoreGame.setText("Score: "+mScore);
               boxX=10;
               box.setX(boxX);
               start.setVisibility(View.VISIBLE);
              // boxX=(int)box.getX();
//               boxSize=box.getWidth();
//               boxX=10;
//               box.setX(boxX);
           }
       });
       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Game2lvl1.this, Dashboard.class);
               startActivity(intent);
           }
       });


    }
    public boolean reset(){
        boxX-=500;
        box.setX(boxX);
        return true;
    }

    public void animate(){
        ObjectAnimator gummieRight = ObjectAnimator.ofFloat(box, "TranslationX", 1500);
        gummieRight.setDuration(3000);
        //zyb
        AnimatorSet animate = new AnimatorSet();
        animate.play(gummieRight);
        animate.start();
    }
    private class MyStrtDrggngLstnr implements View.OnLongClickListener{
        @Override
        public boolean onLongClick(View v) {
            WithDragShadow shadow= new WithDragShadow(v);
            ClipData data= ClipData.newPlainText("","");;
            v.startDrag(data,shadow,v,0);

            return false;
        }

        private class WithDragShadow extends View.DragShadowBuilder {
            public WithDragShadow(View v){ super(v);  }

            @Override public void onDrawShadow(Canvas canvas) { super.onDrawShadow(canvas);}

            @Override
            public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
                super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);
            }
        }
    }


    private class MyEndDrgLstnr implements View.OnDragListener {

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
                        String tagDropTarget = "RIGHT";
                        Button dropped = (Button) view;
                        String tagDroppedImage = (String) dropped.getTag();
                        if ((tagDropTarget != null) && (tagDropTarget.equals(tagDroppedImage))) {
                            //score +
                            mScore=mScore+500;
                            scoreGame.setText("Score: " + mScore);
                            sound.happySoundRun();

                            Toast.makeText(Game2lvl1.this, "Your total Score is " + mScore, Toast.LENGTH_LONG).show();
                            Intent registerRegIntent = new Intent(Game2lvl1.this, Dashboard.class);
                            startActivity(registerRegIntent);

                            resetscore();
                        } else  {
                            mScore=mScore-50;
                            sound.wrongSoundRun();

                        }

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
    public void  resetscore(){
        mScore=0;
    }

}
