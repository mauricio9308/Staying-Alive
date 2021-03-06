package com.fmat.stayingalive.mrnom;

import com.fmat.stayingalive.framework.AndroidGame;
import com.fmat.stayingalive.interfaces.Screen;
import com.fmat.stayingalive.mrnom.screens.LoadingScreen;
import com.fmat.stayingalive.mrnom.world.World;

/**
 * Created by Kevin on 01/10/2014.
 */
public class MrNomGame extends AndroidGame {

    public World world;

    public MrNomGame() {
        world = new World();
    }

    @Override
    public Screen getStartScreen() {
        return new LoadingScreen( MrNomGame.this );
    }
}
