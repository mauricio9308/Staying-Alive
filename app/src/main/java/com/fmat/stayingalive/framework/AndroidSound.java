package com.fmat.stayingalive.framework;

import android.media.SoundPool;

import com.fmat.stayingalive.interfaces.Sound;

/**
 * Created by mauriciolara on 9/22/14.
 *
 * Simple implementation of the sound interface
 */
public class AndroidSound implements Sound {

    private final int SOUND_ID;
    private SoundPool mSoundPool;

    public AndroidSound( SoundPool soundPool, int soundId ){
        mSoundPool = soundPool;
        SOUND_ID = soundId;
    }

    @Override
    public void play(float volume) {
        mSoundPool.play( SOUND_ID, volume, volume, 0 /* priority */, 0 /* loop */, 1 /* rate */);
    }

    @Override
    public void dispose() {
        mSoundPool.unload( SOUND_ID );
    }
}
