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
 * Created by mauriciolara on 9/22/14.
 */
public class AndroidFileIO implements FileIO {

    private AssetManager mAssetManager;
    private final String EXTERNAL_STORAGE_PATH;

    public AndroidFileIO(AssetManager assetManager) {
        mAssetManager = assetManager;
        EXTERNAL_STORAGE_PATH = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator;
    }

    @Override
    public InputStream readAsset(String fileName) throws IOException {
        return mAssetManager.open( fileName );
    }

    @Override
    public InputStream readFile(String fileName) throws IOException {
        return new FileInputStream( EXTERNAL_STORAGE_PATH + fileName );
    }

    @Override
    public OutputStream writeFile(String fileName) throws IOException {
        return new FileOutputStream( EXTERNAL_STORAGE_PATH + fileName );
    }
}
