package storage;


import entities.Persons.Doctor;
import entities.Persons.creation.Sex;
import entities.Persons.creation.Specialization;
import factories.DoctorFactory;

import java.util.ArrayList;
import java.util.List;

public class DoctorInitializer {
    public static List<Doctor> initializeDoctors(DoctorFactory factory) {
        List<Doctor> doctors = new ArrayList<>();

        for (int i = 0; i < 10; i++) doctors.add(factory.create());
        doctors.add(factory.create( "Іван", "Іванов", "380671234567", Sex.MALE, Specialization.SURGEON));
        doctors.add(factory.create("Марія", "Сидоренко", "380672345678", Sex.FEMALE, Specialization.CARDIOLOGIST));
        doctors.add(factory.create( "Петро", "Козак", "380673456789", Sex.MALE, Specialization.DERMATOLOGIST));
        doctors.add(factory.create("Ольга", "Мельник", "380674567890", Sex.FEMALE, Specialization.NEUROLOGIST));
        doctors.add(factory.create("Олександр", "Бондаренко", "380675678901", Sex.MALE, Specialization.SURGEON));

        return doctors;
    }
}
