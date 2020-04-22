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
import java.io.FileOutputStream;
import java.io.FileWriter;
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
    public float insuranceExpenses;
    public float healthCareExpenses;
    public float educationExpenses;
    public float entertainmentExpenses;
    public float miscellaneousExpenses;

    public Budget()
    {
        //Income
        primaryIncome = 0f;
        secondaryIncome = 0f;

        //Expenses
        housingExpenses = 0f;
        utilitiesExpenses = 0f;
        foodExpenses = 0f;
        transportationExpenses = 0f;
        insuranceExpenses = 0f;
        healthCareExpenses = 0f;
        educationExpenses = 0f;
        entertainmentExpenses = 0f;
        miscellaneousExpenses = 0f;
    }


    public Budget UpdateBudgetFromJSON(File budgetFile)
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

    public void UpdateBudgetToJSON(File file, Budget budget)
    {
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
