package main.java;

import expenses.Commands;
import expenses.ExpenseService;
import expenses.ExpenseStorage;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ExpenseStorage.reader();
        //Testing args

        switch (args[0]) {
            case "add":
                if(args.l)){
                    ExpenseService service = new ExpenseService();
                    service.add(args[2],Integer.parseInt(args[4]));

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
            case "summary" : System.out.println("Enter amount to summary");
            break;
            default:
                System.out.println("Invalid input");
        }

    }
}

