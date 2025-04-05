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

        for (int i = 0; i < 16; i++) patients.add(patientFactory.create());
//        patients.add(patientFactory.create("Іван", "Іванов", "380671234567", Sex.MALE,
//                new MedicalRecord("MR11", "P11", "Грип", "Постільний режим", "D11")));
//
//        patients.add(patientFactory.create("Олександр", "Петренко", "380671234568", Sex.MALE,
//                new MedicalRecord("MR12", "P12", "Отит", "Антибіотики", "D11")));
//
//        patients.add(patientFactory.create("Марія", "Шевченко", "380671234569", Sex.FEMALE,
//                new MedicalRecord("MR13", "P13", "Алергія", "Антигістамінні", "D11")));
//
//        patients.add(patientFactory.create("Юлія", "Ковальчук", "380671234570", Sex.FEMALE,
//                new MedicalRecord("MR14", "P14", "Мігрень", "Знеболювальне", "D11")));
//
//        patients.add(patientFactory.create("Дмитро", "Романюк", "380671234571", Sex.MALE,
//                new MedicalRecord("MR15", "P15", "Бронхіт", "Муколітики", "D11")));

        return patients;
    }
}
