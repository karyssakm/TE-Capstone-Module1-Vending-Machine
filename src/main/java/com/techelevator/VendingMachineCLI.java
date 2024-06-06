package com.techelevator;

import com.techelevator.Items.VendingMachineItems;
import com.techelevator.view.VendingMenu;


public class VendingMachineCLI {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String MAIN_MENU_SECRET_OPTION = "*Sales Report";

    private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";

    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_SECRET_OPTION};
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};


    //Declaring variables in VendingMachineCLI
    private VendingMenu menu;
    private VendingMachineItems vendingMachineItems;


    // add VendingMachineItems to constructor to encapsulate the functionality within VendingMachineCLI
    // Initialize and instantiate
    public VendingMachineCLI(VendingMenu menu) {
        this.menu = menu;
        this.vendingMachineItems = new VendingMachineItems();
    }

    public void run() {
		boolean running = true;
		while (running) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			// A switch statement could also be used here.  Your choice.

			//using if-else statements
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {

				// display vending machine items
                //loadInventory();
				vendingMachineItems.loadInventory();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				running = false;
			}
		}
	}

			//using switch statement
//            switch (choice) {
//                case MAIN_MENU_OPTION_DISPLAY_ITEMS:
//                    displayItems();
//                    break;
//                case MAIN_MENU_OPTION_PURCHASE:
//                    //do purchase method
//                    break;
//                case MAIN_MENU_OPTION_EXIT:
//                    running = false;
//                    break;
//                default:
//                    System.out.println("Invalid choice.");
//                    break;
//            }
//        }
//    }


    public static void main(String[] args) {
        VendingMenu menu = new VendingMenu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }


    // Create a path from option 1
//    public void loadInventory() {
//        // Create a path to read the file
//        File myFile = new File("C:\\Users\\Student\\workspace\\module-1-capstone-team-6\\vendingmachine.csv");
//
//        // Use scanner to read the file
//        try (Scanner scanner = new Scanner(myFile)) {
//            // Read each line in file
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                System.out.println(line);
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("File Not Found: " + e.getMessage());
//        }
//    }
}











