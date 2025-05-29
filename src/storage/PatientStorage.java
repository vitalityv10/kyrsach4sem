package storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.Persons.Patient;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PatientStorage {
    private static final String FILE_PATH = "patients.json";

    public static void savePatients(List<Patient> patients) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(patients, writer);
        } catch (IOException e) {
            System.err.println("Помилка під час збереження лікарів: " + e.getMessage());
        }
    }

    public static List<Patient> loadPatients() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type patientListType = new TypeToken<ArrayList<Patient>>(){}.getType();
            return gson.fromJson(reader, patientListType);
        } catch (IOException e) {
            System.out.println("Файл не знайдено або пустий, створюємо новий список.");
            return new ArrayList<>();
        }
    }
}
