package com.example.quangminh.myapplication;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.TextView;

import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialPort;
import com.hoho.android.usbserial.driver.UsbSerialProber;
import com.hoho.android.usbserial.util.SerialInputOutputManager;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.concurrent.Executors;

import static com.example.quangminh.myapplication.MainActivity.STATE.ENDING;
import static com.example.quangminh.myapplication.MainActivity.STATE.READING;
import static com.example.quangminh.myapplication.MainActivity.STATE.READING2;
import static com.example.quangminh.myapplication.MainActivity.STATE.READY;

public class MainActivity extends Activity implements SerialInputOutputManager.Listener, View.OnClickListener {
    public enum STATE { READY, READING, READING2, ENDING }
    STATE state = READY;
    final String TAG = "MAIN_TAG";
    float t,h;
    private static final String ACTION_USB_PERMISSION = "com.android.recipes.USB_PERMISSION";
    private static final String INTENT_ACTION_GRANT_USB = BuildConfig.APPLICATION_ID + ".GRANT_USB";
    TextView temper,humid,aux;
    Button btn0,btn1;

    UsbSerialPort port;
    private void sendDataThingSpeak (float temp1, float temp2) {
        temper.setText(Float.toString(temp1));
        humid.setText(Float.toString(temp2));
        String url = "https://api.thingspeak.com/update?api_key=GE2XB4XEFVYEUWFE&field1=" + temp1 + "&field2=" + temp2;
        OkHttpClient client = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.d("ABC", "Request is fail");
            }

            @Override
            public void onResponse(Response response) throws IOException {

            }
        });
    }
    private void openUART(){
        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);

        if (availableDrivers.isEmpty()) {
            Log.d(TAG, "UART is not available");

        }else {
            Log.d(TAG, "UART is available");

            UsbSerialDriver driver = availableDrivers.get(0);
            UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
            if (connection == null) {

                PendingIntent usbPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(INTENT_ACTION_GRANT_USB), 0);
                manager.requestPermission(driver.getDevice(), usbPermissionIntent);

                manager.requestPermission(driver.getDevice(), PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0));


                return;
            } else {

                port = driver.getPorts().get(0);
                try {
                    port.open(connection);
                    port.setParameters(115200, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);

                    SerialInputOutputManager usbIoManager = new SerialInputOutputManager(port, this);
                    Executors.newSingleThreadExecutor().submit(usbIoManager);
                    Log.d(TAG, "UART is openned");

                } catch (Exception e) {
                    Log.d(TAG, "There is error");
                }
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn0 = findViewById(R.id.btn1);
        btn1 = findViewById(R.id.btn2);
        btn0.setOnClickListener(this);
        temper = findViewById(R.id.temper);
        humid = findViewById(R.id.humid);
        aux = findViewById(R.id.aux);
    }


    String buffer  = "";
    @Override
    public void onNewData(byte[] data) {
        aux.setText("received");
        switch (state){
            case READY:
                if (data.length == 1 && data[0] == (byte)0xab)
                    state = READING;
                else state = READY;
                break;
            case READING:
                if (data.length == 4) {
                    t = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN).getFloat();
                    state = READING2;
                }
                if (data.length==1) state =READY;
                break;
            case READING2:
                if (data.length == 4) {
                    h = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN).getFloat();
                    state = ENDING;
                }
                if (data.length==1) state =READY;
                break;
            case ENDING:
                sendDataThingSpeak(t,h);
                state = READY;
                break;
        }

    }

    @Override
    public void onRunError(Exception e) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn1) sendDataThingSpeak(15,20);
    }
}
