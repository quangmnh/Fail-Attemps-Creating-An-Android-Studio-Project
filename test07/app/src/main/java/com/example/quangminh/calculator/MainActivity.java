package com.example.quangminh.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import static com.example.quangminh.calculator.MainActivity.STATE.FIRST_OP;
import static com.example.quangminh.calculator.MainActivity.STATE.RESET_SECOND_OP;
import static com.example.quangminh.calculator.MainActivity.STATE.SECOND_OP;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public enum STATE { FIRST_OP, RESET_SECOND_OP, SECOND_OP }
    STATE state = FIRST_OP;
    double first_op = 0, second_op = 0; String operator = "";
    TextView txtOutLower,txtOutUpper;
    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9, btnAC, btnDel, btnAdd, btnMinus, btnMultiply, btnDivide, btnPercent, btnDot, btnEqual, btnSign;
    String upperBuffer="";
    String lowerBuffer="";
    String tempstring ="";
    double percent = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtOutLower = findViewById(R.id.txtOutLower);
        txtOutUpper = findViewById(R.id.txtOutUpper);
        final Animation animation; // Change alpha from fully visible to invisible
        animation = new AlphaAnimation(1, 0);
        animation.setDuration(300); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnAdd = findViewById(R.id.btnAdd);
        btnMinus = findViewById(R.id.btnMinus);
        btnDivide = findViewById(R.id.btnDivide);
        btnEqual = findViewById(R.id.btnEqual);
        btnDot = findViewById(R.id.btnDot);
        btnMultiply =findViewById(R.id.btnMultiply);
        btnAC = findViewById(R.id.btnAC);
        btnDel = findViewById(R.id.btnDel);
        btnPercent = findViewById(R.id.btnPercent);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtOutLower.setText(screenBuff());
                txtOutUpper.setText(resultBuff());
                state = FIRST_OP;
            }
        });
        btnDel.setOnClickListener(this);
        btnPercent.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (state) {
            case FIRST_OP:
                if (v.getId() == R.id.btn0 || v.getId() == R.id.btn1 ||
                        v.getId() == R.id.btn2 || v.getId() == R.id.btn3 ||
                        v.getId() == R.id.btn4 || v.getId() == R.id.btn5 ||
                        v.getId() == R.id.btn6 || v.getId() == R.id.btn7 ||
                        v.getId() == R.id.btn8 || v.getId() == R.id.btn9 ||
                        v.getId() == R.id.btnDot ||v.getId() == R.id.btnPercent) {
                    state = FIRST_OP;
                    String displayNumber = ((Button)v).getText().toString();
                    if(txtOutUpper.getText().toString().equals("0") == true){
                        txtOutUpper.setText(displayNumber);
                    }

                    else{
                        txtOutUpper.setText(txtOutUpper.getText() + displayNumber);
                    }
                    if (v.getId() == R.id.btnPercent){
                        percent = 0.01;
                    }
                }
                if (v.getId() == R.id.btnDel){
                    tempstring = txtOutUpper.getText().toString();
                    if (tempstring.length()>0) {
                        tempstring = tempstring.substring(0, tempstring.length() - 1);
                        txtOutUpper.setText(tempstring);
                        state = FIRST_OP;
                    }
                }

                if (v.getId() == R.id.btnAdd) {
                    first_op = Double.parseDouble(txtOutUpper.getText().toString().replace("%",""))*percent;
                    percent = 1;
                    operator = "ADD";
                    txtOutUpper.setText(txtOutUpper.getText() + "+");
                    state = RESET_SECOND_OP;
                }
                if (v.getId() == R.id.btnMinus) {
                    first_op = Double.parseDouble(txtOutUpper.getText().toString().replace("%",""))*percent;
                    percent = 1;
                    operator = "MINUS";
                    txtOutUpper.setText(txtOutUpper.getText() + "-");
                    state = RESET_SECOND_OP;
                }
                if (v.getId() == R.id.btnMultiply) {
                    first_op = Double.parseDouble(txtOutUpper.getText().toString().replace("%",""))*percent;
                    percent = 1;
                    operator = "MULTIPLY";
                    txtOutUpper.setText(txtOutUpper.getText() + "*");
                    state = RESET_SECOND_OP;
                }
                if (v.getId() == R.id.btnDivide) {
                    first_op = Double.parseDouble(txtOutUpper.getText().toString().replace("%",""))*percent;
                    percent = 1;
                    operator = "DIVIDE";
                    txtOutUpper.setText(txtOutUpper.getText() + "/");
                    state = RESET_SECOND_OP;
                }
                break;
            case RESET_SECOND_OP:
                if (v.getId() == R.id.btn0 || v.getId() == R.id.btn1 ||
                        v.getId() == R.id.btn2 || v.getId() == R.id.btn3 ||
                        v.getId() == R.id.btn4 || v.getId() == R.id.btn5 ||
                        v.getId() == R.id.btn6 || v.getId() == R.id.btn7 ||
                        v.getId() == R.id.btn8 || v.getId() == R.id.btn9||
                        v.getId() == R.id.btnDot ){
                    String displayNumber = ((Button)v).getText().toString();
                    txtOutUpper.setText(displayNumber);
                    state = SECOND_OP;
                }
                if (v.getId() == R.id.btnDel){
                    tempstring = txtOutUpper.getText().toString();
                    if (tempstring.length()>0) {
                        tempstring = tempstring.substring(0, tempstring.length() - 1);
                        txtOutUpper.setText(tempstring);
                        state = FIRST_OP;
                    }
                }
                break;
            case SECOND_OP:
                if (v.getId() == R.id.btn0 || v.getId() == R.id.btn1 ||
                        v.getId() == R.id.btn2 || v.getId() == R.id.btn3 ||
                        v.getId() == R.id.btn4 || v.getId() == R.id.btn5 ||
                        v.getId() == R.id.btn6 || v.getId() == R.id.btn7 ||
                        v.getId() == R.id.btn8 || v.getId() == R.id.btn9||
                        v.getId() == R.id.btnDot || v.getId() == R.id.btnPercent){
                    String displayNumber = ((Button)v).getText().toString();
                    txtOutUpper.setText(txtOutUpper.getText() + displayNumber);
                    if (v.getId() == R.id.btnPercent){
                        percent = 0.01;
                    }
                }
                if (v.getId() == R.id.btnDel){
                    tempstring = txtOutUpper.getText().toString();
                    if (tempstring.length()>0) {
                        tempstring = tempstring.substring(0, tempstring.length() - 1);
                        txtOutUpper.setText(tempstring);
                        state = SECOND_OP;
                    }
                }
                if(v.getId() == R.id.btnAdd || v.getId() == R.id.btnMinus ||
                        v.getId() == R.id.btnMultiply || v.getId() == R.id.btnDivide){
                    second_op = Double.parseDouble(txtOutUpper.getText().toString().replace("%",""))*percent;
                    percent = 1;
                    first_op = res(operator,first_op,second_op);
                    state = RESET_SECOND_OP;
                }
                if(v.getId() == R.id.btnEqual){
                    second_op = Double.parseDouble(txtOutUpper.getText().toString().replace("%",""))*percent;
                    percent = 1;
                    res(operator,first_op,second_op);
                    state = FIRST_OP;
                }
                break;
        }
    }
    public double res(String op, Double first_op, Double second_op){
        switch (op){
            case "ADD":
                first_op += second_op;
                break;
            case "MINUS":
                first_op -= second_op;
                break;
            case "MULTIPLY":
                first_op *= second_op;
                break;
            case "DIVIDE":
                first_op /= second_op;
                break;
        }
        txtOutUpper.setText("");
        txtOutLower.setText(Double.toString(first_op));
        return first_op;
    }
    public String screenBuff(){
        if (upperBuffer.length()>13){
            return "..." + (upperBuffer.substring(upperBuffer.length()-13+3));
        }
        return upperBuffer;
    }
    public String resultBuff(){
        if (lowerBuffer.length()>13){
            return (lowerBuffer.substring(0,10))+"...";
        }
        return lowerBuffer;
    }

}
