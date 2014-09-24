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

    }

    @Override
    public void stop() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void setLooping(boolean looping) {

    }

    @Override
    public void setVolume(float volume) {

    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public boolean isStopped() {
        return false;
    }

    @Override
    public boolean isLooping() {
        return false;
    }

    @Override
    public void dispose() {
        if( mMediaPlayer.isPlaying() ){
            mMediaPlayer.stop();
        }else{
            mMediaPlayer.release();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }
}
