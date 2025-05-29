package patterns.factories;

import entities.Persons.Doctor;
import entities.Persons.creation.Sex;
import entities.Persons.creation.Specialization;

import java.util.List;
import java.util.Random;

public class DoctorFactory implements FactoryMethod<Doctor> {
    private static int doctorIdCounter = 1;
    private static final String[] FIRST_NAMES = {"Іван", "Петро", "Марія", "Ольга", "Олександр", "Ганна", "Віталій", "Роман", "Катерина"};
    private static final String[] LAST_NAMES = {"Іванов", "Петров", "Сидоренко", "Коваленко", "Мельник", "Ткаченко", "Гнатюк", "Бондаренко", "Романюк", "Козак"};
    private static final Specialization[] SPECIALIZATIONS = Specialization.values();
    private static final Random RANDOM = new Random();

    @Override
    public Doctor create() {
        String id = "D" + (doctorIdCounter++);
        String firstName = FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)];
        String phoneNumber = "38067" + (RANDOM.nextInt(9000000) + 1000000);
        Sex sex = RANDOM.nextBoolean() ? Sex.MALE : Sex.FEMALE;
        Specialization specialization = SPECIALIZATIONS[RANDOM.nextInt(SPECIALIZATIONS.length)];

        return new Doctor(id, firstName, lastName, phoneNumber, sex, specialization);
    }
    public Doctor create(String firstName, String lastName, String phoneNumber, Sex sex, Specialization specialization) {
        return new Doctor("D" + (doctorIdCounter++), firstName, lastName, phoneNumber, sex, specialization);
    }
    public static void initDoctorIdCounter(List<Doctor> existingDoctors) {
        int maxId = 0;
        for (Doctor doctor : existingDoctors) {
            try {
                String idStr = doctor.getID().replace("D", "");
                int num = Integer.parseInt(idStr);
                if (num > maxId) maxId = num;
            } catch (NumberFormatException ignored) {}
        }
        doctorIdCounter = maxId + 1;
    }
}
