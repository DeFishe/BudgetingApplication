/*
Author: Jesse Thomas (jesse.thomas@snhu.edu)
Name: ViewBudget.java
Purpose: This class handles the activity_jesse.xml - it handles displaying and taking care of the user
Notes: N/A
*/

package com.example.budgetingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class ViewBudget extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jesse);

        // Switch Button
        Switch view = (Switch) findViewById(R.id.vBudget_graphType);

        ChangeChart(3);

        view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) { // Listener for the Switch on the activity_jesse.xmlreall
                if (isChecked) { // Piechart
                    Toast.makeText(ViewBudget.this, "Settings: Pie Chart", Toast.LENGTH_LONG).show();
                    ChangeChart(0);
                } else { // BarGraph
                    Toast.makeText(ViewBudget.this, "Settings: Bar Graph", Toast.LENGTH_LONG).show();
                    ChangeChart(3);
                }
            }
        });
    }

    // Responsible for changing Chart Type
    private void ChangeChart(int chartType) {

        Charts charts = new Charts(chartType, this);

        //if(chartType == 0) {
        //    findViewById(R.id.display_bar_chart).setAlpha(0);
        //}
        //else if (chartType == 3) {
        //    findViewById(R.id.display_pie_chart).setAlpha(0);
        //}

        // Variable for EditText module on ViewBudget Page
        EditText textBox = (EditText) findViewById(R.id.vBudgetInformation);

        // Acquires a static variable from the chart class to capture total income
        float savings = charts.combined_income;

        // Subtraction in a loop
        for(int i = 1; i < charts.chartNumbers.length; i++)
            savings -= charts.chartNumbers[i];

        // Sets bottom EditText styling and settings
        textBox.setTextSize(20.0f);
        textBox.setTextColor(Color.GRAY);
        textBox.setEnabled(false);

        // Changes text based on different levels of user savings
        if(savings >= 0) // Surplus budget
        {
            String message = getApplicationContext().getString(R.string.vBudgetInfo, savings); // Positive Budget
            textBox.setText(message);
        }else if (savings < 0) { // Deficit
            String message = getApplicationContext().getString(R.string.vBudgetInfodef, savings); // Negative Budget
            textBox.setText(message);
        }else{
            String message = getApplicationContext().getString(R.string.vBudgetInfonone); // If all 0's
            textBox.setText(message);
        }
    }
}