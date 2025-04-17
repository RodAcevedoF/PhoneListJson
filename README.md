
# 📞 Phone List Manager

Phonebook manager developed in Java. It allows adding, searching, deleting, and viewing contacts, with data persistence in JSON format. The project also includes integrated unit tests.

## 🧱 Layered Architecture

- `model`: Represents the data, such as the `Contact` class.  
- `repository`: Handles data persistence (the `contacts.json` file).  
- `service`: Contains the business logic.  
- `controller`: Manages the application's flow.  
- `ui`: Console-based user interface.  
- `utils`: Utilities like JSON handling using Gson.  
- `test`: Unit tests (if already placed in a separate package).  

## 🚀 What can you do with this program?

1. 📋 List all contacts  
2. 🔎 Search for a phone number by name  
3. ➕ Add a new contact  
4. ❌ Delete an existing contact  
5. 🚪 Exit the program  

## 🗃️ Storage Format

All contacts are saved in a `contacts.json` file in the project’s root directory. The **Gson** library is used to serialize and deserialize `Contact` objects.

Example JSON content:

```json
[
  {
    "name": "Alice",
    "phone": "123456789"
  },
  {
    "name": "Bob",
    "phone": "987654321"
  }
]
```

## ⚙️ How to Run the Project

1. Clone the repository or download the source files.  
2. Make sure you have Java 17 or higher installed.  
3. Run the `main()` method from your main class (e.g., a `Main` class that calls `new PhoneListController().start();`).  

## 🧪 Testing

The project includes unit tests to ensure the system works correctly. They are based on JUnit. You can run them from your IDE or via command line using Maven/Gradle (if already configured).

## 📦 Dependencies

- [Gson](https://github.com/google/gson): For JSON serialization/deserialization.  
- [JUnit](https://junit.org/junit5/) (optional if tests are already integrated).  

## 📁 Key Files

| File / Folder                 | Description                                 |
|------------------------------|---------------------------------------------|
| `PhoneListController.java`   | Main logic for controlling application flow |
| `PhoneListService.java`      | Business rules                              |
| `PhoneListRepository.java`   | JSON file persistence logic                 |
| `ConsoleUI.java`             | Console input/output                        |
| `JsonUtil.java`              | Java object to JSON conversion and vice versa |
| `contacts.json`              | Data storage file                           |

## ✍️ Author

**Rodrigo A.**  
📍 Toronto / Spain  
💻 [LinkedIn, GitHub, or other links if you'd like to add them]
