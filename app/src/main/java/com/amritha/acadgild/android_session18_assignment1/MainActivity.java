package com.amritha.acadgild.android_session18_assignment1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //initializing integer variable for Battery level

    public static int level;

    //Creating TextView variable

    TextView textPercentage;

    private BroadcastReceiver BatteryPercentageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            //getting current Battery percentage using BatteryManager

            textPercentage.setText("Battery Level Remaining:" + String.valueOf(level) + "%");//Setting Text to TextView
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textPercentage = findViewById(R.id.textPercentage);//Finding view For the Text

    }

    @Override
    protected void onResume() {
        super.onResume();

        //only when the screen is visible, we want to register the BroadCast Receiver

        registerReceiver(BatteryPercentageReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        //Registering Battery Changed Intent and BroadCastReceiver
    }

    @Override
    protected void onPause() {
        super.onPause();

        //onScreen exit, we want to unregister the BroadCast Receiver

        unregisterReceiver(BatteryPercentageReceiver);

    }
}
