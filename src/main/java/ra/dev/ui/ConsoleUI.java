package ra.dev.ui;

import java.util.Scanner;
import java.util.List;
import ra.dev.model.Contact;

public class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println("\n--- Phone List ---");
        System.out.println("1. Get all phone numbers");
        System.out.println("2. Get phone number");
        System.out.println("3. Add phone number");
        System.out.println("4. Delete phone number");
        System.out.println("5. Exit");
        System.out.print("Select an option: ");
    }

    public int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void showAllContacts(List<Contact> contacts) {
        System.out.println("************************");
        System.out.println("** Showing all contacts **");
        System.out.println("************************");
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            contacts.forEach(System.out::println);
        }
    }

    public void showPhoneNumber(String phone) {
        System.out.println("Phone number: " + phone);
    }

    public void clearConsole() {
        scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}

