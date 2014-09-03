package com.fmat.stayingalive.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

/**
 * Created by mauriciolara on 9/3/14.
 */
public class WakeLockTestActivity extends FullScreenTestActivity {

    private static final String WAKELOCK_TAG = "base_wakelock";
    private PowerManager.WakeLock mWakeLock;

    @Override
    public void onCreate( Bundle savedInstanceState ){
        PowerManager powerManager = ( PowerManager ) getSystemService( Context.POWER_SERVICE );
        mWakeLock = powerManager.newWakeLock( PowerManager.PARTIAL_WAKE_LOCK, WAKELOCK_TAG );
        super.onCreate( savedInstanceState );
    }

    @Override
    public void onResume(){
        super.onResume();
        mWakeLock.acquire();
    }

    @Override
    public void onPause(){
        super.onPause();
        mWakeLock.release();
    }
}
