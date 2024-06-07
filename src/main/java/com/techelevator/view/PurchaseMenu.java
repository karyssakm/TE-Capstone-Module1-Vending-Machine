package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class PurchaseMenu {
    private PrintWriter out;
    private Scanner in;


    public PurchaseMenu(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
//        this.currentMoneyProvided = 0;
    }



    // showing purchase options based on user input
    public Object getChoiceFromPurchaseOptions(Object[] options, BigDecimal currentBalance) {
        Object choice = null;
        while (choice == null) {
            displayPurchaseOptions(options, currentBalance);
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
    private void displayPurchaseOptions(Object[] options, BigDecimal currentBalance) {
        out.println();

        //add in umbrella corp spacing to menu
        out.println("*****************************************");
        out.println("*  *  *   *   Purchase Menu   *   *  *  *");
        out.println("*****************************************");

        out.println();

        // Display current balance in purchase menu
//        displayBalance(currentBalance);
//        out.println();
        out.println("Current Balance: $" + currentBalance.setScale(2, RoundingMode.HALF_UP));
        out.flush();


        //print menu options
        for (int i = 0; i < options.length; i++) {
            int optionNum = i + 1;
            out.println(optionNum + ") " + options[i]);
        }
        out.print(System.lineSeparator() + "Please choose an option >>> ");
        out.flush();
    }


    //need to add display current balance to the displayPurchaseOptions above
//    public void displayBalance(BigDecimal currentBalance) {
//        System.out.println("Current Balance: $" + currentBalance.setScale(2, RoundingMode.HALF_UP));
//        System.out.flush();
//    }





    // FEEDING MONEY - ASKING USER FOR MONEY INPUT AND ERRORS IF NOT AN INTEGER
    public BigDecimal feedingMoney(BigDecimal currentBalance) {
        out.print(System.lineSeparator() + "Enter the amount of money to feed machine (Dollars only): $");
        out.flush();

//    int amount = 0;
        try {
            int amount = Integer.parseInt(in.nextLine());
//        amount = in.nextInt();

            if (amount <= 0) {
                out.print("Invalid amount. Please enter a positive amount.");
                out.println();
                out.flush();
            } else {
                BigDecimal amountToAdd = BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP);
                currentBalance = currentBalance.add(amountToAdd);
                out.println("You have added: $" + amountToAdd + " to the machine");
                out.println("Current Money Provided: $" + currentBalance.setScale(2, RoundingMode.HALF_UP));
                out.println();
                out.flush();
//            in.nextLine();
            }
        } catch (NumberFormatException e) {
            out.print("Invalid input. Please enter a dollar amount.");
            out.println();
            out.flush();

        }
        return currentBalance;
    }


    // DISPLAY CURRENT BALANCE TO USER


}



// (2) Select Product - Code


// Finish Transactions - Code
