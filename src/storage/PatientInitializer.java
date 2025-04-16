package storage;

import entities.Persons.Patient;
import factories.PatientFactory;

import java.util.ArrayList;
import java.util.List;

public class PatientInitializer {
    public static List<Patient> patientInitializer(PatientFactory patientFactory) {
        List<Patient> patients = new ArrayList<>();
        for (int i = 0; i < 16; i++) patients.add(patientFactory.create());
        return patients;
    }
}
