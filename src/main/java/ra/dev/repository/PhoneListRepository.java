package ra.dev.repository;

import ra.dev.model.Contact;
import ra.dev.utils.JsonUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneListRepository {
    private String fileName = "contacts.json";

    public PhoneListRepository(String fileName) {
        this.fileName = fileName;
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }

    public PhoneListRepository() {
    }

    public List<Contact> getAllContacts() {
        File file = new File(fileName);
        if (!file.exists()) return new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
            List<Contact> contacts = JsonUtil.fromJson(sb.toString());
            return (contacts != null) ? contacts : new ArrayList<>(); // Asegura que nunca sea null
        } catch (IOException e) {
            System.out.println("Error reading contacts: " + e.getMessage());
            return new ArrayList<>(); // En caso de error, devuelve lista vacía
        }
    }


    public void saveAllContacts(List<Contact> contacts) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(JsonUtil.toJson(contacts));
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    public boolean addContact(Contact contact) {
        List<Contact> contacts = getAllContacts();
        boolean exists = contacts.stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase(contact.getName()));
        if (!exists) {
            contacts.add(contact);
            saveAllContacts(contacts);
            return true;
        } else {
            System.out.println("Contact already exists.");
            return false;
        }
    }

    public boolean deleteContactByName(String name) {
        List<Contact> contacts = getAllContacts();
        boolean removed = contacts.removeIf(c -> c.getName().equalsIgnoreCase(name));
        if (removed) saveAllContacts(contacts);
        return removed;
    }
}
