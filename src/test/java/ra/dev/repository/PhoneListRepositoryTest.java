package ra.dev.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ra.dev.model.Contact;

import java.io.PrintWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions .*;

    public class PhoneListRepositoryTest {

        private PhoneListRepository repository;
        private static final String TEST_FILE = "phonelist_test.json";

        @BeforeEach
        void setUp() {
            // Limpiar el archivo antes de cada prueba
            try (PrintWriter writer = new PrintWriter(TEST_FILE)) {
                writer.print("");
            } catch (Exception e) {
                fail("Unable to clean test file: " + e.getMessage());
            }

            // Inicializar repositorio con archivo de prueba
            repository = new PhoneListRepository(TEST_FILE);
        }

        @Test
        void testAddContact() {
            Contact contact = new Contact("Alice", "123456");
            boolean added = repository.addContact(contact);

            assertTrue(added);
            List<Contact> contacts = repository.getAllContacts();
            assertEquals(1, contacts.size());
            assertEquals("Alice", contacts.get(0).getName());
        }

        @Test
        void testPreventDuplicateContact() {
            repository.addContact(new Contact("Bob", "987654"));
            boolean addedAgain = repository.addContact(new Contact("Bob", "987654"));

            assertFalse(addedAgain);
            assertEquals(1, repository.getAllContacts().size());
        }

        @Test
        void testDeleteContactByName() {
            repository.addContact(new Contact("Charlie", "555555"));
            boolean deleted = repository.deleteContactByName("Charlie");

            assertTrue(deleted);
            assertEquals(0, repository.getAllContacts().size());
        }

        @Test
        void testGetAllContacts_EmptyFile() {
            List<Contact> contacts = repository.getAllContacts();
            assertTrue(contacts.isEmpty());
        }
    }
