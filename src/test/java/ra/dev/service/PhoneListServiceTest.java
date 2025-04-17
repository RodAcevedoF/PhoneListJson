package ra.dev.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ra.dev.model.Contact;
import ra.dev.repository.PhoneListRepository;

import java.io.PrintWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneListServiceTest {

    private PhoneListService service;
    private static final String TEST_FILE = "phonelist_test.json";

    @BeforeEach
    void setUp() {
        // Limpiar el archivo de prueba antes de cada test
        try (PrintWriter writer = new PrintWriter(TEST_FILE)) {
            writer.print("");
        } catch (Exception e) {
            fail("Unable to clean test file: " + e.getMessage());
        }

        // Inicializar el repositorio y el servicio
        PhoneListRepository repo = new PhoneListRepository(TEST_FILE);
        service = new PhoneListService(repo); // <- Esta línea es clave
    }

    @Test
    void testAddAndFindContact() {
        boolean added = service.addContact("Alice", "123456");
        assertTrue(added);
        Contact found = service.findByName("Alice");
        assertNotNull(found);
        assertEquals("123456", found.getPhone());
    }

    @Test
    void testPreventDuplicateContact() {
        service.addContact("Alice", "123456");
        boolean addedAgain = service.addContact("Alice", "123456");
        assertFalse(addedAgain);
        List<Contact> contacts = service.listAllContacts();
        assertEquals(1, contacts.size());
    }

    @Test
    void testDeleteContact() {
        service.addContact("Bob", "987654");
        boolean deleted = service.deleteContact("Bob");
        assertTrue(deleted);
        assertNull(service.findByName("Bob"));
    }
}
