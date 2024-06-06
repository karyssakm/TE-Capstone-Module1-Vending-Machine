package com.techelevator.Items;

import com.techelevator.VendingMachineCLI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Define a new class called VendingMachine
public class VendingMachineItems {

    //constructor
    public VendingMachineItems() {

    }


        // Define the displayItems method in the VendingMachine class
        public void loadInventory () {
            // Create a path to read the file
            File myFile = new File("C:\\Users\\Student\\workspace\\module-1-capstone-team-6\\vendingmachine.csv");

            // Use scanner to read the file
            try (Scanner scanner = new Scanner(myFile)) {

                // Read each line in file
                while (scanner.hasNextLine()) {

                    String line = scanner.nextLine();
                    String [] items = line.split("\\|");


//                    System.out.println(line);
                    String productItems = String.format("%s %-20s %f %s", items[0], items[1], Double.parseDouble(items[2]), "Available: " + 5);
                    System.out.println(productItems);
                }

            } catch (FileNotFoundException e) {
                System.out.println("File Not Found: " + e.getMessage());
            }
        }
    }




