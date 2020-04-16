/********************************************
 Name: class NewBudgetActivity
 Purpose:  Provides activity for budget creation/updating
 Notes:  NA
 ********************************************/


package com.example.budgetingapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//Activity method
public class NewBudgetActivity extends AppCompatActivity
{
    //Declares budget for later use
    Budget budget;
    //Name of file to hold budget
    static final String fileName = "budget.json";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //Code related to layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_budget);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Instantiates gson object
        Gson gson = new Gson();
        //Instantiates file object with expected path of budget.json
        File budgetFile = new File(this.getFilesDir(), fileName);
        //Checks to see if budget file exists and it is not empty
        if (budgetFile.exists() && budgetFile.length() != 0)
        {
            try
            {
                //Creates FileReader with budgetFile
                InputStream inputStream = new FileInputStream(budgetFile);
                StringBuilder stringBuilder = new StringBuilder();
                String output = "";
                if (inputStream != null)
                {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String receiveString = "";
                    //Use a while loop to append the lines from the Buffered reader
                    while ((receiveString = bufferedReader.readLine()) != null){
                        stringBuilder.append(receiveString);
                    }
                    inputStream.close();
                    output = stringBuilder.toString();
                    //Converts json to budget object
                    budget = gson.fromJson(output, Budget.class);
                }

            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            try
            {
                //If necessary, creates missing budgetFile for future use
                budgetFile.createNewFile();
                //Uses default constructor to create budget with all fields set to 0
                budget = new Budget();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        //Sets all text fields to values of the budget objects properties
        EditText editText;
        editText = findViewById(R.id.primaryIncomeField);
        editText.setHint(String.valueOf(budget.primaryIncome));
        editText = findViewById(R.id.secondaryIncomeField);
        editText.setHint(String.valueOf(budget.secondaryIncome));
        editText = findViewById(R.id.housingField);
        editText.setHint(String.valueOf(budget.housingExpenses));
        editText = findViewById(R.id.utilitesField);
        editText.setHint(String.valueOf(budget.utilitiesExpenses));
        editText = findViewById(R.id.foodField);
        editText.setHint(String.valueOf(budget.foodExpenses));
        editText = findViewById(R.id.transportationField);
        editText.setHint(String.valueOf(budget.transportationExpenses));
    }

    //Method that updates budget file when update is button is clicked
    public void updateBudget(View view)
    {
        //Instantiates EditText object for later use
        EditText editText;
        //Instantiates budget object and fills with saved user info
        budget = new Budget();
        editText = findViewById(R.id.primaryIncomeField);
        if (!TextUtils.isEmpty(editText.getText()))
        {
            budget.primaryIncome = Float.parseFloat(editText.getText().toString());
        }
        else
        {
            budget.primaryIncome = Float.parseFloat(editText.getHint().toString());
        }
        editText = findViewById(R.id.secondaryIncomeField);
        if (!TextUtils.isEmpty(editText.getText()))
        {
            budget.secondaryIncome = Float.parseFloat(editText.getText().toString());
        }
        else
        {
            budget.secondaryIncome = Float.parseFloat(editText.getHint().toString());
        }
        editText = findViewById(R.id.housingField);
        if (!TextUtils.isEmpty(editText.getText()))
        {
            budget.housingExpenses = Float.parseFloat(editText.getText().toString());
        }
        else
        {
            budget.housingExpenses = Float.parseFloat(editText.getHint().toString());
        }
        editText = findViewById(R.id.utilitesField);
        if (!TextUtils.isEmpty(editText.getText()))
        {
            budget.utilitiesExpenses = Float.parseFloat(editText.getText().toString());
        }
        else
        {
            budget.utilitiesExpenses = Float.parseFloat(editText.getHint().toString());
        }
        editText = findViewById(R.id.foodField);
        if (!TextUtils.isEmpty(editText.getText()))
        {
            budget.foodExpenses = Float.parseFloat(editText.getText().toString());
        }
        else
        {
            budget.foodExpenses = Float.parseFloat(editText.getHint().toString());
        }
        editText = findViewById(R.id.transportationField);
        if (!TextUtils.isEmpty(editText.getText()))
        {
            budget.transportationExpenses = Float.parseFloat(editText.getText().toString());
        }
        else
        {
            budget.transportationExpenses = Float.parseFloat(editText.getHint().toString());
        }


        //Declares/instantiates File object
        File file = new File(this.getFilesDir(), fileName);

        //Declares FileWriter
        FileWriter fileWriter = null;

        //Declares and instantiates FileWriter object
        try
        {
            fileWriter = new FileWriter(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        //Instantiate gson object for converting budget's properties to json.
        Gson gson = new Gson();
        //Converts budget to string in json format
        String budgetToWrite = gson.toJson(budget);
        FileOutputStream fileOutputStream = null;
        try
        {
            fileOutputStream = new FileOutputStream(file);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        //Convert your JSON String to Bytes and write() it
        try
        {
            if (fileOutputStream != null)
            {
                fileOutputStream.write(budgetToWrite.getBytes());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            if (fileOutputStream != null)
            {
                fileOutputStream.flush();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            if (fileOutputStream != null)
            {
                fileOutputStream.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
