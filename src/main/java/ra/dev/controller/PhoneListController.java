package ra.dev.controller;
import java.util.List;
import ra.dev.model.Contact;
import ra.dev.service.PhoneListService;
import ra.dev.ui.ConsoleUI;

public class PhoneListController {
    private final PhoneListService service;
    private final ConsoleUI console;

    public PhoneListController() {
        this.service = new PhoneListService();
        this.console = new ConsoleUI();
    }

    public PhoneListController(PhoneListService service, ConsoleUI console) {
        this.service = service;
        this.console = console;
    }

    public void start() {
        int option;
        do {
            console.showMenu();
            option = console.readInt();
            switch (option) {
                case 1 -> getAllContacts();
                case 2 -> getPhoneNumber();
                case 3 -> addPhoneNumber();
                case 4 -> deletePhoneNumber();
                case 5 -> console.showMessage("Closing app...");
                default -> console.showMessage("Invalid option.");
            }
        } while (option != 5);
    }


    void getAllContacts() {
        List<Contact> contactList = service.listAllContacts();
        console.showAllContacts(contactList);
    }

    void getPhoneNumber() {
        console.clearConsole();
        String name = console.readLine("Contact's name: ");
        var contact = service.findByName(name);
        if (contact != null) {
            console.showMessage("Phone number: " + contact.getPhone());
        } else {
            console.showMessage("Contact not found.");
        }
    }

    void addPhoneNumber() {
        console.clearConsole();
        String name = console.readLine("Name: ");
        String phoneNumber = console.readLine("Phone number: ");

        if (service.addContact(name, phoneNumber)) {
            console.showMessage("Contact was added.");
        } else {
            console.showMessage("Contact already exists."); // 🔥 Esto soluciona el problema
        }
    }


    void deletePhoneNumber() {
        console.clearConsole();
        String name = console.readLine("Name to delete: ");
        if (service.deleteContact(name)) {
            console.showMessage("Contact was deleted.");
        } else {
            console.showMessage("Contact not found.");
        }
    }
}
