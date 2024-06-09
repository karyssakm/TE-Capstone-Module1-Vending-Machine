package com.techelevator.view;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.math.BigDecimal;

import com.techelevator.Items.VendingMachineItems;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PurchaseMenuTest {

	private ByteArrayOutputStream output;
	private VendingMachineItems vendingMachineItems;
	private PurchaseMenu purchaseMenu;

	@Before
	public void setup() {
		output = new ByteArrayOutputStream();
		vendingMachineItems = new VendingMachineItems();
		purchaseMenu = new PurchaseMenu(new ByteArrayInputStream(new byte[0]), new PrintStream(output), vendingMachineItems);

	}

	@Test
	public void displays_a_list_of_menu_options_and_prompts_user_to_make_a_choice() {
		Object[] options = new Object[]{Integer.valueOf(3), "Blind", "Mice"};
		PurchaseMenu menu = getMenuForTestingWithUserInput("1\n");

		menu.getChoiceFromPurchaseOptions(options, BigDecimal.ZERO);

		String expected = System.lineSeparator() + "*****************************************" + System.lineSeparator()
				+ "*  *  *   *   Purchase Menu   *   *  *  *" + System.lineSeparator()
				+ "*****************************************" + System.lineSeparator()
				+ System.lineSeparator()
				+ "Current Money Provided: $0.00" + System.lineSeparator()
				+ "1) 3" + System.lineSeparator()
				+ "2) Blind" + System.lineSeparator()
				+ "3) Mice" + System.lineSeparator()
				+ System.lineSeparator()
				+ "Please choose an option >>> ";
		Assert.assertEquals(expected, output.toString());
	}

	@Test
	public void returns_object_corresponding_to_user_choice() {
		Integer expected = Integer.valueOf(456);
		Integer[] options = new Integer[]{Integer.valueOf(123), expected, Integer.valueOf(789)};
		PurchaseMenu menu = getMenuForTestingWithUserInput("2" + System.lineSeparator());

		Integer result = (Integer) menu.getChoiceFromPurchaseOptions(options, BigDecimal.ZERO);

		Assert.assertEquals(expected, result);
	}

	@Test
	public void redisplays_menu_if_user_does_not_choose_valid_option() {
		Object[] options = new Object[]{"Larry", "Curly", "Moe"};
		PurchaseMenu menu = getMenuForTestingWithUserInput("4" + System.lineSeparator() + "1" + System.lineSeparator());

		menu.getChoiceFromPurchaseOptions(options, BigDecimal.ZERO);

		String menuDisplay = System.lineSeparator() + "*****************************************" + System.lineSeparator()
				+ "*  *  *   *   Purchase Menu   *   *  *  *" + System.lineSeparator()
				+ "*****************************************" + System.lineSeparator()
				+ System.lineSeparator()
				+ "Current Money Provided: $0.00" + System.lineSeparator()
				+ "1) Larry" + System.lineSeparator()
				+ "2) Curly" + System.lineSeparator()
				+ "3) Moe" + System.lineSeparator()
				+ System.lineSeparator()
				+ "Please choose an option >>> ";

		String expected = menuDisplay + System.lineSeparator() + "*** 4 is not a valid option ***" + System.lineSeparator() + System.lineSeparator() + menuDisplay;

		Assert.assertEquals(expected, output.toString());
	}

	@Test
	public void redisplays_menu_if_user_chooses_option_less_than_1() {
		Object[] options = new Object[]{"Larry", "Curly", "Moe"};
		PurchaseMenu menu = getMenuForTestingWithUserInput("0" + System.lineSeparator() + "1" + System.lineSeparator());

		menu.getChoiceFromPurchaseOptions(options, BigDecimal.ZERO);

		String menuDisplay = System.lineSeparator() + "*****************************************" + System.lineSeparator()
				+ "*  *  *   *   Purchase Menu   *   *  *  *" + System.lineSeparator()
				+ "*****************************************" + System.lineSeparator()
				+ System.lineSeparator()
				+ "Current Money Provided: $0.00" + System.lineSeparator()
				+ "1) Larry" + System.lineSeparator()
				+ "2) Curly" + System.lineSeparator()
				+ "3) Moe" + System.lineSeparator()
				+ System.lineSeparator()
				+ "Please choose an option >>> ";

		String expected = menuDisplay + System.lineSeparator() + "*** 0 is not a valid option ***" + System.lineSeparator() + System.lineSeparator() + menuDisplay;

		Assert.assertEquals(expected, output.toString());
	}

	@Test
	public void redisplays_menu_if_user_enters_garbage() {
		Object[] options = new Object[]{"Larry", "Curly", "Moe"};
		PurchaseMenu menu = getMenuForTestingWithUserInput("Mickey Mouse" + System.lineSeparator() + "1" + System.lineSeparator());

		menu.getChoiceFromPurchaseOptions(options, BigDecimal.ZERO);

		String menuDisplay = System.lineSeparator() + "*****************************************" + System.lineSeparator()
				+ "*  *  *   *   Purchase Menu   *   *  *  *" + System.lineSeparator()
				+ "*****************************************" + System.lineSeparator()
				+ System.lineSeparator()
				+ "Current Money Provided: $0.00" + System.lineSeparator()
				+ "1) Larry" + System.lineSeparator()
				+ "2) Curly" + System.lineSeparator()
				+ "3) Moe" + System.lineSeparator()
				+ System.lineSeparator()
				+ "Please choose an option >>> ";

		String expected = menuDisplay + System.lineSeparator() + "*** Mickey Mouse is not a valid option ***" + System.lineSeparator() + System.lineSeparator() + menuDisplay;

		Assert.assertEquals(expected, output.toString());
	}

	private PurchaseMenu getMenuForTestingWithUserInput(String userInput) {
		ByteArrayInputStream input = new ByteArrayInputStream(userInput.getBytes());
		return new PurchaseMenu(input, new PrintStream(output), vendingMachineItems);
	}

	private PurchaseMenu getMenuForTesting() {
		return getMenuForTestingWithUserInput("1" + System.lineSeparator());
	}

}


