package com.fmat.stayingalive.mrnom;

import com.fmat.stayingalive.interfaces.Game;
import com.fmat.stayingalive.interfaces.Graphics;
import com.fmat.stayingalive.interfaces.Input;
import com.fmat.stayingalive.interfaces.TouchEvent;

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
        List<Input.TouchEvent> touchEventList = mGame.getInput().getTouchEvents();

        for(Input.TouchEvent event : touchEventList) {
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 0, 416, 64, 64)) {
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if (Settings.soundEnabled) {
                        playSound();
                        mGame.setScreen(new MenuScreen(mGame));
                    }
                }

                if (inBounds(event, 64, 220, 192, 42)) {
                    playSound();
                }
                if (inBounds(event, 64, 262, 192, 42)) {
                    playSound();
                }
                if (inBounds(event, 64, 304, 192, 42)) {
                    playSound();
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics graphics = mGame.getGraphics();
        graphics.drawPixmap(Assets.background, 0, 0);
        graphics.drawPixmap(Assets.mainMenu, 64, 20, 0, 42, 196, 42);
        graphics.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64, 64);

        int y = 100;
        for (int i = 0; i < lines.length; i++) {

        }
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
