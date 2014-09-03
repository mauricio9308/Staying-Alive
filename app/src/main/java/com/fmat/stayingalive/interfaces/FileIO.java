package com.fmat.stayingalive.interfaces;

import java.io.IOException;
import java.io.InputStream;

public interface FileIO {
	public InputStream readAsset(String fileName) throws IOException;

	public InputStream readFile(String fileName) throws IOException;

	public InputStream writeFile(String fileName) throws IOException;
}
