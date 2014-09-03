package com.fmat.stayingalive.activity;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mauriciolara on 9/1/14.
 */
public class AccelerometerTestActivity extends Activity implements SensorEventListener{

    private StringBuilder mBuilder;
    private SensorManager mSensorManager;
    private TextView mText;

    @Override
    public void onCreate( Bundle savedInstanceState ){
        super.onCreate( savedInstanceState );

        mText = new TextView( AccelerometerTestActivity.this );
        setContentView( mText );

        mBuilder = new StringBuilder();

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = mSensorManager.getSensorList( Sensor.TYPE_ACCELEROMETER );
        if( sensorList.size() > 0 ){
            Sensor accelerometer  = sensorList.get(0);
            boolean isSensorRegistered =  mSensorManager.registerListener( AccelerometerTestActivity.this,
                    accelerometer, SensorManager.SENSOR_DELAY_GAME );
            mText.setText( ( isSensorRegistered ) ? "Sensor registered" : "Error registering sensor");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        mBuilder.setLength(0);
        mBuilder.append("x: ");
        mBuilder.append(event.values[0]);
        mBuilder.append(", y: ");
        mBuilder.append(event.values[1]);
        mBuilder.append(", z: ");
        mBuilder.append(event.values[2]);
        mText.setText( mBuilder.toString());

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
