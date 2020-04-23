package com.example.budgetingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ViewBudget extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jesse);

        Charts newPieChart = new Charts(-1, this);

        // Variable for EditText module on ViewBudget Page
        EditText textBox = (EditText) findViewById(R.id.vBudgetInformation);

        // Acquires a static variable from the chart class to capture total income
        float savings = newPieChart.combined_income;

        // Subtraction in a loop
        for(int i = 1; i < newPieChart.chartNumbers.length; i++)
            savings -= newPieChart.chartNumbers[i];

        // Sets bottom EditText styling and settings
        textBox.setTextSize(20.0f);
        textBox.setTextColor(Color.GRAY);
        textBox.setEnabled(false);

        if(savings >= 0) // Surplus budget
        {
            String message = getApplicationContext().getString(R.string.vBudgetInfo, savings); // Positive Budget
            textBox.setText(message);
        }else if (savings < 0) { // Deficit
            String message = getApplicationContext().getString(R.string.vBudgetInfodef, savings); // Negative Budget
            textBox.setText(message);
        }else {
            String message = getApplicationContext().getString(R.string.vBudgetInfonone); // If all 0's
            textBox.setText(message);
        }
    }
}