package com.fmat.stayingalive.activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

/**
 * Created by Mauricio Lara on 9/1/14.
 */
public class AndroidBasicStarter extends ListActivity implements AdapterView.OnItemClickListener {

    private final String[] TESTS = new String[]{"LifeCycleTestsActivity", "TouchTestActivity", "MultiTouchTestActivity", "AssetsTestActivity",
            "ExternalStorageTestActivity", "KeyTestActivity", "AccelerometerTestActivity", "FullScreenTestActivity",
            "WakeLockTestActivity", "ShapeTestActivity", "RenderViewTestActivity", "BitmapTestActivity",
            "SoundPoolTestActivity", "MediaPlayerTestActivity", "FontTestActivity", "SurfaceViewTestActivity"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(AndroidBasicStarter.this,
                android.R.layout.simple_expandable_list_item_1, TESTS));
        getListView().setOnItemClickListener(AndroidBasicStarter.this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            Class testClass = Class.forName("com.fmat.stayingalive.activity." + TESTS[position]);
            Intent launchTest = new Intent(AndroidBasicStarter.this /* context */, testClass);
            startActivity(launchTest);
        } catch (ClassNotFoundException e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }

            Toast.makeText(AndroidBasicStarter.this, "Activity not implemented", Toast.LENGTH_SHORT).show();
        }

    }
}
