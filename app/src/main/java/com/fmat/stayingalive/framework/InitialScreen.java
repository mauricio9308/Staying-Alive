package com.fmat.stayingalive.framework;

import com.fmat.stayingalive.interfaces.Game;
import com.fmat.stayingalive.interfaces.Graphics;
import com.fmat.stayingalive.interfaces.Pixmap;
import com.fmat.stayingalive.interfaces.Screen;

/**
 * Created by mauriciolara on 9/22/14.
 */
public class InitialScreen extends Screen {

    private Pixmap mSampleImage;
    private double mXValue;

    public InitialScreen( Game game ){
       super( game );
       mSampleImage = game.getGraphics().newPixmap("data/pic.png",
               Graphics.PixmapFormat.ARGB8888 );
    }

    @Override
    public void update(float deltaTime) {
        mXValue += 50 * deltaTime; //moves 50 pixels every second
        if( mXValue > 100 ){
            mXValue = 0;
        }
    }

    @Override
    public void present(float deltaTime) {
        getGame().getGraphics().clear(0);
        getGame().getGraphics().drawPixmap( mSampleImage, (int)mXValue, 0, 0, 0,
                mSampleImage.getWidth(), mSampleImage.getHeight() );

    }

    @Override
    public void pause() {
        // nothing to do here
    }

    @Override
    public void resume() {
        // nothing to do here
    }

    @Override
    public void dispose() {
        mSampleImage.dispose();
    }
}
