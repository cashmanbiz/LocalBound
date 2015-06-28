package com.example.jjcashman.localbound;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class LocalBoundActivity extends ActionBarActivity {

    BoundService myService;
    boolean isBound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_bound);

        Intent intent = new Intent(this,BoundService.class);

        bindService(intent,myServiceConnection, Context.BIND_AUTO_CREATE);
    }

    public void showTime(View view){
        String currentTime= myService.getCurrentTime();
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(currentTime);
        TextView textView2 = (TextView)findViewById(R.id.textView2);
        textView2.setText(currentTime);
    }

    private ServiceConnection myServiceConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            BoundService.MyLocalBinder binder =(BoundService.MyLocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
             isBound = false;
        }
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_local_bound, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
