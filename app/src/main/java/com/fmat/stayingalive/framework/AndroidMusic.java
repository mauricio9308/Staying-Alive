package com.fmat.stayingalive.framework;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import com.fmat.stayingalive.interfaces.Music;

import java.io.IOException;

/**
 * Created by mauriciolara on 9/22/14.
 */
public class AndroidMusic implements Music, MediaPlayer.OnCompletionListener{

    private MediaPlayer mMediaPlayer;
    private boolean mIsPrepared = false;

    public AndroidMusic( AssetFileDescriptor fileDescriptor ){
        mMediaPlayer = new MediaPlayer();
        try{
            mMediaPlayer.setDataSource( fileDescriptor.getFileDescriptor(),
                    fileDescriptor.getStartOffset(),
                    fileDescriptor.getLength() );
            mMediaPlayer.prepare();
            mIsPrepared = true;
            mMediaPlayer.setOnCompletionListener( AndroidMusic.this /* MediaPlayer.OnCompletionListener */);
        }catch( IOException e ){
            throw new RuntimeException("Error while loading the music file in "
                    + AndroidMusic.class.getSimpleName() );
        }
    }

    @Override
    public void play() {
        if (mMediaPlayer.isPlaying()) {
            return;
        }
        try {
            synchronized (this) {
                if (!mIsPrepared) {
                    mMediaPlayer.prepare();
                }
                mMediaPlayer.start();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        mMediaPlayer.stop();
        synchronized (this) {
            mIsPrepared = false;
        }
    }

    @Override
    public void pause() {
        //this apparently wasn't in the book
    }

    @Override
    public void setLooping(boolean looping) {
        mMediaPlayer.setLooping(looping);
    }

    @Override
    public void setVolume(float volume) {
        mMediaPlayer.setVolume(volume, volume);
    }

    @Override
    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    @Override
    public boolean isStopped() {
        return !mIsPrepared;
    }

    @Override
    public boolean isLooping() {
        return mMediaPlayer.isLooping();
    }

    @Override
    public void dispose() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
        mMediaPlayer.release();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        synchronized (this) {
            isPrepared = false;
        }
    }

}
