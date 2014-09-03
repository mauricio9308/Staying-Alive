package com.fmat.stayingalive.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by mauriciolara on 9/3/14.
 */
public class FullScreenTestActivity extends ActionBarActivity {

    @Override
    public void onCreate( Bundle savedInstanceState ){
        requestWindowFeature(Window.FEATURE_NO_TITLE );
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        super.onCreate(savedInstanceState);
    }
}
