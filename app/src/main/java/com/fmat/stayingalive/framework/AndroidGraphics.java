package com.fmat.stayingalive.framework;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.fmat.stayingalive.interfaces.Graphics;
import com.fmat.stayingalive.interfaces.Pixmap;
import com.fmat.stayingalive.interfaces.PixmapFormat;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Kevin on 9/24/2014.
 */
public class AndroidGraphics implements Graphics {

    AssetManager mManager;
    Bitmap mFrameBuffer;
    Canvas mCanvas;
    Rect mSrc = new Rect();
    Rect mDst = new Rect();
    Paint mPaint;

    public AndroidGraphics(AssetManager manager, Bitmap frameBuffer) {
        mManager = manager;
        mFrameBuffer = frameBuffer;
        mCanvas = new Canvas(frameBuffer);
        mPaint = new Paint();
    }

    @Override
    public Pixmap newPixmap(String fileName, PixmapFormat format) {
        Bitmap.Config config = null;
        if( format == PixmapFormat.RGB565 ){
            config = Bitmap.Config.RGB_565;
        }else{
            if( format == PixmapFormat.ARGB4444 ){
                config = Bitmap.Config.ARGB_4444;
            }else{
                config = Bitmap.Config.ARGB_8888;
            }
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = config;


        InputStream in = null;
        Bitmap bitmap = null;
        try {
            in = mManager.open(fileName);
            bitmap = BitmapFactory.decodeStream(in);
            if( bitmap == null ){
                throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '"
                    + fileName + "'");
        }finally {
            if( in != null ){
                try{
                    in.close();
                }catch ( IOException e ){
                    /* Nothing to do */
                }
            }
        }

        return new AndroidPixmap(bitmap, format);
    }

    public void clear( int color ){
        mCanvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, (color & 0xff));
    }

    @Override
    public void clear(int r, int g, int b) {
        mCanvas.drawRGB(r, g, b);
    }

    @Override
    public void drawPixel(int x, int y, int color) {
        mPaint.setColor(color);
        mCanvas.drawPoint(x, y, mPaint);
    }

    @Override
    public void drawLine(int x, int y, int x2, int y2, int color) {
        mPaint.setColor(color);
        mCanvas.drawLine(x, y, x2, y2, mPaint);
    }

    @Override
    public void drawRect(int x, int y, int width, int height, int color) {
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL);
        mCanvas.drawRect(x, y, x + width, y + height, mPaint);
    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight) {
        mSrc.left = srcX;
        mSrc.top = srcY;
        mSrc.right = srcX + srcWidth - 1;
        mSrc.bottom = srcY + srcHeight - 1;
        mDst.left = x;
        mDst.top = y;
        mDst.right = x + srcWidth - 1;
        mDst.bottom = y + srcHeight - 1;
        mCanvas.drawBitmap(((AndroidPixmap) pixmap).mBitmap, mSrc, mDst, null);
    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y) {
        mCanvas.drawBitmap(((AndroidPixmap) pixmap).mBitmap, x, y, null);
    }

    @Override
    public int getWidth() {
        return mFrameBuffer.getWidth();
    }

    @Override
    public int getHeight() {
        return mFrameBuffer.getHeight();
    }
}
