package com.example.quangminh.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MQTTHelper mqttHelper;
    TextView topi,data;
    EditText phone;
    Button send;
    int mqtt_id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topi = findViewById(R.id.topic);
        data = findViewById(R.id.data);
        phone = findViewById(R.id.phone);
        send = findViewById(R.id.send);
        send.setOnClickListener(this);
        startMQTT();

    }
    private void sendDataToMQTT(String ID, String value){

        MqttMessage msg = new MqttMessage(); msg.setId(1234);
        msg.setQos(0); msg.setRetained(true);

        String data = value;

        byte[] b = data.getBytes(Charset.forName("UTF-8")); msg.setPayload(b);

        try {
            mqttHelper.mqttAndroidClient.publish("quangmnh/feeds/iot-ex-202", msg);

        }catch (MqttException e){
        }
    }
    private void startMQTT(){
        mqttHelper = new MQTTHelper(getApplicationContext());
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {

            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                Log.d("MQTT", mqttMessage.toString());
                String temp = "Topic: " + topic;
                topi.setText(temp);
                temp = "Data: " + mqttMessage.toString();
                data.setText(temp);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.send){
            int aux = 0;
            if (phone.getText().toString().length() == 0) aux = 0;
            else aux = Integer.parseInt(phone.getText().toString());
            mqtt_id++;
            sendDataToMQTT("", Integer.toString(aux));
        }
    }
}
