package ra.dev.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ra.dev.model.Contact;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

    public class JsonUtil {
        private static final Gson gson = new Gson();
        private static final Type listType = new TypeToken<List<Contact>>() {}.getType();

        public static String toJson(List<Contact> contactList) {
            return gson.toJson(contactList);
        }

        public static List<Contact> fromJson(String json) {
            if (json == null || json.isEmpty()) {
                return new ArrayList<>(); // Retorna una lista vacía en lugar de null
            }
            List<Contact> contacts = gson.fromJson(json, listType);
            return (contacts != null) ? contacts : new ArrayList<>();
        }

    }
