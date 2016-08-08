package com.example.kc.letsbond;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class PositionLoggerService extends Service implements SensorEventListener {

    private SensorManager mSensorManager;
    private final float[] mAccelerometerReading = new float[3];
    private final float[] mMagnetometerReading = new float[3];

    private final float[] mRotationMatrix = new float[9];
    private final float[] mOrientationAngles = new float[3];
    private Sensor mSensor = null;



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand");
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        return START_STICKY;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
        // You must implement this callback in your code.
    }

    // Get readings from accelerometer and magnetometer. To simplify calculations,
    // consider storing these readings as unit vectors.
    @Override
    public void onSensorChanged(SensorEvent event) {

        //System.out.println("sensor changed");


        //System.out.println(".");
        System.arraycopy(event.values, 0, mAccelerometerReading,
                0, mAccelerometerReading.length);


        updateOrientationAngles();

    }

    // Compute the three orientation angles based on the most recent readings from
    // the device's accelerometer and magnetometer.

    public void updateOrientationAngles() {
        // Update rotation matrix, which is needed to update orientation angles.
        mSensorManager.getRotationMatrix(mRotationMatrix, null,
                mAccelerometerReading, mMagnetometerReading);

        // "mRotationMatrix" now has up-to-date information.

        mSensorManager.getOrientation(mRotationMatrix, mOrientationAngles);

        //System.out.println("...");
        System.out.println(mOrientationAngles[0] + ", " + mOrientationAngles[1] + ", " + mOrientationAngles[2]);
        // "mOrientationAngles" now has up-to-date information.
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
