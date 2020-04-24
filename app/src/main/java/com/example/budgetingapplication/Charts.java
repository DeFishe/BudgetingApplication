/*
Author: Jesse Thomas (jesse.thomas@snhu.edu)
Name: Charts.java
Purpose: This class handles the information regarding PieCharts, it offers three types. Large, Small, Deficit, and Bar
Notes: If you wish to call a large pie chart please use a negative number, for small use positive.
        The code will overwrite whatever you put automatically if it detects a deficit. Refer 'this' as the second argument
*/

package com.example.budgetingapplication;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Charts {

    // Public Chart Variables
    private Activity activity; // Required to access the XML file from another class (passes their activity)
    public static float chartNumbers[]; // Makes up slice # and size of each slice
    public static String chartLabels[]; // Labels for Chart, used in Key or can be abstracted on top of chart
    List<PieEntry> pieEntries = new ArrayList<>(); // Pie Entries (Each Slice)
    public static float combined_income;
    private boolean debt;

    // Required to pull Budget information
    static final String fileName = "budget.json";
    Budget budget;
    //


    public Charts(int ChartType, Activity _activity) { // Negative for LargeChart, Positive for SmallChart(), 0 for Other

        // Sets activity to class instantiating this class
        this.activity = _activity;

        // Gets .json file for use
        File budgetFile = new File(this.activity.getFilesDir(), fileName);
        budget = new Budget();
        budget = budget.UpdateBudgetFromJSON(budgetFile);

        // Accounts so pie chart is accurate in displaying (does not cover negative values)
        float totalIncome = budget.primaryIncome + budget.secondaryIncome;
        float debits = budget.housingExpenses + budget.transportationExpenses + budget.utilitiesExpenses + budget.foodExpenses
                + budget.insuranceExpenses + budget.healthCareExpenses + budget.educationExpenses + budget.entertainmentExpenses
                + budget.miscellaneousExpenses;
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
            ChartType = 2;
        }

        // Chart Type
        if (ChartType == 0)
            LargeChart();
        else if (ChartType == 1)
            SmallChart();
        else if (ChartType == 2)
            DeficitChart();
        else if (ChartType == 3)
            BarGraph();
    }

    // Returns formatted Small Chart
    public void LargeChart() {

        this.activity.findViewById(R.id.display_bar_chart).setAlpha(0); // Hide the Bar Chart
        this.activity.findViewById(R.id.display_pie_chart).setAlpha(1); // Redisplay this chart (Priority)

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
        legend.setEnabled(false);
        //legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        //legend.setYOffset(50.0f);

        // Chart Information and Tweaking
        chart.setData(data);
        chart.setExtraBottomOffset(10.0f);
        chart.setExtraTopOffset(10.0f);
        //chart.setDrawEntryLabels(false);
        chart.setEntryLabelColor(ColorTemplate.colorWithAlpha(ColorTemplate.VORDIPLOM_COLORS[1], 200));
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

    // Prints out deficit Pie Chart (Only should occur if spending exceeds income)
    public void DeficitChart() {
        for(int i = 0; i < chartNumbers.length; i++) {
            pieEntries.add(new PieEntry(1.0f));
        } // Loops through length of # entries and adds them as a new PieEntry (Part of MPAndroidChart)

        // Pie Chart Variables - You likely need not touch these
        PieDataSet dataSet = new PieDataSet(pieEntries, "Deficit Pie Chart"); // PieDataSet a part of MPAndroidChart
        PieChart chart = (PieChart) this.activity.findViewById(R.id.display_pie_chart);
        Legend legend = chart.getLegend();

        // Pie Data Set
        dataSet.setColors(Color.RED); // Makes the Chart Colorful
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

    // Used when Labels may overlap severely with PieChart (bad display issue)
    public void BarGraph() {

        this.activity.findViewById(R.id.display_pie_chart).setAlpha(0); // hide the PieChart underlapping
        this.activity.findViewById(R.id.display_bar_chart).setAlpha(1); // Prioritize this chart module
        BarChart barChart = activity.findViewById(R.id.display_bar_chart); // Reference the BarChart module in XML design
        List<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < chartNumbers.length; i++) {

            if (chartNumbers[i] != 0) {
                entries.add(new BarEntry(i, chartNumbers[i]));

            } // If it's zero ignore
        } // Loops through length of # entries and adds them as a new PieEntry (Part of MPAndroidChart)

        int count = 0;
        for(int i = 0; i < chartLabels.length; i++) {
            if(chartLabels[i] != null) // In case the chartLabels contains null indexes (which happens if an entry # is 0)
                count++;
        }


        String labels[] = new String[count]; // Size of non-null version of chartLabels

        // Shortens string length
        for(int i = 0; i < labels.length; i++) {
            labels[i] = new String(chartLabels[i]); // Labels array is used in place of other array as it's a little different
            String temp = labels[i];
            for (int j = labels[i].length() - 1; j >= 0; j--) {
                temp = labels[i].substring(0, 3);
                labels[i] = temp;
            }
        }

        // Description tag for BarDataSet
        BarDataSet set = new BarDataSet(entries, "Your Budget");
        set.setValueTextSize(10.0f);

        BarData data = new BarData(set);
        data.setBarWidth((0.8f)); // Width of each bar

        // Initial data setup in Bar Graph
        barChart.setData(data);

        // XAxis Parameters
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis.setGranularity(1);
        xAxis.setCenterAxisLabels(false);
        xAxis.setAxisMinimum(data.getXMin() - 0.5f);
        xAxis.setAxisMaximum(data.getXMax() + 0.5f);
        xAxis.setLabelCount(10);

        barChart.getLegend().setEnabled(false);
        barChart.getDescription().setEnabled(false);
        barChart.animateY(1000);

        // Draws Graph
        barChart.invalidate();

    }
}
