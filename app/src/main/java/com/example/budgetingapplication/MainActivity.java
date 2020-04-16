/********************************************
 Program:  BudgetingApplication a.k.a SmolBudget
 Purpose: To create a simple budgeting application for young adults who want to start budgeting small.
 Author:  Darian Fisher, Jesse Thomas, Logan Jones
 Date: Mar 31,2020
 ********************************************/

/********************************************
 Name: class MainActivity
 Purpose:  The starting point for our application.
 Notes:  NA
 ********************************************/


package com.example.budgetingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity { // HOME CLASS

    public static final String EXTRA_MESSAGE = "com.example.budgetingapplication.secondinstance";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_1);
    }


    public void JesseActivity(View view) {
        Intent intent = new Intent(this, jesse.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, "fixme");
        startActivity(intent);
    }
}
