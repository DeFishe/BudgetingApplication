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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity { // HOME CLASS
    private Button editBudgetButton;
    private Button budgetDetailsButton;

    public static final String EXTRA_MESSAGE = "com.example.budgetingapplication.secondinstance";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_1);

        budgetDetailsButton = findViewById(R.id.budgetDetailsButton);
        budgetDetailsButton.setOnClickListener(new View.OnClickListener() { // Fixme: Is the indentation on this wonky or just me?
                        @Override
                        public void onClick(View v) {
                             openViewBudget();
                         }
                  });

        editBudgetButton = findViewById(R.id.editBudgetButton);
        editBudgetButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openMainCreateBudgetActivity();
                    }
                });
        Charts index_chart = new Charts(1, this); // 1 = Small Chart
    }


    public void openMainCreateBudgetActivity() {
        Intent intent = new Intent(this, NewBudgetActivity.class);
        startActivity(intent);
    }



    public void openViewBudget() {
        Intent intent = new Intent(this, ViewBudget.class);
        startActivity(intent);
    }

    // Required things: a file stream of some sort, depends on the function we're in at the time.
    private static final String FILE_NAME = "example.txt";
    EditText mEditText;

}
