package com.fmat.stayingalive.activity;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class SoundPoolTestActivity extends ActionBarActivity implements View.OnClickListener, SoundPool.OnLoadCompleteListener{

    private SoundPool mSoundPool;
    private int mExplosionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_sound_pool_test );
        findViewById( R.id.bttnPlay ).setOnClickListener( SoundPoolTestActivity.this /* OnClickListener */);
        mSoundPool = new SoundPool( 1 /* max Streams */,
                AudioManager.STREAM_MUSIC /* streamType */, 0 /* srcQuality*/ );
    }


    @Override
    public void onClick(View v) {
        if( v.getId() == R.id.bttnPlay ){
            try{
                playSound();
            }catch( IOException e ){
                if(BuildConfig.DEBUG){
                    e.printStackTrace();
                    Toast.makeText( SoundPoolTestActivity.this,
                            "Error al reproducir el sonido", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void playSound() throws IOException {
        AssetFileDescriptor fileDescriptor = getAssets().openFd("explosion.ogg");
        mExplosionId  = mSoundPool.load( fileDescriptor, 1 /* priority */);
        mSoundPool.setOnLoadCompleteListener( SoundPoolTestActivity.this );
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
        mSoundPool.play( mExplosionId, 0.99f /* left volume */, 0.99f /* rightvolume*/, 1 /* priority*/,
                0 /* loop, no loop */, 1 /* rate */);
    }
}
