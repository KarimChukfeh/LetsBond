package com.example.kc.letsbond;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by KC on 7/26/2016.
 */
public class AccelerometerLoggerService extends Service implements SensorEventListener {
    private static final String DEBUG_TAG = "AccelerometerLoggerService";

    private SensorManager sensorManager = null;
    private Sensor sensor = null;
    public SensorEventLoggerTask sensorEventLoggerTask;
    public MyListaIntegration myListaIntegration;

    public Float[] mylista;
    public Float[] myListaX;
    public Float[] myListaZ;

    public NumberFixedLengthFifoQueue mList;
    public NumberFixedLengthFifoQueue mListX;
    public NumberFixedLengthFifoQueue mListZ;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        mylista = new Float[25];
        myListaX = new Float[25];
        myListaZ = new Float[25];

        mList = new NumberFixedLengthFifoQueue(mylista);
        mListX = new NumberFixedLengthFifoQueue(myListaX);
        mListZ = new NumberFixedLengthFifoQueue(myListaZ);

        return START_STICKY;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        sensorEventLoggerTask = (SensorEventLoggerTask) new SensorEventLoggerTask().execute(sensorEvent);
        try {
            myListaIntegration = (MyListaIntegration) new MyListaIntegration().execute(mList);
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public String getName() {
        return "ABC";
    }

    private class SensorEventLoggerTask extends
            AsyncTask<SensorEvent, Void, Void> {

        @Override
        protected Void doInBackground(SensorEvent... events) {

            SensorEvent event = events[0];

            float valueX = event.values[0];
            float value = event.values[1];
            float valueZ = event.values[2];
            // log the value

            try {
                Context context = getApplicationContext();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("C:\\Users\\KC\\Desktop\\Disrupt\\me\\gitter\\LetsBond\\wear\\src\\main\\res\\data", Context.MODE_PRIVATE));
                outputStreamWriter.write(String.valueOf(value));
                outputStreamWriter.close();
            }
            catch (IOException e) {
                Log.e("Exception", "File write failed: " + e.toString());
            }

            long timeInMillis = System.currentTimeMillis();

            //if(value<0) value += 10;

            float v = (float) ((value * timeInMillis)/(Math.pow(10,13)));

            //if(valueX<3 && value<0 && valueZ<9) System.out.println(", " + valueX + ", " + value + ", " + valueZ + "," + timeInMillis + ", Handshake position");
            //else System.out.println(", " + valueX + ", " + value + ", " + valueZ + "," + timeInMillis);

            try {
                Thread.sleep(230);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mList.add(value);
            mListX.add(valueX);
            mListZ.add(valueZ);

            System.out.println("\n\n");
            System.out.println(mListX);
            System.out.println(mList);
            System.out.println(mListZ);
            System.out.println("\n\n");

            return null;

        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy(){
        System.out.println(mList.toString());
        sensorManager.unregisterListener(this);
        sensorEventLoggerTask.cancel(true);
        System.exit(0);
    }

    private class MyListaIntegration extends AsyncTask<NumberFixedLengthFifoQueue, Void, Void>{

        @Override
        protected Void doInBackground(NumberFixedLengthFifoQueue... events) {

            Float first = null;
            Float second = null;
            Float third = null;
            Float slope = null;

            try {
                first = Float.valueOf(mList.get(22).toString());
                second = Float.valueOf(mList.get(23).toString());
                third = Float.valueOf(mList.get(24).toString());
                slope = third - second;

            }catch(Exception e){

            }
            //try{
            //if(Math.abs(Math.round(slope))!=9) {
            //System.out.println("\n" + (third - second) + "\n");
            //}else{
            //System.out.println("\n0.0000\n");
            //}
            //}catch(Exception e){

            //}


            return null;
        }
    }
}
