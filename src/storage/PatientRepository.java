package storage;

import entities.Persons.Patient;
import factories.PatientFactory;

import java.util.ArrayList;
import java.util.List;

public class PatientRepository {
    private static PatientRepository instance;
    private List<Patient> patients;

    public PatientRepository() {
        PatientFactory patientFactory = new PatientFactory();
        this.patients = PatientInitializer.patientInitializer(patientFactory);
    }

    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients);
    }

    public static PatientRepository getInstance(){
        if (instance == null) {
            instance = new PatientRepository();
        }
        return instance;
    }
    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public Patient getPatientById(String id) {
        return patients.stream().filter(p -> p.getID().equals(id)).findFirst().orElse(null);
    }
}
