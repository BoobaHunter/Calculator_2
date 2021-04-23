package com.example.calculator_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculator_2.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import android.view.Menu;

import android.content.ClipData;
import android.content.ClipboardManager;

public class MainActivity extends AppCompatActivity {
    Button mbtn1;
    Button mbtn2;
    Button mbtn3;
    Button mbtn4;
    Button mbtn5;
    Button mbtn6;
    Button mbtn7;
    Button mbtn8;
    Button mbtn9;
    Button mbtn0;

    TextView mText;

    Button mbtnplus;
    Button mbtnminus;
    Button mbtnumn;
    Button mbtndel;
    Button mbtnravno;

    Button mbtnclear;
    Button mbtnback;
    Button mbtndot;
    Button mbtnsmena;

    float mValue = 0;
    String mOperator = "";

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_settings:
                startSettings();
                return true;
            case R.id.about:
                about();
                return true;
            case R.id.copy:
                copy();
                return true;
            case R.id.paste:
                paste();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startSettings() {
        Intent activityIntent = new Intent(getApplicationContext(), settings.class);
        startActivity(activityIntent);
    }

    private void about() {
        Intent activityIntent = new Intent(getApplicationContext(), about.class);
        startActivity(activityIntent);
    }

    private void copy() {
        ClipboardManager clipboard = (ClipboardManager)
                getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard != null){
            ClipData clip = ClipData.newPlainText("", mText.getText());
            clipboard.setPrimaryClip(clip);
        }
    }

    private void paste() {
        ClipboardManager clipboard = (ClipboardManager)
                getSystemService(Context.CLIPBOARD_SERVICE);

        if (clipboard != null){
            if (clipboard.hasPrimaryClip() && clipboard.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)){
                ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                String pasteData = item.getText().toString();
                if (isNumeric(pasteData))
                    mText.setText(pasteData);
            }
        }

    }

    public static boolean isNumeric(String text){
        if (text == null)
            return false;
        try{
            Double.parseDouble(text);
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.calculator_2.ThemeManager.setTheme(this);
        setContentView(R.layout.activity_main);


        mbtn1 = findViewById(R.id.btn1);
        mbtn2 = findViewById(R.id.btn2);
        mbtn3 = findViewById(R.id.btn3);
        mbtn4 = findViewById(R.id.btn4);
        mbtn5 = findViewById(R.id.btn5);
        mbtn6 = findViewById(R.id.btn6);
        mbtn7 = findViewById(R.id.btn7);
        mbtn8 = findViewById(R.id.btn8);
        mbtn9 = findViewById(R.id.btn9);
        mbtn0 = findViewById(R.id.btn0);

        mText = findViewById(R.id.Text);

        mbtnplus = findViewById(R.id.btnplus);
        mbtnminus = findViewById(R.id.btnminus);
        mbtnumn = findViewById(R.id.btnumn);
        mbtndel = findViewById(R.id.btndel);
        mbtnravno = findViewById(R.id.btnravno);

        mbtnclear = findViewById(R.id.btnclear);
        mbtnback = findViewById(R.id.btnback);
        mbtndot = findViewById(R.id.btndot);
        mbtnsmena = findViewById(R.id.btnsmena);

        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }


            private void onNumberClick(View button) {
                String number = ((Button) button).getText().toString();
                String display = mText.getText().toString();

                if (display.equals("0"))
                    display = number;
                else
                    display += number;

                mText.setText(display);
            }
        };
        mbtn1.setOnClickListener(numberListener);
        mbtn2.setOnClickListener(numberListener);
        mbtn3.setOnClickListener(numberListener);
        mbtn4.setOnClickListener(numberListener);
        mbtn5.setOnClickListener(numberListener);
        mbtn6.setOnClickListener(numberListener);
        mbtn7.setOnClickListener(numberListener);
        mbtn8.setOnClickListener(numberListener);
        mbtn9.setOnClickListener(numberListener);
        mbtn0.setOnClickListener(numberListener);


        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorListener(v);
            }

            private void onOperatorListener(View button) {
                String operator = ((Button) button).getText().toString();
                mOperator = operator;

                String display = mText.getText().toString();
                mValue = Float.parseFloat(display);

                mText.setText("0");
            }
        };
        mbtnplus.setOnClickListener(operatorListener);
        mbtnminus.setOnClickListener(operatorListener);
        mbtnumn.setOnClickListener(operatorListener);
        mbtndel.setOnClickListener(operatorListener);


        View.OnClickListener ravnoListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResultListener(v);
            }

            private void onResultListener(View button) {
                String display = mText.getText().toString();
                float value = Float.parseFloat(display);

                float result = value;

                switch (mOperator) {
                    case "+": {
                        result = value + mValue;
                        break;
                    }

                    case "-": {
                        result = mValue - value;
                        break;
                    }

                    case "/": {
                        if (value !=0) {
                            result = mValue / value;
                            break;
                        }
                    }

                    case "*": {
                        result = value * mValue;
                        break;
                    }
                }

                DecimalFormat format = new DecimalFormat("0.#######");
                format.setRoundingMode(RoundingMode.DOWN);
                String resultText = format.format(result);

                mText.setText(resultText);

                mValue = result;
                mOperator = "";
            }

        };

        mbtnravno.setOnClickListener(ravnoListener);


        View.OnClickListener clearListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearListener(v);
            }

            private void onClearListener(View button) {
                mText.setText("0");
            }
        };
        mbtnclear.setOnClickListener(clearListener);

        View.OnClickListener backListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackListener(v);
            }

            private void onBackListener(View v) {

                String display = mText.getText().toString();
                if (display.length()!=0)
                    mText.setText(display.substring(0, display.length() - 1));

            }
        };
        mbtnback.setOnClickListener(backListener);

        View.OnClickListener dotListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDotListener(v);
            }

            private void onDotListener(View v) {

                String display = mText.getText().toString();
                if (display.length()!=0)
                    mText.setText(display + ".");

            }
        };
        mbtndot.setOnClickListener(dotListener);

    }
}