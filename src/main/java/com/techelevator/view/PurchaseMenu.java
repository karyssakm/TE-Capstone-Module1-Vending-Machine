package com.techelevator.view;

import com.techelevator.Items.VendingMachineItems;

import java.awt.*;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

import static java.lang.System.out;

public class PurchaseMenu {
    private PrintWriter out;
    private Scanner in;
    private VendingMachineItems vendingMachineItems;


    public PurchaseMenu(InputStream input, OutputStream output, VendingMachineItems vendingMachineItems) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
        this.vendingMachineItems = vendingMachineItems;


//        this.currentMoneyProvided = 0;
    }


    /************************************************************************************
     THE PURCHASE MENU IS RESPONSIBLE FOR MANAGING THE USER'S BALANCE AND THEIR PURCHASES
     ***********************************************************************************/




    /************************************************************************************
     SHOWING MENU OPTIONS BASED ON USER INPUT
     ***********************************************************************************/
    public Object getChoiceFromPurchaseOptions(Object[] options, BigDecimal currentBalance) {
        Object choice = null;
        while (choice == null) {
            displayPurchaseOptions(options, currentBalance);
            choice = purchaseOptionFromUserInput(options);
        }
        return choice;
    }


    /************************************************************************************
     GETTING INPUT FROM USER
     ***********************************************************************************/
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


    /************************************************************************************
     DISPLAY PURCHASE MENU OPTIONS
     ***********************************************************************************/
    private void displayPurchaseOptions(Object[] options, BigDecimal currentBalance) {
        out.println();

        //add in umbrella corp spacing to menu
        out.println("*****************************************");
        out.println("*  *  *   *   Purchase Menu   *   *  *  *");
        out.println("*****************************************");

        out.println();

        // Display current balance in purchase menu
        displayBalance(currentBalance);


        //print menu options
        for (int i = 0; i < options.length; i++) {
            int optionNum = i + 1;
            out.println(optionNum + ") " + options[i]);
        }
        out.print(System.lineSeparator() + "Please choose an option >>> ");
        out.flush();
    }


    //need to add display current balance to the displayPurchaseOptions above
    public void displayBalance(BigDecimal currentBalance) {
        out.println("Current Balance: $" + currentBalance.setScale(2, RoundingMode.HALF_UP));
        out.flush();
    }


    /************************************************************************************
     FEEDING MONEY - ASKING USER FOR MONEY INPUT AND ERRORS IF NOT AN INTEGER
     ***********************************************************************************/
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
                //update the current balance
                currentBalance = currentBalance.add(amountToAdd);

                out.println("You have added $" + amountToAdd + " to the machine.\n");
                out.println("Current Money Provided: $" + currentBalance.setScale(2, RoundingMode.HALF_UP) + "\n");
                out.flush();
            }

        } catch (NumberFormatException e) {
            out.print("Invalid input. Please enter a dollar amount.\n");
            out.flush();

        }
        return currentBalance;
    }


    /************************************************************************************
     SELECT PRODUCT - ASKING USER TO SELECT A PRODUCT
     ***********************************************************************************/


    public BigDecimal selectProduct(BigDecimal currentBalance) {

        //Use the displayItems method to show list of items available to buy
        vendingMachineItems.loadInventory();


        //Ask for user input
        out.print(System.lineSeparator() + "Select product to purchase: ");
        out.flush();
        String productCode = in.nextLine().toUpperCase();

        BigDecimal productCost = vendingMachineItems.productCost(productCode);


        if (vendingMachineItems.isValidProductCode(productCode)) {
            //Validating the productCode
            out.println("You selected product " + productCode);

            //Check to verify balance is sufficient to buy item
            if (currentBalance.compareTo(productCost) <= 0) {
                out.println("WHOMP WHOMP you don't have enough money please add more");
                return currentBalance;
            }
            if(vendingMachineItems.updateInventory(productCode, 0) <= 0){
                out.println("Sorry our itens are too good! Sold Out");
                return currentBalance;
            }

            String message = "";
            Selectitem;
            if  ( vendingMachineItems.)



            //Process purchase and subtract item cost from current balance
           int currentQuantity = vendingMachineItems.updateInventory(productCode, 5);
            out.println("There are only:" + currentQuantity + " remaining");

            currentBalance = currentBalance.subtract(productCost);
            // Update Inventory after purchasing an item
            vendingMachineItems.updateInventory(productCode,currentQuantity);
            out.println("You have purchased the item " + productCode);
            out.println("Remaining Balance: $" + currentBalance.setScale(2));
            out.flush();

            return currentBalance;

        } else {
            // Invalid productCode
            out.println("***    Invalid product code    ***");
            out.flush();
            return currentBalance;
        }

    }







}


// (2) Select Product - Code


// Finish Transactions - Code
