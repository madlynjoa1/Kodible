package com.androidclass.kodible;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundMini {
    private SoundPool miniSound;
    private SoundPool wrSound;
    private int happySound;
    private int wrongSound;

    public SoundMini(Context context){
        miniSound= new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        happySound=miniSound.load(context, R.raw.claps,1);

        wrSound=new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        wrongSound=wrSound.load(context,R.raw.wrong,1);
    }
    public void happySoundRun(){
        miniSound.play(happySound,1.0f,1.0f,1,0,1.0f);

    }

    public void wrongSoundRun(){
        wrSound.play(wrongSound,1.0f,1.0f,1,3,1.0f);
    }

}
