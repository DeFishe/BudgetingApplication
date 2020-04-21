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

    // Required things: a file stream of some sort, depends on the function we're in at the time.
    private static final String FILE_NAME = "example.txt";
    EditText mEditText;

    public void LoadFile(View v) {
        FileInputStream fis = null;

        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while((text = br.readLine()) != null) {
                sb.append(text).append("\n"); // Reads text line by line
            }

            mEditText.setText(sb.toString());

            // debugging
            System.out.println("testing");
            for(int i = 0; i < sb.length(); i++) {
                System.out.println("Current index: " + i + ", line is: " + sb.toString());
            }

            // debugging end

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void Save(View v) {
        String text = mEditText.getText().toString();
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());

            mEditText.getText().clear();
            Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
