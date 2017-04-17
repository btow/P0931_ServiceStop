package com.example.samsung.p0931_servicestop;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    final String LOG_TAG = "myLogs";
    private ExecutorService executorService;
    private Object someRes;
    private String message = "";

    @Override
    public void onCreate() {
        super.onCreate();
        message = "MyService onCreate()";
        Log.d(LOG_TAG, message);
        executorService = Executors.newFixedThreadPool(3);
        someRes = new Object();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        message = "MyService onDestroy()";
        Log.d(LOG_TAG, message);
        someRes = null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        message = "MyService onStartCommand()";
        Log.d(LOG_TAG, message);
        int time = intent.getIntExtra("time", 1);
        MyRun myRun = new MyRun(time, startId);
        executorService.execute(myRun);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class MyRun implements Runnable {

        int time, startId;

        public MyRun(int time, int startId) {
            this.time = time;
            this.startId = startId;
            message = "MyRun#" + startId + " create";
            Log.d(LOG_TAG, message);
        }

        public void run() {
            message = "MyRun#" + startId + " start, time = " + time;
            Log.d(LOG_TAG, message);
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                message = "MyRun#" + startId + " someRes = " + someRes.getClass();
                Log.d(LOG_TAG, message);
            } catch (NullPointerException e) {
                message = "MyRun#" + startId + " error, null pointer";
                Log.d(LOG_TAG, message);
            }
            stop();
        }

        private void stop() {
            message = "MyRun#" + startId + " end, stopSelfResult(" + startId + ") = " + stopSelfResult(startId);
            Log.d(LOG_TAG, message);
        }
    }
}
