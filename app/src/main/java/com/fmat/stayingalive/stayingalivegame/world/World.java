package com.fmat.stayingalive.stayingalivegame.world;

import java.util.ArrayList;

/**
 * Created by Kevin on 14/10/2014.
 */
public class World {

    public static final int WORLD_HEIGHT = 0; //dummy
    public static final int WORLD_WIDTH = 0; //dummy

    public static final int MAX_SHIELD_ENERGY = 0; //dummy
    public static final int TOTAL_LIVES = 3;

    public static final int NORMAL_MOVEMENT_SPEED = 0; //dummy
    public static final int BOOSTED_MOVEMENT_SPEED = 0; //dummy

    public static final int INVINCIBILITY_DURATION = 0; //dummy
    public static final int SPEEDUP_DURATION = 0; //dummy

    Player player;
    ArrayList<Bullet> bullets;
    ArrayList<PowerUp> powerUps;

    int remainingLives;
    int score;
    int shieldEnergy;
    boolean isShieldActive;
    PowerUp.Effect storedPower;

    int invincibilityCounter = 0;
    int speedUpCounter = 0;

    public boolean isGameOver;

    public World() {
        player = new Player();
        bullets = new ArrayList<Bullet>();
        powerUps = new ArrayList<PowerUp>();

        shieldEnergy = MAX_SHIELD_ENERGY;
        storedPower = null;
        score = 0;
        remainingLives = TOTAL_LIVES;
    }

    public void update(float deltaTime) {



    }

    public void generateBullet() {
        bullets.add(new Bullet(0, 0, 0)); //TODO randomize bullet generation and stuff
    }
    public void generatePowerUp() {
        powerUps.add(new PowerUp(0, 0, 0)); //TODO randomize power up generation
    }

    //TODO check when player hits wall
    public void playerMoveLeft() {
        if (speedUpCounter > 0) {
            player.move(player.x - BOOSTED_MOVEMENT_SPEED, player.y);
        } else {
            player.move(player.x - NORMAL_MOVEMENT_SPEED, player.y);
        }
    }
    public void playerMoveRight() {
        if (speedUpCounter > 0) {
            player.move(player.x + BOOSTED_MOVEMENT_SPEED, player.y);
        } else {
            player.move(player.x + NORMAL_MOVEMENT_SPEED, player.y);
        }
    }
    public void playerJump() {
        if (!player.crouching && player.jumpState == 0) {
            player.jumpState = player.JUMP_STATE_MAX;
        }
    }

    public void playerCrouch() {
        if (player.jumpState == 0) {
            player.crouching = true;
        }
    }

    public void activateShield() {
        if (shieldEnergy > 0) {
            isShieldActive = true;
        }
    }

    public void activatePowerUp() {
        if (storedPower != null) {
            switch(storedPower) {
                case BURST:
                    //TODO code to destroy all bullets in a certain radius around user
                    break;
                case INVINCIBLE:
                    invincibilityCounter = INVINCIBILITY_DURATION;
                    break;
                case SPEEDUP:
                    speedUpCounter = SPEEDUP_DURATION;
                    break;
            }
        }
        storedPower = null;
    }

}
