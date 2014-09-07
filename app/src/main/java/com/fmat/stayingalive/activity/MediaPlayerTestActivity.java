package com.fmat.stayingalive.activity;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class MediaPlayerTestActivity extends ActionBarActivity implements View.OnClickListener,
        MediaPlayer.OnSeekCompleteListener {

    private static final String SAVE_IS_PLAYING = "save_is_playing";
    private static final String SAVE_MUSIC_POSITION = "save_music_position";

    private MediaPlayer mMediaPlayer;
    private boolean mIsPlaying = false;
    private int mMusicPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player_test);

        if (setUpMediaPlayer()) {
            findViewById(R.id.bttnPauseMusic).setOnClickListener(this);
            findViewById(R.id.bttnPlayMusic).setOnClickListener(this);

            if (savedInstanceState != null) {
                mIsPlaying = savedInstanceState.getBoolean(SAVE_IS_PLAYING);
                mMusicPosition = savedInstanceState.getInt(SAVE_MUSIC_POSITION);
            }
        } else {
            Toast.makeText(MediaPlayerTestActivity.this, "Error setting up media player", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        resumeMusic();
    }

    @Override
    public void onPause() {
        super.onPause();
        pauseMusic();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVE_IS_PLAYING, mIsPlaying);
        outState.putInt(SAVE_MUSIC_POSITION, mMusicPosition);
    }

    @Override
    public void onStop() {
        super.onStop();
        mMediaPlayer.release();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bttnPlayMusic:
                if (mMediaPlayer != null && !mIsPlaying) {
                    mMediaPlayer.start();
                } else {
                    resumeMusic();
                }
                break;
            case R.id.bttnPauseMusic:
                if (mMediaPlayer.isPlaying()) {
                    pauseMusic();
                }
                break;
            default:
                throw new IllegalStateException("Button not registered ID: " + view.getId());
        }
    }

    private boolean setUpMediaPlayer() {
        try {
            mMediaPlayer = new MediaPlayer();
            AssetFileDescriptor descriptor = getAssets().openFd("YOLO.mp3");
            mMediaPlayer.setLooping(true);
            mMediaPlayer.setVolume(1 /* max volume */, 1 /* max volume */);
            mMediaPlayer.setOnSeekCompleteListener( this );
            mMediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            mMediaPlayer.prepare();
            return true;
        } catch (IOException e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
            return false;
        }
    }

    private void pauseMusic() {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mIsPlaying = true;
            mMusicPosition = mMediaPlayer.getCurrentPosition();
        }
    }

    private void resumeMusic() {
        if (mMediaPlayer != null && mIsPlaying) {
            /* resumes playback */
            try{
                mMediaPlayer.start();
            }catch ( IllegalStateException e ){
                mMediaPlayer = null;
                setUpMediaPlayer();
                mMediaPlayer.seekTo( mMusicPosition );
                mMediaPlayer.start();
            }
        }
    }


    @Override
    public void onSeekComplete(MediaPlayer mp) {
        mMediaPlayer.start();
    }

}
