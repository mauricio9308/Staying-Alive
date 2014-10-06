package com.fmat.stayingalive.mrnom;

import com.fmat.stayingalive.framework.AndroidGame;
import com.fmat.stayingalive.interfaces.Screen;

/**
 * Created by Kevin on 01/10/2014.
 */
public class MrNomGame extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen( MrNomGame.this );
    }
}
