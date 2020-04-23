/********************************************
 Name: class NewBudgetActivity
 Purpose:  Provides activity for budget creation/updating
 Notes:  NA
 ********************************************/


package com.example.budgetingapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.io.File;

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

        //Instantiates file object with expected path of budget.json
        File budgetFile = new File(this.getFilesDir(), fileName);

        //Instantiates budget and updates values from JSON
        budget = new Budget();
        budget = budget.UpdateBudgetFromJSON(budgetFile);

        //Sets all text fields to values of the budget objects properties
        EditText editText;
        editText = findViewById(R.id.primaryIncomeField);
        editText.setHint(String.format("$%.2f", budget.primaryIncome));
        editText = findViewById(R.id.secondaryIncomeField);
        editText.setHint(String.format("$%.2f", budget.secondaryIncome));
        editText = findViewById(R.id.housingField);
        editText.setHint(String.format("$%.2f", budget.housingExpenses));
        editText = findViewById(R.id.utilitesField);
        editText.setHint(String.format("$%.2f", budget.utilitiesExpenses));
        editText = findViewById(R.id.foodField);
        editText.setHint(String.format("$%.2f", budget.foodExpenses));
        editText = findViewById(R.id.transportationField);
        editText.setHint(String.format("$%.2f", budget.transportationExpenses));
        editText = findViewById(R.id.insuranceField);
        editText.setHint(String.format("$%.2f", budget.insuranceExpenses));
        editText = findViewById(R.id.healthCareField);
        editText.setHint(String.format("$%.2f", budget.healthCareExpenses));
        editText = findViewById(R.id.educationField);
        editText.setHint(String.format("$%.2f", budget.educationExpenses));
        editText = findViewById(R.id.entertaimentField);
        editText.setHint(String.format("$%.2f", budget.entertainmentExpenses));
        editText = findViewById(R.id.miscellaneousField);
        editText.setHint(String.format("$%.2f", budget.miscellaneousExpenses));

    }


    //Method that updates budget file when update button is clicked
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
            budget.primaryIncome = Float.parseFloat(editText.getHint().toString().substring(1));
        }
        editText = findViewById(R.id.secondaryIncomeField);
        if (!TextUtils.isEmpty(editText.getText()))
        {
            budget.secondaryIncome = Float.parseFloat(editText.getText().toString());
        }
        else
        {
            budget.secondaryIncome = Float.parseFloat(editText.getHint().toString().substring(1));
        }
        editText = findViewById(R.id.housingField);
        if (!TextUtils.isEmpty(editText.getText()))
        {
            budget.housingExpenses = Float.parseFloat(editText.getText().toString());
        }
        else
        {
            budget.housingExpenses = Float.parseFloat(editText.getHint().toString().substring(1));
        }
        editText = findViewById(R.id.utilitesField);
        if (!TextUtils.isEmpty(editText.getText()))
        {
            budget.utilitiesExpenses = Float.parseFloat(editText.getText().toString());
        }
        else
        {
            budget.utilitiesExpenses = Float.parseFloat(editText.getHint().toString().substring(1));
        }
        editText = findViewById(R.id.foodField);
        if (!TextUtils.isEmpty(editText.getText()))
        {
            budget.foodExpenses = Float.parseFloat(editText.getText().toString());
        }
        else
        {
            budget.foodExpenses = Float.parseFloat(editText.getHint().toString().substring(1));
        }
        editText = findViewById(R.id.transportationField);
        if (!TextUtils.isEmpty(editText.getText()))
        {
            budget.transportationExpenses = Float.parseFloat(editText.getText().toString());
        }
        else
        {
            budget.transportationExpenses = Float.parseFloat(editText.getHint().toString().substring(1));
        }
        editText = findViewById(R.id.insuranceField);
        if (!TextUtils.isEmpty(editText.getText()))
        {
            budget.insuranceExpenses = Float.parseFloat(editText.getText().toString());
        }
        else
        {
            budget.insuranceExpenses = Float.parseFloat(editText.getHint().toString().substring(1));
        }
        editText = findViewById(R.id.healthCareField);
        if (!TextUtils.isEmpty(editText.getText()))
        {
            budget.healthCareExpenses = Float.parseFloat(editText.getText().toString());
        }
        else
        {
            budget.healthCareExpenses = Float.parseFloat(editText.getHint().toString().substring(1));
        }
        editText = findViewById(R.id.educationField);
        if (!TextUtils.isEmpty(editText.getText()))
        {
            budget.educationExpenses = Float.parseFloat(editText.getText().toString());
        }
        else
        {
            budget.educationExpenses = Float.parseFloat(editText.getHint().toString().substring(1));
        }
        editText = findViewById(R.id.entertaimentField);
        if (!TextUtils.isEmpty(editText.getText()))
        {
            budget.entertainmentExpenses = Float.parseFloat(editText.getText().toString());
        }
        else
        {
            budget.entertainmentExpenses = Float.parseFloat(editText.getHint().toString().substring(1));
        }
        editText = findViewById(R.id.miscellaneousField);
        if (!TextUtils.isEmpty(editText.getText()))
        {
            budget.miscellaneousExpenses = Float.parseFloat(editText.getText().toString());
        }
        else
        {
            budget.miscellaneousExpenses = Float.parseFloat(editText.getHint().toString().substring(1));
        }
        
        //Declares/instantiates File object
        File file = new File(this.getFilesDir(), fileName);
        //Saves properties of budget to JSON
        budget.UpdateBudgetToJSON(file, budget);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
