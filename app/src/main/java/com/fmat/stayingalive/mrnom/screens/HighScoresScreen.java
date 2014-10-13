package com.fmat.stayingalive.mrnom.screens;

import com.fmat.stayingalive.interfaces.Game;
import com.fmat.stayingalive.interfaces.Graphics;
import com.fmat.stayingalive.interfaces.TouchEvent;
import com.fmat.stayingalive.mrnom.Assets;
import com.fmat.stayingalive.mrnom.Settings;

import java.util.List;

/**
 * Created by Kevin on 01/10/2014.
 */
public class HighScoresScreen extends ScreenBase {

    String[] lines = new String[5];

    public HighScoresScreen(Game game) {
        super(game);
        for (int i = 0; i < lines.length; i++) {
            lines[i] = (i + 1) + ". " + Settings.highscores[i];
        }
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEventList = mGame.getInput().getTouchEvents();

        for(TouchEvent event : touchEventList) {
            if (event.type == TouchEvent.TOUCH_UP) {
                if( event.x < 64 && event.y > 416 ){
                    if( Settings.soundEnabled){
                        Assets.click.play(1);
                    }

                    getGame().setScreen( new MenuScreen( getGame() ));
                    return;
                }

            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics graphics = mGame.getGraphics();
        graphics.drawPixmap(Assets.background, 0, 0);
        graphics.drawPixmap(Assets.mainMenu, 64, 20, 0, 42, 196, 42);

        int y = 100;
        for (int i = 0; i < lines.length; i++) {
            drawText(graphics, lines[i], 20, y );
            y += 50;
        }

        graphics.drawPixmap( Assets.buttons, 0, 416, 64, 64, 64, 64 );
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
