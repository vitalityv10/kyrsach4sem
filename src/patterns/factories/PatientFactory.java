package patterns.factories;

import entities.MedicalRecord;
import entities.Persons.Patient;
import entities.Persons.creation.Sex;

import java.util.List;
import java.util.Random;

public class PatientFactory implements FactoryMethod<Patient> {
    private static int patientIdCounter = 1;
    private static final String[] FIRST_NAMES = {"Андрій", "Софія", "Михайло", "Ірина", "Дмитро", "Юлія", "Анастасія", "Олексій", "Людмила"};
    private static final String[] LAST_NAMES = {"Ковальчук", "Шевченко", "Гречко", "Кулікова", "Романова", "Шимчук", "Завадський", "Грінченко", "Панасюк", "Чорний"};
    private static final Random RANDOM = new Random();

    @Override
    public Patient create() {
        String id = "P" + (patientIdCounter++);
        String firstName = FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)];
        String phoneNumber = "38067" + (RANDOM.nextInt(9000000) + 1000000);
        Sex sex = RANDOM.nextBoolean() ? Sex.MALE : Sex.FEMALE;

        MedicalRecord medicalRecord = new MedicalRecordFactory().create(id);
        return new Patient(id, firstName, lastName, phoneNumber, sex, medicalRecord);
    }
    public Patient create(String firstName, String lastName, String phoneNumber, Sex sex, MedicalRecord medicalRecord) {
        String patientId = "P" + (patientIdCounter++);

        if (medicalRecord == null ) medicalRecord = new MedicalRecordFactory().create(patientId);
        return new Patient(patientId, firstName, lastName, phoneNumber, sex, medicalRecord);
    }
    public static void initPatientIdCounter(List<Patient> existingDoctors) {
        int maxId = 0;
        for (Patient patient : existingDoctors) {
            try {
                String idStr = patient.getID().replace("P", "");
                int num = Integer.parseInt(idStr);
                if (num > maxId) maxId = num;
            } catch (NumberFormatException ignored) {}
        }
        patientIdCounter = maxId + 1;
    }
}

