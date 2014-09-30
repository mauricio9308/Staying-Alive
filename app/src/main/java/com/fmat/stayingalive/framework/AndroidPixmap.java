package com.fmat.stayingalive.framework;

import android.graphics.Bitmap;

import com.fmat.stayingalive.interfaces.Pixmap;
import com.fmat.stayingalive.interfaces.PixmapFormat;

/**
 * Created by Kevin on 9/24/2014.
 */
public class AndroidPixmap implements Pixmap {

    Bitmap mBitmap;
    PixmapFormat mFormat;

    public AndroidPixmap(Bitmap bitmap, PixmapFormat format) {
        mBitmap = bitmap;
        mFormat = format;
    }

    @Override
    public int getWidth() {
        return mBitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return mBitmap.getHeight();
    }

    @Override
    public PixmapFormat getFormat() {
        return mFormat;
    }

    @Override
    public void dispose() {
        mBitmap.recycle();
    }
}
