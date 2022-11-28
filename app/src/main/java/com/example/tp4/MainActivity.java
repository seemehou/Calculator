package com.example.tp4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.mariuszgromada.math.mxparser.*;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                }
            }
        });//

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0,cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }
        else {
            display.setText(String.format("%s%s%s",leftStr , strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }

    }
    public void btnZero(View view) {

        updateText("0");
    }
    public void btnOne (View view){

        updateText("1");
    }
    public void btnTwo(View view){
        updateText("2");

    }
    public void btnThree(View view){

        updateText("3");
    }
    public void btnFour(View view){

        updateText("4");
    }
    public void btnFive(View view){

        updateText("5" );
    }
    public void btnSix(View view){

        updateText("6");
    }
    public void btnSeven (View view){

        updateText("7");
    }
    public void btnEight(View view){

        updateText("8");
    }
    public void btnNine(View view){

        updateText("9");
    }
    public void btnEqual(View view){

        String expr = display.getText().toString();

        Expression exp = new Expression(expr);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }
    public void btnMul(View view){

        updateText("×");
    }
    public void btnMinus(View view){

        updateText("-");
    }
    public void btnPoint(View view){

        updateText(".");
    }
    public void btnPercent(View view){

        updateText("%");
    }
    public void btnPlus(View view){

        updateText("+");
    }
    public void btnDiv(View view){

        updateText("÷");
    }
    public void btnPar(View view){
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = display.getText().length();

        for (int i =0 ; i < cursorPos ; i++){
            if (display.getText().toString().substring(i,i+1).equals("(")){
                openPar +=1 ;
            }
            if (display.getText().toString().substring(i,i+1).equals(")")){
                closedPar +=1 ;
            }
        }
        if (openPar == closedPar || display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText("(");
        }
        else if (closedPar < openPar && !display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPos + 1);
    }
    public void btnClear(View view){

        display.setText("");
    }
    public void btnBack(View view){

        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        if ( cursorPos != 0 && textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }
}