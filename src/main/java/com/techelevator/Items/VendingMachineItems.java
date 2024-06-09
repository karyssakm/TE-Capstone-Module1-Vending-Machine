package com.techelevator.Items;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static java.lang.System.load;
import static java.lang.System.out;


// Define a new class called VendingMachine
public class VendingMachineItems {
    private Map<String, Integer> itemList = new HashMap<>();
    private Map<String, String> itemNames = new HashMap<>();
    private Map<String, BigDecimal> itemPrices = new HashMap<>();
    private Scanner scanner;
    private boolean isInventoryLoaded = false;


    //constructor
    public VendingMachineItems() {
    }


    public boolean isValidProductCode(String productCode) {
        return itemList.containsKey(productCode);
    }

    public BigDecimal productCost(String productCode) {
        return itemPrices.getOrDefault(productCode, BigDecimal.ZERO);
    }

    public String snackName(String productCode) {
        return itemNames.getOrDefault(productCode, "unknown");
    }



/************************************************************************************
 MANAGING THE ITEMS AVAILABLE IN THE VENDING MACHINE
 ***********************************************************************************/


// READING THE CSV FILE FOR INVENTORY ITEMS
public void loadInventory() {

    // Create a path to read the file
    File myFile = new File("C:\\Users\\Student\\workspace\\module-1-capstone-team-6\\vendingmachine.csv");

    // Use scanner to read the file
    try (Scanner scanner = new Scanner(myFile)) {

        // Read each line in file
        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();
            String[] items = line.split("\\|");

            String productCode = items[0];
            String productName = items[1];
            BigDecimal productPrice = new BigDecimal(items[2]);
            String productCategory = items[3];

            itemList.put(productCode, 5);
            itemNames.put(productCode, productName);
            itemPrices.put(productCode, productPrice);

        }
        isInventoryLoaded = true;

    } catch (FileNotFoundException e) {
        out.println("File Not Found: " + e.getMessage());
    }

}


// DISPLAYING PRODUCTS FROM LOAD INVENTORY
public void displayItems() {
    if (!isInventoryLoaded) {
        loadInventory(); // Load the inventory before displaying items
    }

    if (itemList.isEmpty()) {
        out.println("No items to display. Inventory might not be loaded properly.");
    } else {
        // Create a TreeMap to sort items by product code
        Map<String, Integer> sortedItemList = new TreeMap<>(itemList);
        for (Map.Entry<String, Integer> entry : sortedItemList.entrySet()) {
            String productCode = entry.getKey();
            String productName = itemNames.get(productCode);
            BigDecimal productPrice = itemPrices.get(productCode);
            int quantity = entry.getValue();
            String availability = quantity > 0 ? String.valueOf(quantity) : "SOLD OUT";
            out.printf("%s %-20s $%.2f %s\n", productCode, productName, productPrice, availability);
        }
    }
}


// Update inventory after a purchase of an item
    public int updateInventory (String productCode){
        int currentQuantity = itemList.getOrDefault(productCode, 0);
        if (currentQuantity > 0) {
            itemList.put(productCode, currentQuantity - 1);
            return currentQuantity - 1;
        } else {
            return 0;
        }
    }
}

















