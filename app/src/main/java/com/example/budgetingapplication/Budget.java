/********************************************
 Name: class Budget
 Purpose:  To create functionality of a budget
 Notes:  NA
 ********************************************/


package com.example.budgetingapplication;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Budget
{
    //Income sources
    public float primaryIncome;
    public float secondaryIncome;

    //Expense sources
    public float housingExpenses;
    public float utilitiesExpenses;
    public float foodExpenses;
    public float transportationExpenses;

    public Budget()
    {
        primaryIncome = 0f;
        secondaryIncome = 0f;

        housingExpenses = 0f;
        utilitiesExpenses = 0f;
        foodExpenses = 0f;
        transportationExpenses = 0f;
    }


    public Budget UpdateBudgetfromJson(File budgetFile)
    {
        //Instantiates gson object
        Gson gson = new Gson();
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
                    return gson.fromJson(output, Budget.class);
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
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            //Uses default constructor to create budget with all fields set to 0
            return new Budget();
        }
        return new Budget();
    }
}
