package com.fmat.stayingalive.interfaces;

public class TouchEvent {
	public static final int TOUCH_DOWN = 0; 
	public static final int TOUCH_UP = 1; 
	public static final int TOUCH_DRAG = 2; 
	public int type; 
	public int x, y; 
	public int pointer; 
}
