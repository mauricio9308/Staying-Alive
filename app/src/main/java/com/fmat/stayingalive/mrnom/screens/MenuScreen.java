package com.fmat.stayingalive.mrnom.screens;

import android.util.Log;

import com.fmat.stayingalive.interfaces.Game;
import com.fmat.stayingalive.interfaces.Graphics;
import com.fmat.stayingalive.interfaces.TouchEvent;
import com.fmat.stayingalive.mrnom.Assets;
import com.fmat.stayingalive.mrnom.Settings;

import java.util.List;

/**
 * Created by Kevin on 01/10/2014.
 */
public class MenuScreen extends ScreenBase {

    private static final String LOG_TAG = MenuScreen.class.getSimpleName();

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics gameGraphics = getGame().getGraphics();
        List<TouchEvent> touchEventList = mGame.getInput().getTouchEvents();

        if (touchEventList == null) {
            return;
        }

        for (TouchEvent event : touchEventList) {
            if (event.type == TouchEvent.TOUCH_UP) {

                if (inBounds(event, 0, gameGraphics.getHeight() - 64, 64, 64)) {
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    Log.d(LOG_TAG, "Sounds");
                    return;
                }

                if (inBounds(event, 64, 220, 192, 42)) {
                    getGame().setScreen(new BookGameScreen(getGame()));
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    Log.d(LOG_TAG, "GAME");

                    return;
                }
                if (inBounds(event, 64, 220 + 42, 192, 42)) {
                    getGame().setScreen(new HighScoresScreen(getGame()));
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    Log.d(LOG_TAG, "Highscores");
                    return;
                }
                if (inBounds(event, 64, 220 + 84, 192, 42)) {
                    getGame().setScreen(new HelpScreen(getGame()));
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    Log.d(LOG_TAG, "Help");
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics graphics = getGame().getGraphics();
        graphics.drawPixmap(Assets.background, 0, 0);
        graphics.drawPixmap(Assets.logo, 32, 20);
        graphics.drawPixmap(Assets.mainMenu, 64, 220);
        if (Settings.soundEnabled) {
            graphics.drawPixmap(Assets.buttons, 0, 416, 0, 0, 64, 64);
        } else {
            graphics.drawPixmap(Assets.buttons, 0, 416, 64, 0, 64, 64);
        }
    }

    @Override
    public void pause() {
        Settings.save(mGame.getFileIO());
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
