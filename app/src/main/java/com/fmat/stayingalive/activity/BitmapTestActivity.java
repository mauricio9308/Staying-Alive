package com.fmat.stayingalive.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

public class BitmapTestActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( new RenderView( BitmapTestActivity.this ));
    }

    private class RenderView extends View {

        private Bitmap mBob565;
        private Bitmap mBob4444;
        private Rect mDestinationRect = new Rect();

        public RenderView(Context context) {
            super(context);

            try{
                AssetManager assetManager = context.getAssets();
                InputStream inputStream = assetManager.open("bobrgb888.png");
                mBob565 = BitmapFactory.decodeStream( inputStream );
                inputStream.close();
                Log.d("BitmapTest", "bobrgb888.png format: " +mBob565.getConfig() );

                inputStream = assetManager.open("bobargb8888.png");
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_4444;
                mBob4444 = BitmapFactory.decodeStream( inputStream, null, options );
                inputStream.close();
                Log.d("BitmapTest", "bobargb8888.png format: " + mBob4444.getConfig());


            }catch( IOException e ){

            }
        }


        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawRGB(100, 100, 100);
            mDestinationRect.set(50, 50, 350, 350);
            canvas.drawBitmap(mBob565, null, mDestinationRect, null);
            canvas.drawBitmap(mBob4444, 100, 500, null);
            invalidate();
        }
    }

}
