package com.fmat.stayingalive.interfaces;

public abstract class Screen {
	protected final Game mGame;

	public Screen(Game game) {
		mGame = game;
	}

	public abstract void update(float deltaTime);

	public abstract void present(float deltaTime);

	public abstract void pause();

	public abstract void resume();

	public abstract void dispose();

    public Game getGame(){
        return mGame;
    }
}
