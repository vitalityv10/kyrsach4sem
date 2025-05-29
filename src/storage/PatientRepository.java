package storage;

import entities.Persons.Patient;
import patterns.factories.*;

import java.util.*;

public class PatientRepository {
    private static PatientRepository instance;
    private List<Patient> patients;

    public PatientRepository() {
        patients = PatientStorage.loadPatients();
        PatientFactory.initPatientIdCounter(patients);
    }

    public List<Patient> getAllPatients() {return patients;}
    public static PatientRepository getInstance(){
        if (instance == null) instance = new PatientRepository();
        return instance;
    }
    public void addPatient(Patient patient) {patients.add(patient); PatientStorage.savePatients(patients);}
    public Patient getPatientById(String id) {
        return patients.stream().filter(p -> p.getID().equals(id)).findFirst().orElse(null);
    }

}
