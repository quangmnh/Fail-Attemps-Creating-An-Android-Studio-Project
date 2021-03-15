package com.example.quangminh.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView txtResult;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResult = findViewById(R.id.txtOut);
        btn1 = findViewById(R.id.btn1);
        txtResult.setText("ABC");
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                txtResult.setText("1");
            }

        });
    }

    @Override
    public void onClick(View v) {

    }
}
