package main;

import expenses.Commands;
import expenses.ExpenseData;
import expenses.ExpenseService;
import expenses.ExpenseStorage;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ExpenseStorage.reader();
        //Testing args

        if (args.length == 0) {
            System.out.println("Usage: add --description <description> --amount <amount>");
            return;
        }

        switch (args[0]) {
            case "add":
                if (args.length != 5 || !args[1].equals(Commands.Flag.DESCRIPTION.getValue()) || !args[3].equals(Commands.Flag.AMOUNT.getValue())) {
                    System.out.println("Usage: add --description <description> --amount <amount>");
                    break;
                }
                try {
                    ExpenseService expenseService = new ExpenseService();
                    ExpenseData created = expenseService.add(args[2], Integer.parseInt(args[4]));

                    System.out.println("Expense added successfully (ID: " + created.getId() + ")");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter an integer");

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "remove":
                System.out.println("Enter amount to remove");
                break;
            case "list":
                System.out.println("Enter amount to list");
                break;
            case "update":
                System.out.println("Enter amount to update");
                break;
            case "summary":
                System.out.println("Enter amount to summary");
                break;
            default:
                System.out.println("Invalid input");
        }

    }
}


