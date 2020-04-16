package com.example.budgetingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity { // HOME CLASS
    private Button button;
    private Button button2;

    public static final String EXTRA_MESSAGE = "com.example.budgetingapplication.secondinstance";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_1);

        button2 = findViewById(R.id.button2);
                  button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                             openViewBudget();
                         }
                  });

        button = findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openMainCreateBudgetActivity();
                    }
                });
    }


    public void openMainCreateBudgetActivity() {
        Intent intent = new Intent(this, MainCreateBudgetActivity.class);
        startActivity(intent);
    }



    public void openViewBudget() {
        Intent intent = new Intent(this, ViewBudget.class);
        startActivity(intent);
    }



    public void JesseActivity(View view) {
        Intent intent = new Intent(this, jesse.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        //String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, "fixme");
        startActivity(intent);
    }
}
