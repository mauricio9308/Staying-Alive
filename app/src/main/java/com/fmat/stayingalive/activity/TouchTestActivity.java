package com.fmat.stayingalive.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by mauriciolara on 9/3/14.
 */
public class TouchTestActivity extends ActionBarActivity implements View.OnTouchListener{

    private static final String LOG_TAG = MultiTouchTestActivity.class.getSimpleName();

    private StringBuilder mBuilder;
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBuilder = new StringBuilder();
        mText = new TextView( TouchTestActivity.this );
        mText.setOnTouchListener( TouchTestActivity.this /* OnTouchListener */);
        setContentView( mText );
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mBuilder.setLength(0);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mBuilder.append("down, ");
                break;
            case MotionEvent.ACTION_MOVE:
                mBuilder.append("move, ");
                break;
            case MotionEvent.ACTION_CANCEL:
                mBuilder.append("cancle, ");
                break;
            case MotionEvent.ACTION_UP:
                mBuilder.append("up, ");
                break;
        }
        mBuilder.append(event.getX());
        mBuilder.append(", ");
        mBuilder.append(event.getY());
        String text = mBuilder.toString();
        Log.d("TouchTest", text);
        mText.setText(text);
        return true;
    }


}
