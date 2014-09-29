package com.fmat.stayingalive.interfaces;

public interface Graphics {
	public Pixmap newPixmap(String fileName, PixmapFormat format);

	public void clear(int r, int g, int b);

	public void drawPixel(int x, int y, int color);

	public void drawLine(int x, int y, int x2, int y2, int color);

	public void drawRect(int x, int y, int width, int height, int color);

	public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY,
                           int srcWidth, int srcHeight);

	public void drawPixmap(Pixmap pixmap, int x, int y);

	public int getWidth();

	public int getHeight();
}
