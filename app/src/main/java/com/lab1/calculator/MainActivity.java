package com.lab1.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private boolean isFirstPoint = true;
    private int parenthesesNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        } else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }

    }

    private void secondSymbolCheck(String strToAdd) {
        int cursorPos = display.getSelectionStart();
        String oldStr = String.valueOf(display.getText());
        char[] oldChar = oldStr.toCharArray();
        if (cursorPos != 0) {
            if (oldChar[cursorPos - 1] == '0' || oldChar[cursorPos - 1] == '1' || oldChar[cursorPos - 1] == '2' || oldChar[cursorPos - 1] == '3' ||
                    oldChar[cursorPos - 1] == '4' || oldChar[cursorPos - 1] == '5' || oldChar[cursorPos - 1] == '6' || oldChar[cursorPos - 1] == '7' ||
                    oldChar[cursorPos - 1] == '8' || oldChar[cursorPos - 1] == '9' || oldChar[cursorPos - 1] == ')'){
                updateText(strToAdd);
                isFirstPoint = true;
            }
        }
    }

    public void zeroBTN(View view) {
        updateText("0");

    }

    public void oneBTN(View view) {
        updateText("1");

    }

    public void twoBTN(View view) {

        updateText("2");
    }

    public void threeBTN(View view) {
        updateText("3");

    }

    public void fourBTN(View view) {
        updateText("4");

    }

    public void fiveBTN(View view) {
        updateText("5");

    }

    public void sixBTN(View view) {

        updateText("6");
    }

    public void sevenBTN(View view) {
        updateText("7");

    }

    public void eightBTN(View view) {
        updateText("8");

    }

    public void nineBTN(View view) {

        updateText("9");
    }


    public void clearBTN(View view) {

        display.setText("");
    }

    public void exponentBTN(View view) {
        secondSymbolCheck("^");

    }

    public void parenthesesBTN(View view) {
        int cursorPos = display.getSelectionStart();
        String oldStr = String.valueOf(display.getText());
        char[] oldChar = oldStr.toCharArray();
        if (cursorPos != 0) {
            if (oldChar[cursorPos - 1] == '0' || oldChar[cursorPos - 1] == '1' || oldChar[cursorPos - 1] == '2' || oldChar[cursorPos - 1] == '3' ||
                    oldChar[cursorPos - 1] == '4' || oldChar[cursorPos - 1] == '5' || oldChar[cursorPos - 1] == '6' || oldChar[cursorPos - 1] == '7' ||
                    oldChar[cursorPos - 1] == '8' || oldChar[cursorPos - 1] == '9' || oldChar[cursorPos - 1] == ')'){
                if (parenthesesNum>0) {
                    parenthesesNum--;
                    updateText(")");
                }
            }
            if (oldChar[cursorPos - 1] == '+' || oldChar[cursorPos - 1] == '-' || oldChar[cursorPos - 1] == '÷' || oldChar[cursorPos - 1] == '×' ||
                    oldChar[cursorPos - 1] == '^' || oldChar[cursorPos - 1] == '(' ){
                parenthesesNum++;
                updateText("(");
            }
        } else {
            parenthesesNum++;
            updateText("(");
        }
    }


    public void divideBTN(View view) {
        secondSymbolCheck("÷");
    }

    public void multiplyBTN(View view) {
        secondSymbolCheck("×");
    }

    public void addBTN(View view) {
        secondSymbolCheck("+");
    }

    public void subtractBTN(View view) {
        secondSymbolCheck("-");
    }

    public void plusMinusBTN(View view) {
        secondSymbolCheck("+/-");
    }

    public void pointBTN(View view) {
        if (isFirstPoint == true) {
            secondSymbolCheck(".");
            isFirstPoint = false;
        }
    }

    public void equalsBTN(View view) {
        String userExp = display.getText().toString();
        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");
        Expression exp = new Expression(userExp);
        String result = String.valueOf(exp.calculate());
        display.setText(result);
        display.setSelection(result.length());

    }

    public void backspaceBTN(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        if (cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }

    }


}