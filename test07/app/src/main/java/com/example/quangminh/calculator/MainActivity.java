package com.example.quangminh.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Stack;
import java.util.Vector;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView txtOutLower,txtOutUpper;
    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9, btnAC, btnDel, btnAdd, btnMinus, btnMultiply, btnDivide, btnPercent, btnDot, btnEqual, btnSign;
    String upperBuffer="";
    String lowerBuffer="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtOutLower = findViewById(R.id.txtOutLower);
        txtOutUpper = findViewById(R.id.txtOutUpper);
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
        btnSign = findViewById(R.id.btnSign);
        btnMultiply =findViewById(R.id.btnMultiply);
        btnAC = findViewById(R.id.btnAC);
        btnDel = findViewById(R.id.btnDel);
        btnPercent = findViewById(R.id.btnPercent);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperBuffer+="0";
                txtOutUpper.setText(screenBuff());
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperBuffer+="1";
                txtOutUpper.setText(screenBuff());
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperBuffer+="2";
                txtOutUpper.setText(screenBuff());
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperBuffer+="3";
                txtOutUpper.setText(screenBuff());
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperBuffer+="4";
                txtOutUpper.setText(screenBuff());
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperBuffer+="5";
                txtOutUpper.setText(screenBuff());
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperBuffer+="6";
                txtOutUpper.setText(screenBuff());
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperBuffer+="7";
                txtOutUpper.setText(screenBuff());
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperBuffer+="8";
                txtOutUpper.setText(screenBuff());
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperBuffer+="9";
                txtOutUpper.setText(screenBuff());
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperBuffer+="+";
                txtOutUpper.setText(screenBuff());
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperBuffer+="-";
                txtOutUpper.setText(screenBuff());
            }
        });
        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperBuffer+="/";
                txtOutUpper.setText(screenBuff());
            }
        });
        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperBuffer+="*";
                txtOutUpper.setText(screenBuff());
            }
        });
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperBuffer+=".";
                txtOutUpper.setText(screenBuff());
            }
        });
        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperBuffer="";
                lowerBuffer="";
                txtOutLower.setText(screenBuff());
                txtOutUpper.setText(resultBuff());
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperBuffer=upperBuffer.substring(0,upperBuffer.length()-1);
                txtOutUpper.setText(screenBuff());
            }
        });
        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperBuffer+="%";
                txtOutUpper.setText(screenBuff());
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lowerBuffer = Integer.toString(stackResolve());
                //txtOutLower.setText(resultBuff());
            }
        });
    }


    @Override
    public void onClick(View v) {

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
    public int stackResolve(){
        Stack operator = new Stack();
        Stack operands = new Stack();
        Vector postfix = new Vector();
        Vector form = new Vector();
        int operand = 0;
        for (int i =0; i<upperBuffer.length();i++){
            if (isDigit(upperBuffer.charAt(i))){
                operand = operand*10 + upperBuffer.charAt(i)-'0';
            }
            else {
                form.add(operand);
                form.add(upperBuffer.charAt(i));
                operand = 0;
            }
        }

        while (!form.isEmpty()){
            if (form.firstElement() instanceof  Integer){
                postfix.add(form.firstElement());
                form.removeElementAt(0);
            }
            else if (operator.isEmpty()) operator.push(form.firstElement());
            else if (form.firstElement().toString().equals("*")|form.firstElement().toString().equals("/")){
                operator.push(form.firstElement());
            }
            else {
                String temp = operator.pop().toString();
                while (temp.equals("*")|temp.equals("/")) {
                    postfix.add(temp);
                    temp = operator.pop().toString();
                }
            }
        }
        String res = "";
        for ()
        while (!operator.isEmpty()) postfix.add(operator.pop());
        while (!postfix.isEmpty()){
            if (postfix.firstElement() instanceof Integer) operands.push(postfix.firstElement());
            else {
                int t = 0;
                switch (postfix.firstElement().toString()){
                    case "+": t= Integer.parseInt(operands.pop().toString()) + Integer.parseInt(operands.pop().toString());
                        break;
                    case "-": t= -1*Integer.parseInt(operands.pop().toString()) + Integer.parseInt(operands.pop().toString());
                        break;
                    case "*": t= Integer.parseInt(operands.pop().toString()) * Integer.parseInt(operands.pop().toString());
                        break;
                    case "/": t= 1/(Integer.parseInt(operands.pop().toString())) * Integer.parseInt(operands.pop().toString());
                        break;
                }
                operands.push(t);
            }
            postfix.removeElementAt(0);
        }
        return 0;
        //return Integer.parseInt(operands.firstElement().toString());
    }
    public Boolean isDigit(char temp){
        if (temp>='0' && temp<='9') return Boolean.TRUE;
        return Boolean.FALSE;
    }

}
