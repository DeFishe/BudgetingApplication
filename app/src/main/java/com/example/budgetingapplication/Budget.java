/********************************************
 Name: class Budget
 Purpose:  To create functionality of a budget
 Notes:  NA
 ********************************************/


package com.example.budgetingapplication;

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
}
