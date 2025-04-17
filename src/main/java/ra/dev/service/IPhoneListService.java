package ra.dev.service;

import ra.dev.model.Contact;
import java.util.List;

public interface IPhoneListService {
    public List<Contact> listAllContacts();
    public Contact findByName(String name);
    public boolean addContact(String name, String phone);
    public boolean deleteContact(String name);
}
