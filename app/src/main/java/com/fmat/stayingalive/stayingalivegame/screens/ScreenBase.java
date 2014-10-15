package com.fmat.stayingalive.stayingalivegame.screens;

import com.fmat.stayingalive.interfaces.Game;
import com.fmat.stayingalive.interfaces.Screen;
import com.fmat.stayingalive.interfaces.TouchEvent;
import com.fmat.stayingalive.stayingalivegame.StayingAliveGame;
import com.fmat.stayingalive.stayingalivegame.world.World;

/**
 * Created by Kevin on 14/10/2014.
 */
public abstract class ScreenBase extends Screen {

    public ScreenBase(Game game) {
        super(game);
    }

    protected boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1){
            return true;
        }else {
            return false;
        }
    }

}
