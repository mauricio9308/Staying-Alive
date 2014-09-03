package stayingalive.fmat.com.activity;

import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MultiTouchTestActivity extends ActionBarActivity implements OnTouchListener {

    private StringBuilder mBuilder;
    private TextView mTextView;
    private float[] mPointerX;
    private float[] mPointerY;
    private boolean[] mTouched;

    @Override
    public void onCreate( Bundle savedInstanceState ){
        super.onCreate( savedInstanceState );

        mTextView = new TextView( MultiTouchTestActivity.this );
        mTextView.setOnTouchListener( MultiTouchTestActivity.this );
        setContentView( mTextView );

        mBuilder = new StringBuilder();
        mPointerX = new float[10];
        mPointerY = new float[10];
        mTouched = new boolean[10];
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int action;
        int pointerIndex;
        int pointerId;

        if( BuildVersionUtils.isGingerbreadOrAbove() ){
            action = MotionEventCompat.getActionMasked(event);
            pointerIndex = MotionEventCompat.getActionIndex( event );
            pointerId = MotionEventCompat.getPointerId( event, pointerIndex );
        }else{
            action = event.getAction() & MotionEvent.ACTION_MASK;
            pointerIndex = ( event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK)
                    >> MotionEvent.ACTION_POINTER_ID_SHIFT;
            pointerId = event.getPointerId( pointerIndex );
        }

        switch( action ){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                mTouched[ pointerId ] = true;
                mPointerX[ pointerId ] = (int) event.getX( pointerIndex );
                mPointerY[ pointerId ] = (int) event.getY( pointerIndex );
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                mTouched[ pointerId ] = false;
                mPointerX[ pointerId ] = (int) event.getX( pointerIndex );
                mPointerY[ pointerId ] = (int) event.getY( pointerIndex );
                break;
            case MotionEvent.ACTION_MOVE:
                int pointerCount = event.getPointerCount();
                for( int i = 0; i < pointerCount; i ++ ){
                    pointerIndex = i;
                    pointerId = event.getPointerId( pointerIndex );
                    mPointerX[ pointerId ] = (int) event.getX( pointerIndex );
                    mPointerY[ pointerId ] = (int) event.getY( pointerIndex );
                }
                break;
        }
        updateTextView();
        return true;
    }

    private void updateTextView(){
        mBuilder.setLength(0);
        for( int i = 0; i < 10; i ++ ){
            mBuilder.append( mTouched[i] );
            mBuilder.append(",");
            mBuilder.append(mPointerX[i]);
            mBuilder.append(",");
            mBuilder.append(mPointerY[i]);
            mBuilder.append("\n");
        }
        mTextView.setText( mBuilder.toString() );
    }
}
