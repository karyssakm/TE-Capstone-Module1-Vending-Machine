package com.techelevator.view;

import com.techelevator.Items.Item;
import com.techelevator.Items.VendingMachineItems;

import java.awt.*;
import java.awt.event.ItemListener;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static java.lang.System.out;

public class PurchaseMenu {
    private PrintWriter out;
    private Scanner in;
    private VendingMachineItems vendingMachineItems;
    private BigDecimal currentBalance;


    public PurchaseMenu(InputStream input, OutputStream output, VendingMachineItems vendingMachineItems) {

        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
        this.vendingMachineItems = vendingMachineItems;
        this.currentBalance = BigDecimal.ZERO;

    }


    /************************************************************************************
     THE PURCHASE MENU IS RESPONSIBLE FOR MANAGING THE USER'S BALANCE AND THEIR PURCHASES
     ***********************************************************************************/


    // SHOWING MENU OPTIONS BASED ON USER INPUT
    public Object getChoiceFromPurchaseOptions(Object[] options, BigDecimal currentBalance) {

        Object choice = null;

        while (choice == null) {
            displayPurchaseOptions(options, currentBalance);
            choice = purchaseOptionFromUserInput(options);

        }
        return choice;
    }


    //GETTING INPUT FROM USER
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


    //DISPLAY PURCHASE MENU OPTIONS
    private void displayPurchaseOptions(Object[] options, BigDecimal currentBalance) {
        out.println();

        //add in umbrella corp spacing to menu
        out.println("*****************************************");
        out.println("*  *  *   *   Purchase Menu   *   *  *  *");
        out.println("*****************************************");

        out.println();

        // Display current balance in purchase menu
        out.println("Current Money Provided: $" + currentBalance.setScale(2, RoundingMode.HALF_UP));
        out.println();


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

        try {
            int amount = Integer.parseInt(in.nextLine());

            if (amount > 0) {
                BigDecimal amountToAdd = BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP);
                currentBalance = currentBalance.add(amountToAdd);
                out.println("\nNice! You have added $" + amountToAdd + " to your balance.");
                out.flush();

            } else {
                out.println("Invalid amount. Please enter a positive amount.");
            }
        } catch (NumberFormatException e) {
            out.println("Invalid input. Please enter a dollar amount.");
        }
        return currentBalance;
    }


    /************************************************************************************
     SELECT PRODUCT - ASKING USER TO SELECT A PRODUCT
     ***********************************************************************************/
    public BigDecimal selectProduct(BigDecimal currentBalance) {
        vendingMachineItems.displayItems(); // Display items only once

        //ask user for product code to purchase
        out.print(System.lineSeparator() + "Select product to purchase: ");
        out.flush();
        String productCode = in.nextLine().toUpperCase();

        if (vendingMachineItems.isValidProductCode(productCode)) {

            BigDecimal productCost = vendingMachineItems.productCost(productCode);

            if (currentBalance.compareTo(productCost) >= 0) {

                if (vendingMachineItems.updateInventory(productCode) > 0) {

                    String productName = vendingMachineItems.snackName(productCode);

                    currentBalance = currentBalance.subtract(productCost); // Update current balance

                    out.println("\nDispensing " + productName + " for $" + productCost.setScale(2) + ". Remaining Balance: $" + currentBalance.setScale(2));
                    out.println(specialMessage(productName));
                    out.flush();

                } else {
                    out.println("Sorry, the item is SOLD OUT.");
                }

            } else {
                out.println("WOMP WOMP Insufficient funds. Please add more money.");
            }

        } else {
            out.println("Invalid product code.");
        }
        return currentBalance;
    }


    // Method for the message to connect with the product code and product name together
    private String specialMessage(String productName) {

        switch (productName) {

            case "Potato Crisps":
            case "Stackers":
            case "Grain Waves":
            case "Cloud Popcorn":
                return "Crunch Crunch, It's Yummy!";

            case "Moonpie":
            case "Cowtales":
            case "Wonka Bar":
            case "Crunchie":
                return "Munch Munch, Mmm Mmm Good!";

            case "Cola":
            case "Dr. Salt":
            case "Mountain Melter":
            case "Heavy":
                return "Glug Glug, Chug Chug!";

            case "U-Chews":
            case "Little League Chew":
            case "Chiclets":
            case "Triplemint":
                return "Chew Chew, Pop!";

            default:
                return "";
        }
    }


    /************************************************************************************
     FINISH TRANSACTION & LOG TRANSACTION
     ***********************************************************************************/

    //FINISH TRANSACTION
    public void finishTransaction(BigDecimal currentBalance) {

        returnChange(currentBalance); // Call returnChange without passing currentBalance
        this.currentBalance = BigDecimal.ZERO; // Reset currentBalance to zero
    }

    private void returnChange(BigDecimal remainingBalance) {

        int remainingBalanceInCents = remainingBalance.multiply(new BigDecimal("100")).intValue();

        int quarters = remainingBalanceInCents / 25;
        remainingBalanceInCents %= 25;
        int dimes = remainingBalanceInCents / 10;
        remainingBalanceInCents %= 10;
        int nickels = remainingBalanceInCents / 5;

        out.println("Returning change:");
        out.println("Quarters: " + quarters);
        out.println("Dimes: " + dimes);
        out.println("Nickels: " + nickels);
        out.flush();

        BigDecimal changeGiven = remainingBalance.setScale(2, RoundingMode.HALF_UP);
        logTransaction("GIVE CHANGE", changeGiven, BigDecimal.ZERO); // Log the transaction
    }


    //LOG TRANSACTION
    public void logTransaction(String action, BigDecimal amount, BigDecimal newBalance) {

        try (FileWriter fileWriter = new FileWriter("Log.txt", true);
             PrintWriter logWriter = new PrintWriter(fileWriter)) {

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
            String formattedDate = now.format(formatter);

            logWriter.printf("%s %s: $%.2f $%.2f\n", formattedDate, action, amount, newBalance);
            System.out.println("Log entry created: " + formattedDate + " " + action + ": $" + amount);

        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }
}







