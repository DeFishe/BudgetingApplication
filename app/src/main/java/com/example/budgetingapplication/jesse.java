package com.example.budgetingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class jesse extends AppCompatActivity {

    float budgetInformation[] = {1.0f, 2.0f, 5.0f, 10.5f};
    String numNames[] = {"Tax Bracket", "Car % From Budget", "Hershey's Bar", "CS:GO Skins"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jesse);

        TextView header = (TextView)findViewById(R.id.display_results_header);
        header.setTextSize(35);

        startPieChart();
    }


    public void startPieChart() {
        // Pie Entries
        List<PieEntry> pieEntries = new ArrayList<>();

        for(int i = 0; i < budgetInformation.length; i++) {
            pieEntries.add(new PieEntry(budgetInformation[i], numNames[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "Your Budget!");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);

        // Chart
        PieChart chart = (PieChart) findViewById(R.id.display_pie_chart);
        chart.setData(data);
        //chart.
        chart.animateY(1000);
        chart.invalidate();
    }
}