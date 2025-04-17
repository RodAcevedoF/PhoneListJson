package ra.dev.service;

import ra.dev.model.Contact;
import ra.dev.repository.PhoneListRepository;
import java.util.List;

public class PhoneListService implements IPhoneListService{
    private final PhoneListRepository repository;

    public PhoneListService() {
        this.repository = new PhoneListRepository();
    }

    public PhoneListService(PhoneListRepository repo) {
        this.repository = repo;
    }

    public List<Contact> listAllContacts() {
        return repository.getAllContacts();
    }
    public Contact findByName(String name) {
        return repository.getAllContacts()
                .stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public boolean addContact(String name, String phone) {
        if (findByName(name) != null) {
            System.out.println("Contact already exists.");
            return false;
        }
        if (name == null || phone == null || name.isEmpty() || phone.isEmpty()) {
            System.out.println("Name or phone is empty.");
            return false;
        }
        return repository.addContact(new Contact(name, phone));
    }



    public boolean deleteContact(String name) {
        return repository.deleteContactByName(name);
    }
}
