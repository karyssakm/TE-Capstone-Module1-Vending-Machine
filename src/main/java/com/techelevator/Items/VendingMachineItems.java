package com.techelevator.Items;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static java.lang.System.load;
import static java.lang.System.out;


// Define a new class called VendingMachineitems (declaring variables)
public class VendingMachineItems {
    private Map<String, Integer> itemList = new HashMap<>();
    private Map<String, String> itemNames = new HashMap<>();
    private Map<String, BigDecimal> itemPrices = new HashMap<>();
    private Map<String, Integer> salesData = new HashMap<>();
    private Scanner scanner;
    private boolean isInventoryLoaded = false;


    //constructor
    public VendingMachineItems() {
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
            salesData.put(productCode, 0);      //Initialize sales data based on each item in inventory

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
            out.printf("%-2s %-20s $%-6.2f QTY: %s\n", productCode, productName, productPrice, availability);
        }
    }
}


// Update inventory after a purchase of an item
    public int updateInventory (String productCode){

        int currentQuantity = itemList.getOrDefault(productCode, 0);

        if (currentQuantity > 0) {
            itemList.put(productCode, currentQuantity - 1);
            salesData.put(productCode, salesData.get(productCode) + 1);   // updating sales data
            return currentQuantity - 1;

        } else {
            return 0;
        }
    }

    public boolean isValidProductCode(String productCode) {
        return itemList.containsKey(productCode);
    }

    public String snackName(String productCode) {
        return itemNames.getOrDefault(productCode, "unknown");
    }

    public BigDecimal productCost(String productCode) {
        return itemPrices.getOrDefault(productCode, BigDecimal.ZERO);
    }


    /************************************************************************************
             GENERATE A SALES REPORT
     ***********************************************************************************/
    public void generateSalesReport() {

        try (PrintWriter writer = new PrintWriter(new FileWriter("SalesReport_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".txt"))) {
            BigDecimal totalSales = BigDecimal.ZERO;


            //define string format
            String format = "%-20s|%5d%n";

            for (Map.Entry<String, Integer> entry : salesData.entrySet()) {

                String productCode = entry.getKey();
                int sales = entry.getValue();
                String productName = snackName(productCode);
                BigDecimal productPrice = productCost(productCode);
                BigDecimal productSales = productPrice.multiply(BigDecimal.valueOf(sales));

                totalSales = totalSales.add(productSales);

                writer.println(productName + "|" + sales);
            }

            writer.println();
            writer.printf("**TOTAL SALES** $%.2f%n", totalSales.setScale(2, RoundingMode.HALF_UP));

        } catch (IOException e) {
            System.out.println("Error writing sales report: " + e.getMessage());
        }
    }

}

















