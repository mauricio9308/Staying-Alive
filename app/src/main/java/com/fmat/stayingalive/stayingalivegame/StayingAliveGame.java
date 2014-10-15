package com.fmat.stayingalive.stayingalivegame;

import com.fmat.stayingalive.framework.AndroidGame;
import com.fmat.stayingalive.interfaces.Screen;
import com.fmat.stayingalive.stayingalivegame.world.World;

/**
 * Created by Kevin on 14/10/2014.
 */
public class StayingAliveGame extends AndroidGame {

    public World mWorld;

    public StayingAliveGame() {
        mWorld = new World();
    }

    @Override
    public Screen getStartScreen() {
        return null; //should lead to first screen of game
    }
}
