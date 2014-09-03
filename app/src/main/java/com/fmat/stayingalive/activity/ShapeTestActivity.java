package com.fmat.stayingalive.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import java.util.Random;

public class ShapeTestActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( new RenderView( ShapeTestActivity.this ));
    }

    private class RenderView extends View {

        private Random mRand;
        private Paint mPaint;

        public RenderView(Context context) {
            super(context);
            mRand = new Random();
        }

        @Override
        public void onDraw( Canvas canvas ){
            canvas.drawRGB( 255, 255, 255 );
            mPaint = new Paint( Color.RED );
            canvas.drawLine(0, 0, canvas.getWidth() - 1, canvas.getHeight() - 1, mPaint );

            mPaint.setStyle(Paint.Style.STROKE );
            mPaint.setColor( 0xff00ff00 );
            canvas.drawCircle( canvas.getWidth() / 2, canvas.getHeight() / 2, 40, mPaint );

            mPaint.setStyle(Paint.Style.FILL );
            mPaint.setColor( 0x770000ff );
            canvas.drawRect( 100, 100, 200, 200, mPaint );

            invalidate();
        }
    }
}
