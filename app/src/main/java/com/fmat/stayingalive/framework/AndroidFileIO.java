package com.fmat.stayingalive.framework;

import android.content.res.AssetManager;
import android.os.Environment;

import com.fmat.stayingalive.interfaces.FileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Kevin on 9/24/2014.
 */
public class AndroidFileIO implements FileIO {

    AssetManager manager;
    String externalStoragePath;

    public AndroidFileIO(AssetManager manager) {
        this.manager = manager;
        this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    @Override
    public InputStream readAsset(String fileName) throws IOException {
        return manager.open(fileName);
    }

    @Override
    public InputStream readFile(String fileName) throws IOException {
        return new FileInputStream(externalStoragePath + fileName);
    }

    @Override
    public OutputStream writeFile(String fileName) throws IOException {
        return new FileOutputStream(externalStoragePath + fileName);
    }
}
