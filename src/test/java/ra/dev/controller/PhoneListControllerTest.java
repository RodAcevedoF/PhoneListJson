package ra.dev.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ra.dev.model.Contact;
import ra.dev.service.PhoneListService;
import ra.dev.ui.ConsoleUI;

import java.util.List;

import static org.mockito.Mockito.*;

public class PhoneListControllerTest {

    private PhoneListController controller;
    private PhoneListService mockService;
    private ConsoleUI mockConsole;

    @BeforeEach
    void setUp() {
        mockService = mock(PhoneListService.class);
        mockConsole = mock(ConsoleUI.class);
        controller = new PhoneListController(mockService, mockConsole);
    }

    @Test
    void testGetAllContacts() {
        List<Contact> contacts = List.of(new Contact("Alice", "123456"));
        when(mockService.listAllContacts()).thenReturn(contacts);

        controller.getAllContacts();

        verify(mockConsole).showAllContacts(contacts);
    }

    @Test
    void testGetPhoneNumber_ContactExists() {
        when(mockConsole.readLine("Contact's name: ")).thenReturn("Alice");
        when(mockService.findByName("Alice")).thenReturn(new Contact("Alice", "123456"));

        controller.getPhoneNumber();

        verify(mockConsole).showMessage("Phone number: 123456");
    }

    @Test
    void testGetPhoneNumber_ContactNotFound() {
        when(mockConsole.readLine("Contact's name: ")).thenReturn("Bob");
        when(mockService.findByName("Bob")).thenReturn(null);

        controller.getPhoneNumber();

        verify(mockConsole).showMessage("Contact not found.");
    }

    @Test
    void testAddPhoneNumber_Success() {
        when(mockConsole.readLine("Name: ")).thenReturn("Charlie");
        when(mockConsole.readLine("Phone number: ")).thenReturn("555555");
        when(mockService.addContact("Charlie", "555555")).thenReturn(true);

        controller.addPhoneNumber();

        verify(mockConsole).showMessage("Contact was added.");
    }

    @Test
    void testAddPhoneNumber_Failure() {
        when(mockConsole.readLine("Name: ")).thenReturn("Charlie");
        when(mockConsole.readLine("Phone number: ")).thenReturn("555555");
        when(mockService.addContact("Charlie", "555555")).thenReturn(false);

        controller.addPhoneNumber();

        verify(mockConsole).showMessage("Contact already exists.");
    }

    @Test
    void testDeletePhoneNumber_Success() {
        when(mockConsole.readLine("Name to delete: ")).thenReturn("Alice");
        when(mockService.deleteContact("Alice")).thenReturn(true);

        controller.deletePhoneNumber();

        verify(mockConsole).showMessage("Contact was deleted.");
    }

    @Test
    void testDeletePhoneNumber_Failure() {
        when(mockConsole.readLine("Name to delete: ")).thenReturn("Bob");
        when(mockService.deleteContact("Bob")).thenReturn(false);

        controller.deletePhoneNumber();

        verify(mockConsole).showMessage("Contact not found.");
    }
}

