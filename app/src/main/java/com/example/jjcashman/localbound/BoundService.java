package com.example.jjcashman.localbound;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BoundService extends Service {

    private final IBinder myBinder=new MyLocalBinder();

    public BoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return myBinder;
    }

    public String getCurrentTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.UK);
        return dateFormat.format(new Date());
    }

    public class MyLocalBinder extends Binder{
        BoundService getService(){
            return BoundService.this;
        }
    }
}
