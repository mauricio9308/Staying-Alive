package com.fmat.stayingalive.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Random;

/**
 * Created by mauriciolara on 9/3/14.
 */
public class RenderViewTestActivity extends ActionBarActivity {

    @Override
    public void onCreate( Bundle savedInstanceState ){
        requestWindowFeature(Window.FEATURE_NO_TITLE );
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        super.onCreate( savedInstanceState );

        setContentView( new RenderView( RenderViewTestActivity.this ));
    }

    private class RenderView extends View {

        private Random mRand;

        public RenderView(Context context) {
            super(context);
            mRand = new Random();
        }

        @Override
        public void onDraw( Canvas canvas ){
            canvas.drawRGB(mRand.nextInt(256), mRand.nextInt(256), mRand.nextInt(256));
            invalidate();
        }
    }
}
