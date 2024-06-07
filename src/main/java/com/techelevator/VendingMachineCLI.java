package com.techelevator;

import com.techelevator.Items.VendingMachineItems;
import com.techelevator.view.PurchaseMenu;
import com.techelevator.view.VendingMenu;

import java.math.BigDecimal;


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
    private PurchaseMenu purchaseMenu;
    private BigDecimal currentBalance; //changed this

    // add VendingMachineItems to constructor to encapsulate the functionality within VendingMachineCLI
    // Initialize and instantiate
    public VendingMachineCLI(VendingMenu menu,PurchaseMenu purchaseMenu) {
        this.menu = menu;
        this.vendingMachineItems = new VendingMachineItems();
        this.purchaseMenu = purchaseMenu;
        this.currentBalance = BigDecimal.ZERO.setScale(2);  //initialize balance to 0 (default)

    }


    /************************************************************************************
                                    MAIN MENU OPTIONS
     ***********************************************************************************/
    public void run() {
		boolean running = true;
		while (running) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			//using if-else statements
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {

				// display vending machine items
				vendingMachineItems.loadInventory();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
                purchaseRun();

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				running = false;
			}
		}
	}



    /************************************************************************************
                             PROCESS PURCHASE RUN OPTIONS
     ***********************************************************************************/
    public void purchaseRun() {
        boolean purchasing = true;
        while (purchasing) {

            //Get user choice from purchase menu
            String choice = (String) purchaseMenu.getChoiceFromPurchaseOptions(PURCHASE_MENU_OPTIONS, this.currentBalance);


            //using if-else statements
            if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
                this.currentBalance = purchaseMenu.feedingMoney(this.currentBalance);


            } else if (choice.equals( PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
                // do purchase

            } else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
                purchasing = false;
            }
        }
    }

// method to display balance below the title in the purchase menu option
    private void displayCurrentBalance() {
        purchaseMenu.displayBalance(this.currentBalance);
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
        PurchaseMenu purchaseMenu = new PurchaseMenu(System.in , System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu, purchaseMenu);
        cli.run();
    }
}








