package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class PurchaseMenu {
    private PrintWriter out;
    private Scanner in;



    public PurchaseMenu(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }

    public Object getChoiceFromPurchaseOptions(Object[] choises ) {
        Object choice = null;
        while (choice == null) {
            displayPurchaseOptions(choises);
            choice = displayPurchaseOptions(choises);
        }
        return choice;
    }

    private Object displayPurchaseOptions(Object[] choises) {
        System.out.println();
    }

}
