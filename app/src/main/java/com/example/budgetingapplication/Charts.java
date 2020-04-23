package com.example.budgetingapplication;

import android.app.Activity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Charts {

    // Public Chart Variables
    public Activity activity; // Required to access the XML file from another class (passes their activity)
    private float chartNumbers[] = {100000, 50000, 25000, 12500}; // Makes up slice # and size of each slice
    private String chartLabels[] = {"Label1", "Label2", "Label3", "Label4"}; // Labels for Chart, used in Key or can be abstracted on top of chart
    List<PieEntry> pieEntries = new ArrayList<>(); // Pie Entries (Each Slice)

    public Charts(int ChartType, Activity _activity) { // Negative for LargeChart, Positive for SmallChart()
        this.activity = _activity;

        // Gets .json file for use
        File budgetFile = new File(this.activity.getFilesDir(), fileName);
        budget = new Budget();
        budget = budget.UpdateBudgetFromJSON(budgetFile);

        // Accounts so pie chart is accurate in displaying (does not cover negative values)
        float totalIncome = budget.primaryIncome + budget.secondaryIncome;
        float debits = budget.housingExpenses + budget.transportationExpenses + budget.utilitiesExpenses + budget.foodExpenses;
        float remainingIncome = totalIncome - debits;
        combined_income = totalIncome;

        // Adding to Array<T> for pie chart slices
        chartNumbers = new float[]{
                (remainingIncome),
                (budget.housingExpenses),
                (budget.transportationExpenses),
                (budget.utilitiesExpenses),
                (budget.foodExpenses),
                (budget.insuranceExpenses),
                (budget.healthCareExpenses),
                (budget.educationExpenses),
                (budget.entertainmentExpenses),
                (budget.miscellaneousExpenses)
                };
        // Adding to Array<T> for Pie chart Labels
        chartLabels = new String[]{
                ("Savings"),
                ("Housing"),
                ("Transport"),
                ("Utilities"),
                ("Food"),
                ("Insurance"),
                ("Health Care"),
                ("Education"),
                ("Entertainment"),
                ("Misc")
        };

        for(int i = 0; i < chartNumbers.length; i++) {
            if(chartNumbers[i] == 0) {
                chartLabels[i] = null;
            }
        }

        // Regardless of what setting a class may instantiate this class from - it will run a Deficit Pie Chart if savings < 0
        if(remainingIncome < 0) {
            ChartType = 0;
        }
        // Chart Type
        if (ChartType < 0)
            LargeChart();
        else
            SmallChart();
    }

    // Returns formatted Small Chart
    public void LargeChart() {

        for(int i = 0; i < chartNumbers.length; i++) {

            if (chartNumbers[i] != 0) {
                pieEntries.add(new PieEntry(chartNumbers[i], chartLabels[i]));
            } // If it's zero ignore
        } // Loops through length of # entries and adds them as a new PieEntry (Part of MPAndroidChart)

        // Pie Chart Variables - You likely need not touch these
        PieDataSet dataSet = new PieDataSet(pieEntries, "Your Budget!"); // PieDataSet a part of MPAndroidChart
        PieChart chart = (PieChart) this.activity.findViewById(R.id.display_pie_chart);
        Legend legend = chart.getLegend();

        // Pie Data Set
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS); // Makes the Chart Colorful
        dataSet.setValueTextSize(20.0f); // Text Size of numbers in chart and data entries
        PieData data = new PieData(dataSet); // A part of MPAndroidChart

        // Legend Information and Tweaking
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setYOffset(50.0f);

        // Chart Information and Tweaking
        chart.setData(data);
        chart.setExtraBottomOffset(10.0f);
        chart.setExtraTopOffset(10.0f);
        chart.setDrawEntryLabels(false);
        chart.getDescription().setText("Your Budget");
        chart.getDescription().setTextSize(15.0f);
        chart.animateY(1000);
        chart.invalidate();
    }

    // Returns formatted Large Chart
    public void SmallChart() {

        for(int i = 0; i < chartNumbers.length; i++) {

            if (chartNumbers[i] != 0) {
                pieEntries.add(new PieEntry(chartNumbers[i], chartLabels[i]));
            } // If it's zero ignore
        } // Loops through length of # entries and adds them as a new PieEntry (Part of MPAndroidChart)

        // Pie Chart Variables - You likely need not touch these
        PieDataSet dataSet = new PieDataSet(pieEntries, "Your Budget!"); // PieDataSet a part of MPAndroidChart
        PieChart chart = (PieChart) this.activity.findViewById(R.id.display_pie_chart);
        Legend legend = chart.getLegend();

        // Pie Data Set
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS); // Makes the Chart Colorful
        dataSet.setValueTextSize(0.0f); // Text Size of numbers in chart and data entries
        PieData data = new PieData(dataSet); // A part of MPAndroidChart

        // Legend Information and Tweaking
        legend.setEnabled(false);

        // Chart Information and Tweaking
        chart.setData(data);
        chart.setEntryLabelColor(ColorTemplate.colorWithAlpha(ColorTemplate.VORDIPLOM_COLORS[1], 200));
        chart.getDescription().setEnabled(false);
        chart.animateY(750);
        chart.invalidate(); // Refreshes / Draws Chart
    }
}
