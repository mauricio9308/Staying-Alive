package com.fmat.stayingalive.framework;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.fmat.stayingalive.interfaces.Audio;
import com.fmat.stayingalive.interfaces.Music;
import com.fmat.stayingalive.interfaces.Sound;

import java.io.IOException;

/**
 * Created by mauriciolara on 9/22/14.
 */
public class AndroidAudio implements Audio{

    private Activity mActivity;
    private AssetManager mAssetManager;
    private SoundPool mSoundPool;

    private static final int MAX_AUDIO_STREAMS = 20;

    public AndroidAudio( Activity activity ){
        mActivity = activity;
        mActivity.setVolumeControlStream(AudioManager.STREAM_MUSIC );
        mAssetManager = activity.getAssets();
        mSoundPool = new SoundPool( MAX_AUDIO_STREAMS,
                AudioManager.STREAM_MUSIC, 0 /* srcQuality */);
    }

    @Override
    public Music newMusic(String fileName) {
        try{
            AssetFileDescriptor assetFileDescriptor = mAssetManager.openFd( fileName );
            return new AndroidMusic( assetFileDescriptor );
        }catch( IOException e ){
            throw new RuntimeException("Invalid file path given " + fileName );
        }
    }

    @Override
    public Sound newSound(String fileName) {
        try{
            AssetFileDescriptor assetFileDescriptor = mAssetManager.openFd( fileName );
            int soundId = mSoundPool.load( assetFileDescriptor, 1
                /* priority not used, using 1 for future compatibility */);
            return new AndroidSound( mSoundPool, soundId );
        }catch( IOException e ){
            throw new RuntimeException("Unable to load sound " + fileName );
        }
    }
}
