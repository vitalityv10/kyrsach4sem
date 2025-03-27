package storage;

import entities.MedicalRecord;
import entities.Persons.Patient;
import entities.Persons.Sex;
import factories.PatientFactory;

import java.util.ArrayList;
import java.util.List;

public class PatientInitializer {
    public static List<Patient> patientInitializer(PatientFactory patientFactory) {
        List<Patient> patients = new ArrayList<>();

        for (int i = 0; i < 10; i++) patients.add(patientFactory.create());
        patients.add(patientFactory.create( "Іван", "Іванов", "380671234567", Sex.MALE, null));
        patients.add(patientFactory.create("Олександр", "Петренко", "380671234568", Sex.MALE, null));
        patients.add(patientFactory.create("Марія", "Шевченко", "380671234569", Sex.FEMALE, null));
        patients.add(patientFactory.create( "Юлія", "Ковальчук", "380671234570", Sex.FEMALE,null));
        patients.add(patientFactory.create("Дмитро", "Романюк", "380671234571", Sex.MALE, new MedicalRecord("MR15", "P15", "Застуда", "Антибіотики", "D15")));
        return patients;
    }
}
