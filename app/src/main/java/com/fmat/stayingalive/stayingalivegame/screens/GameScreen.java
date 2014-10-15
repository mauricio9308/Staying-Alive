package com.fmat.stayingalive.stayingalivegame.screens;

import com.fmat.stayingalive.interfaces.Game;
import com.fmat.stayingalive.interfaces.TouchEvent;
import com.fmat.stayingalive.stayingalivegame.StayingAliveGame;
import com.fmat.stayingalive.stayingalivegame.screens.ScreenBase;
import com.fmat.stayingalive.stayingalivegame.world.World;

import java.util.List;

/**
 * Created by Kevin on 14/10/2014.
 */
public class GameScreen extends ScreenBase {

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> events = mGame.getInput().getTouchEvents();
        World world = ((StayingAliveGame) mGame).mWorld;

        for (TouchEvent event : events) {

            if (event.type == TouchEvent.TOUCH_DOWN) { //TODO MrNom uses TOUCH_UP instead, if anything suspicious happens this may be it
                if (inBounds(event, 0, 0, 0, 0)) { //TODO pause button

                } else if (inBounds(event, 0, 0, 0, 0)) { //left-pad
                    world.playerMoveLeft();
                } else if (inBounds(event, 0, 0, 0, 0)) { //right pad
                    world.playerMoveRight();
                } else if (inBounds(event, 0, 0, 0, 0)) { //up pad
                    world.playerJump();
                } else if (inBounds(event, 0, 0, 0, 0)) { //down pad
                    world.playerCrouch();
                }

                if (inBounds(event, 0, 0, 0 ,0)) { //shield pressed
                    world.activateShield();
                }
                if (inBounds(event, 0, 0, 0, 0)) { //power-up pressed
                    world.activatePowerUp();
                }
            }
        }
        world.update(deltaTime);

        if(world.isGameOver) {
            //TODO game over handling
        }

    }

    @Override
    public void present(float deltaTime) {
        //TODO draw world stuff
    }

    @Override
    public void pause() {
        //TODO send to pause screen
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
