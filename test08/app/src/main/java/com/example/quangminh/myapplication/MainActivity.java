package com.example.quangminh.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText txtA, txtB;
    TextView txtC;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtA = findViewById(R.id.txtA);
        txtB = findViewById(R.id.txtB);
        txtC = findViewById(R.id.txtC);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intAdd();
            }
        });
    }
    private void intAdd(){
        int A = Integer.parseInt(txtA.getText().toString());
        int B = Integer.parseInt(txtB.getText().toString());
        int C = A +B;
        txtC.setText(C+"");
}
}
