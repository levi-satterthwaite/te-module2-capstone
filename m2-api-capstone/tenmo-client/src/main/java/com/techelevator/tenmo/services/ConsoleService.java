package com.techelevator.tenmo.services;


import com.techelevator.tenmo.auth.models.AuthenticatedUser;
import com.techelevator.tenmo.models.Account;
import com.techelevator.tenmo.models.Users;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class ConsoleService {

	private PrintWriter out;
	private Scanner in;


	public ConsoleService(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output, true);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		out.println();
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
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

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	public String getUserInput(String prompt) {
		out.print(prompt + ": ");
		out.flush();
		return in.nextLine();
	}

	public Integer getUserInputInteger(String prompt) {
		Integer result = null;
		do {
			out.print(prompt + ": ");
			out.flush();
			String userInput = in.nextLine();
			try {
				result = Integer.parseInt(userInput);
			} catch (NumberFormatException e) {
				out.println(System.lineSeparator() + "*** " + userInput + " is not valid ***" + System.lineSeparator());
			}
		} while (result == null);
		return result;
	}

	public void showUserBalance(Account account) {

		out.printf("Your current account balance is: $%.2f", account.getBalance());
		out.println("");
		out.flush();
	}

	public void showUserList(AuthenticatedUser currentUser, String baseUrl) {
		TransfersService transfersService = new TransfersService(currentUser, baseUrl);
		List<Users> userList = transfersService.getUsers();

		out.println("-------------------------------------------");
		out.println("Users");
		out.println("ID          Name");
		out.println("-------------------------------------------");

		for (Users users : userList) {
			if (!users.getUsername().equals(currentUser.getUser().getUsername())) {
				out.print(users.getUserID());
				out.println("        " + users.getUsername());
			}
		}

		out.println("---------");
		out.println("");
	}

	public int userTransferIdChoice() {
		out.println("Enter ID of user you are sending to (0 to cancel):");
		String userInput = in.nextLine();
		return Integer.parseInt(userInput);
	}

	public double userTransferAmountChoice(Account account) {
		out.println("Enter amount:");
		String userInput = in.nextLine();
		double inputAsDouble = Double.parseDouble(userInput);

		if (inputAsDouble > account.getBalance()){
			System.out.printf("The send request is greater than your current balance of $%.2f", account.getBalance());
			inputAsDouble = 0;
		}
		out.println("");

		return inputAsDouble;
	}
}
