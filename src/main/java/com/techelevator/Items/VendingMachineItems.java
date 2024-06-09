package com.techelevator.Items;

import com.techelevator.VendingMachineCLI;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.out;



// Define a new class called VendingMachine
public class VendingMachineItems {
    private Map <String, Integer> itemList= new HashMap<>();
    private Scanner scanner;

    //constructor
    public VendingMachineItems() {
        scanner = new Scanner(System.in);
//        itemList = new HashMap<String, Integer>();

    }

//    public int updateInventory(String item, int updateQuality) {
//        int currentQuantity = itemList.getOrDefault(item, 0); // Get the current quantity of the item
//        if (currentQuantity > 0) {
//            currentQuantity -= updateQuality; // Decrease currentQuantity by updateQuality (in this case, 1)
//            itemList.put(item, currentQuantity); // Update the quantity in the inventory
//            out.println("There are only: " + currentQuantity + " available");
//            return currentQuantity; // Return the updated quantity
//        } else {
//            out.println("Item not found in the Inventory or out of stock!");
//            return currentQuantity; // Return the unchanged quantity if the item is not found or has quantity 0
//        }
//    }

   public int updateInventory(String item ,int updateQuality ) {
       int currentQuantity = itemList.getOrDefault(item, 5);
       if (itemList.containsKey(item)) {
           currentQuantity = itemList.get(item);

           return currentQuantity;
       }
       if (currentQuantity > 0) {
        itemList.put(item,currentQuantity -1);
          // out.println("There are only:" + currentQuantity + " avaliable");
        return currentQuantity;
       }else {
           out.println("Item not found in the Inventory!");
       }

       return currentQuantity;

   }








    /************************************************************************************
         MANAGING THE ITEMS AVAILABLE IN THE VENDING MACHINE
     ***********************************************************************************/




    /************************************************************************************
        DEFINE THE DISPLAY_ITEMS METHOD IN THE VENDING_MACHINE CLASS
     ***********************************************************************************/

    public void loadInventory() {
        // Create a path to read the file
        File myFile = new File("C:\\Users\\Student\\workspace\\module-1-capstone-team-6\\vendingmachine.csv");

        // Use scanner to read the file
        try (Scanner scanner = new Scanner(myFile)) {

            // Read each line in file
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] items = line.split("\\|");

                String productCode =items[0];
                String productName = items[1];
                BigDecimal productPrice = new BigDecimal(items [2]);
                String productCategory = items[3];



                String productItems = String.format("%s %-20s %.2f ", items[0], items[1], Double.parseDouble(items[2]) );
                out.println(productItems);
            }

        } catch (FileNotFoundException e) {
            out.println("File Not Found: " + e.getMessage());
        }
    }


    /************************************************************************************
     SELECT PRODUCT FROM THE PURCHASE MENU
     ***********************************************************************************/
    public void displayItems() {
        // Create a path to read the file
        File myFile = new File("C:\\Users\\Student\\workspace\\module-1-capstone-team-6\\vendingmachine.csv");

        // Use scanner to read the file
        try (Scanner scanner = new Scanner(myFile)) {

            // Read each line in file
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] items = line.split("\\|");


                String productItems = String.format("%s %-20s %.2f %s", items[0], items[1], Double.parseDouble(items[2]), "Available: " + 5);
                out.println(productItems);
            }

        } catch (FileNotFoundException e) {
            out.println("File Not Found: " + e.getMessage());
        }
    }


    //validate product code
    public boolean isValidProductCode(String productCode) {
        File myFile = new File("C:\\Users\\Student\\workspace\\module-1-capstone-team-6\\vendingmachine.csv");
        try (Scanner scanner = new Scanner(myFile)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] items = line.split("\\|");

                if (items[0].equals(productCode)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            out.println("File Not Found: " + e.getMessage());
        }
        return false;
    }


    /************************************************************************************
                                PRODUCT COST
     ***********************************************************************************/

    public BigDecimal productCost(String productCode) {

        //set the default cost
        BigDecimal cost = BigDecimal.ZERO;


        //Load the inventory and find product code
        try (Scanner scanner = new Scanner(new File("C:\\Users\\Student\\workspace\\module-1-capstone-team-6\\vendingmachine.csv"))) {
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] items = line.split("\\|");

                if (items.length >= 3 && items[0].equals(productCode)) {

                    //Price is at index 2
                    cost = new BigDecimal(items[2]);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            //If file is not found exception
        }
        return cost;
    }



    /************************************************************************************
                         PRODUCT List
     ***********************************************************************************/






}


//
//
//    public void selectProduct() {
//        //loads available products
//        loadInventory();
//
//        //ask user to select a product
//        out.print(System.lineSeparator() + "Select product to purchase from machine:");
//        String userInput = scanner.nextLine().toUpperCase();
//
//    }
//
//
//    //explore more as another option
//    // private String name = "";
//
//
//    // Parse user input to the selected product number
//    String userSelectedProduct;
//
//
//    try {
//        if()
//
//    }
//
//

















