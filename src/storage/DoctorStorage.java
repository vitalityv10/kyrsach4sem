package storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entities.Persons.Doctor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DoctorStorage {
    private static final String FILE_PATH = "doctors.json";

    public static void saveDoctors(List<Doctor> doctors) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(doctors, writer);
        } catch (IOException e) {
            System.err.println("Помилка під час збереження лікарів: " + e.getMessage());
        }
    }

    public static List<Doctor> loadDoctors() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type doctorListType = new TypeToken<ArrayList<Doctor>>(){}.getType();
            return gson.fromJson(reader, doctorListType);
        } catch (IOException e) {
            System.out.println("Файл не знайдено або пустий, створюємо новий список.");
            return new ArrayList<>();
        }
    }
}
