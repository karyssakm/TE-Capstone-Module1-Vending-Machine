package com.techelevator.Items;

import com.techelevator.VendingMachineCLI;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.out;



// Define a new class called VendingMachine
public class VendingMachineItems {

    private Scanner scanner;


    //constructor
    public VendingMachineItems() {
        scanner = new Scanner(System.in);
    }

    //constructor
//    public VendingMachineItems() {
//
//    }


    /************************************************************************************
            DEFINE THE DISPLAY_ITEMS METHOD IN THE VENDING_MACHINE CLASS
     ***********************************************************************************/

        public void loadInventory () {
            // Create a path to read the file
            File myFile = new File("C:\\Users\\Student\\workspace\\module-1-capstone-team-6\\vendingmachine.csv");

            // Use scanner to read the file
            try (Scanner scanner = new Scanner(myFile)) {

                // Read each line in file
                while (scanner.hasNextLine()) {

                    String line = scanner.nextLine();
                    String [] items = line.split("\\|");


                    String productItems = String.format("%s %-20s %.2f %s", items[0], items[1], Double.parseDouble(items[2]), "Available: " + 5);
                    out.println(productItems);
                }

            } catch (FileNotFoundException e) {
                out.println("File Not Found: " + e.getMessage());
            }
        }


    /************************************************************************************
         SELECT PRODUCT FROM THE PURCHASE MENU
     ***********************************************************************************/

    public void selectProduct() {
        //loads available products
        loadInventory();

        //ask user to select a product
        out.print(System.lineSeparator() + "Select product to purchase from machine:");
        String userInput = scanner.nextLine().toUpperCase();

    }


    //explore more as another option
    // private String name = "";


    // Parse user input to the selected product number
    String userSelectedProduct;


    try {
        if()

    }









}









