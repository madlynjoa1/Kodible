package com.androidclass.kodible;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;

public class Game2lvl2 extends AppCompatActivity {
    ImageView box;

    int boxX;
    int boxY;
    MyStrtDrggngLstnr mStrtDrg;
    MyEndDrgLstnr mEndDrg;

    Button start,play,back,reset,drag;
    TextView scoreGame;

    private MediaPlayer audio;
    private SoundMini sound;
    static  int mScore=0;
    int totalAnswers=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2lvl2);
        sound= new SoundMini((Game2lvl2.this));
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
        findViewById(R.id.btn_drop2).setOnDragListener(mEndDrg);
        findViewById(R.id.btn_drop3).setOnDragListener(mEndDrg);
        //music
        audio=MediaPlayer.create(Game2lvl2.this,R.raw.netherplace);
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
                boxX=5;
                boxY=500;
                box.setX(boxX);
                box.setY(boxY);
                start.setVisibility(View.VISIBLE);


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game2lvl2.this, Dashboard.class);
                startActivity(intent);
            }
        });

    }
//    public boolean reset(){
//        boxY-=500;
//        box.setX(boxX);
//        box.setY(boxY);
//        return true;
//    }

    public void animate(){
        ObjectAnimator gummieRight = ObjectAnimator.ofFloat(box, "TranslationX", 360);
        gummieRight.setDuration(650);
        ObjectAnimator jumpUp = ObjectAnimator.ofFloat(box, "translationY", -450);
        jumpUp.setDuration(1000);
        ObjectAnimator gummieRi = ObjectAnimator.ofFloat(box, "TranslationX", 1900);
        gummieRi.setDuration(2200);


        AnimatorSet animate=new AnimatorSet();
        animate.play(gummieRight);
        animate.play(jumpUp).after(gummieRight);
        animate.play(gummieRi).after(jumpUp);
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
                        Button dropTarget=(Button) v;
                        Button dropped = (Button) view;

                        String tagDropTarget = (String)dropTarget.getTag();
                        String tagDroppedImage=(String)dropped.getTag();

                        if(!(totalAnswers<=0)) {
                                if ((tagDropTarget != null) && (tagDropTarget.equals(tagDroppedImage))) {
                                    //score +
                                    mScore += 500;
                                    scoreGame.setText("Score: " + mScore);
                                    sound.happySoundRun();
                                    totalAnswers -= 1;
                                    if(totalAnswers<=0){
                                        Toast.makeText(Game2lvl2.this, "Your total Score is " + mScore, Toast.LENGTH_LONG).show();
                                        Intent registerRegIntent = new Intent(Game2lvl2.this, Dashboard.class);
                                        startActivity(registerRegIntent);
                                        resetscore();
                                    }

                                } else {
                                    mScore -= 100;
                                    if (mScore != 0) {
                                        scoreGame.setText("Score: " + mScore);
                                    }
                                    sound.wrongSoundRun();

                                }

                        }
//                        if ((tagDropTarget != null) && (tagDropTarget.equals(tagDroppedImage))) {
//                            //score +
//                            mScore=mScore+500;
//                            scoreGame.setText("Score: " + mScore);
//                            sound.happySoundRun();
//                            Intent intent = new Intent(Game2lvl2.this,ScoreResult.class);
//                            startActivity(intent);
//
//                            resetscore();
//                        } else  {
//                            mScore=mScore-50;
//                            sound.wrongSoundRun();
//


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

    private void resetscore() {
        mScore=0;
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
//private class MyStrtDrggngLstnr implements View.OnLongClickListener{
//    @Override
//    public boolean onLongClick(View v) {
//        WithDragShadow shadow= new WithDragShadow(v);
//        ClipData data= ClipData.newPlainText("","");
//        v.startDrag(data,shadow,v,0);
//        sound.happySoundRun();
//        return false;
//    }
//}
//
//private class MyEndDrgLstnr implements View.OnDragListener{
//
//    @Override
//    public boolean onDrag(View v, DragEvent event) {
//        if (event.getAction() == DragEvent.ACTION_DROP) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
////this will ckeck if the drag and drop was correct
//                v.setBackground(((Button)event.getLocalState()).getBackground());
//            }
//        }
//        return true;
//    }
//
//}
//    public void score(){
//        if(key_flag==true){
//            score+=10;
//        }
//        else{
//            score=0;
//        }
//
//    }
//
//private class WithDragShadow extends View.DragShadowBuilder {
//    public WithDragShadow(View v){ super(v);}
//
//    @Override public void onDrawShadow(Canvas canvas){
//        super.onDrawShadow(canvas);
//    }
//    @Override
//    public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint){
//        super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);
//    }
//}
//////////////////////////////////////////////

//    public void changePos(){
//
//        if(action_flag==true){
//            //touching
//            boxX+=20;
//        }
//        else{
//            //releasing
//            boxX-=20;
//        }
//        //check the box position
//        if(boxX<0){
//            boxX=0;
//        }
//        if(boxX >frameWidth - boxSize){
//            boxX=frameWidth-boxSize;
//        }
//        box.setX(boxX);
//    }
//
//    public boolean onTouchEvent(MotionEvent me){
//        if(start_flg==false){
//            start_flg=true;
//            //get frame size from xml
//            //set it here because teh UI has not set a screen in the On Create)(
//            FrameLayout frame=(FrameLayout) findViewById(R.id.frame);
//            frameWidth=frame.getWidth();
//            //The box is a square height and width are the smae
//            boxX=(int)box.getX();
//            boxSize=box.getWidth();
//
//            start.setVisibility(View.GONE);
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            changePos();
//                        }
//                    });
//                }
//            },0,20);
//
//        }else{
//            if(me.getAction()==MotionEvent.ACTION_DOWN)
//            {
//                action_flag=true;
//
//            }
//            else if(me.getAction()==MotionEvent.ACTION_UP){
//                action_flag=false;
//            }
//        }
//        if(me.getAction()==MotionEvent.ACTION_DOWN){
//            action_flag=true;
//        }
//        else if(me.getAction()==MotionEvent.ACTION_UP){
//            action_flag=false;
//        }
//
//        return true;
//    }
