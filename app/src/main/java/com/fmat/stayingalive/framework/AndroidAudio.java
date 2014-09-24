package com.fmat.stayingalive.framework;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.fmat.stayingalive.activity.SoundPoolTestActivity;
import com.fmat.stayingalive.interfaces.Audio;
import com.fmat.stayingalive.interfaces.Music;
import com.fmat.stayingalive.interfaces.Sound;

import java.io.IOException;

/**
 * Created by Kevin on 9/24/2014.
 */
public class AndroidAudio implements Audio {

    AssetManager manager;
    SoundPool soundPool;

    public AndroidAudio(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.manager = activity.getAssets();
        this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    @Override
    public Music newMusic(String fileName) {
        try {
            AssetFileDescriptor assetFileDescriptor = manager.openFd(fileName);
            return new AndroidMusic(assetFileDescriptor);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load music '" + fileName + "'");
        }
    }

    @Override
    public Sound newSound(String fileName) {
        try {
            AssetFileDescriptor assetFileDescriptor = manager.openFd(fileName);
            int soundId = soundPool.load(assetFileDescriptor, 0);
            return new AndroidSound(soundPool, soundId);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load sound '" + fileName + "'");
        }
    }
}
