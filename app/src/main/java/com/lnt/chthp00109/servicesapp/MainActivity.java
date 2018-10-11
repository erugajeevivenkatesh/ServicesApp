package com.lnt.chthp00109.servicesapp;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int Permission_All = 1;
    SaveLogFiles logFiles=new SaveLogFiles();

    private static final String TAG = MainActivity.class.getSimpleName();
    public SeekBar Ringervolume;
    AudioManager audioManager;
    final BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
    TextView Batterymode, Batterylevel;
    ImageButton OnOfBluetooth, Wifionof;
    String Batterystatus = "";
    int deviceStatus;

    public Boolean Onstatus = false;
    public Boolean permission = false;

    public static Boolean WifiOnStatus = false;
 //   BluetoothManager
    IntentFilter intentFilter;


    String[] perimissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_ADMIN};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Batterymode = findViewById(R.id.Batterystate);
        OnOfBluetooth = findViewById(R.id.Onofbluetooth);
        Wifionof = findViewById(R.id.WifiOnofff);
        Batterylevel = findViewById(R.id.BatteryPercentage);
        volumecontrol();
        Log.d(TAG,logFiles.Logfile());

        MainActivity.this.registerReceiver(broadcastReceiver,intentFilter);
        if (!hasPermissions(this, perimissions)) {
            ActivityCompat.requestPermissions(this, perimissions, Permission_All);
        }
    }

    public static boolean hasPermissions(Context context, String[] perimissionss) {
        if (context != null && perimissionss != null) {
            for (String permission : perimissionss) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public void volumecontrol() {
         logFiles.Senddata(TAG+"-->Controlling Ringer volume");
        try {
            Ringervolume = (SeekBar) findViewById(R.id.changeRinger);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            Ringervolume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_RING));
            Ringervolume.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_RING));
            Ringervolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    audioManager.setStreamVolume(AudioManager.STREAM_RING, progress, 0);
                logFiles.Senddata(TAG+"-->changing Ringer volume");
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void OnBluetoooth(View view) {
        try {
            if (bAdapter == null) {
                Toast.makeText(MainActivity.this, "BlueTooth Not supported", Toast.LENGTH_SHORT).show();
            } else {
                if (!Onstatus) {
                    if (!bAdapter.isEnabled()) {
                        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), Permission_All);
                        Onstatus = true;
                        // OnOfBluetooth.setImageResource(R.drawable.bluetoothoff);
                        //   Toast.makeText(MainActivity.this, "BlueTooth TUrned On", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    bAdapter.disable();
                    Onstatus = false;
                    OnOfBluetooth.setImageResource(R.drawable.onbluetooth);
                      logFiles.Senddata(TAG+"-->Bluetooth is off");


                    //Toast.makeText(MainActivity.this, "BlueTooth Turned OF", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException e) {
            Log.e(TAG, e.toString());
        }
    }

    public void WifiOn(View view) {
        try {
            WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (!WifiOnStatus) {
                if (wifi != null) {
                    WifiOnStatus = true;
                    wifi.setWifiEnabled(true);
                            logFiles.Senddata(TAG+"-->Wifi Is on");
                    Toast.makeText(this, "WifiisOn", Toast.LENGTH_SHORT).show();
                    Wifionof.setImageResource(R.drawable.wifionstatus);

                }
            } else {
                WifiOnStatus = false;
                Toast.makeText(this, "WifiisOf", Toast.LENGTH_SHORT).show();
                     logFiles.Senddata(TAG+"-->Wifi Is off");
                wifi.setWifiEnabled(false);
                Wifionof.setImageResource(R.drawable.wifion);
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public void searchwifi(View view) {
       logFiles.Senddata(TAG+"-->Searching for Available Networks");
        try {
            WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (wifi.isWifiEnabled()) {
                Intent inted = new Intent(Settings.ACTION_WIFI_SETTINGS);
                startActivity(inted);
            } else
                Toast.makeText(this, "WIFI DISABLE PLEASE ENABLE", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public void SceduleActivity(View view) {
        logFiles.Senddata(TAG+"-->Starting Sceduling Task Activity");
        startActivity(new Intent(getApplicationContext(), Sceduletask.class));
    }

    public void SerchNearBlueTooth(View view) {
        try {


        if (bAdapter.isEnabled()) {
            Intent inte = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
            startActivity(inte);
            logFiles.Senddata(TAG+"-->search near Bluetoth devices");

        } else
            Toast.makeText(this, "Please Enable Bluetooth", Toast.LENGTH_SHORT).show();
    }catch(NullPointerException e)
    {
       Log.e(TAG,e.toString());
    }
}
    @Override
    protected void onResume() {
        super.onResume();
        if(permission)
        {
            Onstatus = true;
            OnOfBluetooth.setImageResource(R.drawable.sharebluetooth);

        }
        else {
            OnOfBluetooth.setImageResource(R.drawable.onbluetooth);

        }
    }
    private BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            deviceStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int batteryLevel=(int)(((float)level / (float)scale) * 100.0f);
            if(deviceStatus == BatteryManager.BATTERY_STATUS_CHARGING){
                Batterystatus="CHARGING";
                logFiles.Senddata(TAG+"-->Batterystatus is charging");
                Batterymode.setText(Batterystatus.toString());
                Batterylevel.setText(String.valueOf(batteryLevel).concat("%"));

            }
            if(deviceStatus == BatteryManager.BATTERY_STATUS_DISCHARGING){
                logFiles.Senddata(TAG+"-->Battery status discharging");
                Batterystatus="DISCHARGING";
                Batterymode.setText(Batterystatus.toString());
                Batterylevel.setText(String.valueOf(batteryLevel).concat("%"));

            }
            if (deviceStatus == BatteryManager.BATTERY_STATUS_FULL){

                Batterystatus="BATTERY FULL";
               logFiles.Senddata(TAG+"-->Batteryfull");

                Batterymode.setText(Batterystatus.toString());
                Batterylevel.setText(String.valueOf(batteryLevel).concat("%"));

            }
            if(deviceStatus == BatteryManager.BATTERY_STATUS_UNKNOWN){

                Batterystatus="---";
                Batterymode.setText(Batterystatus.toString());
                Batterylevel.setText(String.valueOf(batteryLevel).concat("%"));
            }

            if (deviceStatus == BatteryManager.BATTERY_STATUS_NOT_CHARGING){
                Batterystatus="NOT CHARGING";
                Batterymode.setText(Batterystatus.toString());
                Batterylevel.setText(String.valueOf(batteryLevel).concat("%"));
            }

        }
    };



}
