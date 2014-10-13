package com.fmat.stayingalive.mrnom.screens;

import com.fmat.stayingalive.interfaces.Game;
import com.fmat.stayingalive.interfaces.Graphics;
import com.fmat.stayingalive.interfaces.Screen;
import com.fmat.stayingalive.interfaces.TouchEvent;
import com.fmat.stayingalive.mrnom.Assets;
import com.fmat.stayingalive.mrnom.Settings;

import java.util.List;

/**
 * Created by mauriciolara on 10/12/14.
 */
public class HelpScreen extends Screen{

    public HelpScreen( Game game ){
        super( game );
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = getGame().getInput().getTouchEvents();

        if( touchEvents == null || touchEvents.isEmpty() ){
            return;
        }

        for( TouchEvent event : touchEvents ){
            if( event.type == TouchEvent.TOUCH_UP ){
                if( event.x > 256 && event.y > 416 ){
                    getGame().setScreen( new HelpScreen( getGame() ));
                    if(Settings.soundEnabled){
                        Assets.click.play(1);
                    }
                    return;
                }
            }
        }

    }

    @Override
    public void present(float deltaTime) {
        Graphics g = getGame().getGraphics();
        g.drawPixmap( Assets.background, 0, 0 );
        g.drawPixmap(Assets.help1, 64, 100);
        g.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
