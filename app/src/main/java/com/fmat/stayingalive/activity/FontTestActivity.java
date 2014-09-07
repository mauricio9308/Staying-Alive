package com.fmat.stayingalive.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class FontTestActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( new TextRenderView( FontTestActivity.this /* context */) );
    }

    private class TextRenderView extends View {

        private Paint mPaint;

        public TextRenderView(Context context) {
            super(context);
            mPaint = new Paint();
        }

        @Override
        public void onDraw( Canvas canvas ){
            Typeface font = Typeface.createFromAsset( getAssets(), "font.ttf");
            mPaint.setTypeface( font );
            mPaint.setTextSize( 30 );
            canvas.drawText("This is a test!", 100, 100, mPaint );
            invalidate();
        }
    }

}
