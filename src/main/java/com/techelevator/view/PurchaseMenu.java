package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class PurchaseMenu {
    private PrintWriter out;
    private Scanner in;



    public PurchaseMenu(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }
    // showing purchase options based on user input
    public Object getChoiceFromPurchaseOptions(Object[] options) {
        Object choice = null;
        while (choice == null) {
            displayPurchaseOptions(options);
            choice = purchaseOptionFromUserInput(options);
        }
        return choice;
    }
    // Getting the input from a user
    private Object purchaseOptionFromUserInput(Object[] options) {
        Object choice = null;
        String userInput = in.nextLine();
        try {
            int selectedOption = Integer.valueOf(userInput);
            if (selectedOption > 0 && selectedOption <= options.length) {
                choice = options[selectedOption - 1];
            }
        } catch (NumberFormatException e) {
            // eat the exception, an error message will be displayed below since choice will be null
        }
        if (choice == null) {
            out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
        }
        return choice;
    }
    // Displaying the Purchase Menu Options
    private void displayPurchaseOptions(Object[] options) {
        out.println();

        //add in umbrella corp spacing to menu
        out.println("*****************************************");
        out.println("*  *  *   *   Purchase Menu   *   *  *  *");
        out.println("*****************************************");

        out.println();

        for (int i = 0; i < options.length; i++) {
            int optionNum = i + 1;
            out.println(optionNum + ") " + options[i]);
        }
        out.print(System.lineSeparator() + "Please choose an option >>> ");
        out.flush();
    }

    // (1) Feeding Money - Code
    public double  feedingMoney(){
        out.print("Enter the amount of money to feed machine (Dollars only): $");
        out.flush();
        int  amount = 0;
        try{
            if ( amount < 0 ){
                out.print(" Invalid amount. Please enter a positive amount.");
                out.flush();
            }else{
                out.print("you have added: $"+ amount + " to the machine");

            }
        } catch (NumberFormatException e){
            out.print("Invalid input. Please enter a dollar amount.");
            out.flush();
        }
        return amount;


    }

}










// (2) Select Product - Code










// Finish Transactions - Code
