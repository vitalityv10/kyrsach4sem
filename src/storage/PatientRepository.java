package storage;

import entities.Persons.Patient;
import factories.*;

import java.util.*;

public class PatientRepository {
    private static PatientRepository instance;
    private final List<Patient> patients =  new ArrayList<>();

    public PatientRepository() {
        AbstractFactory<Patient> patientFactory = new PatientFactory();
        for (int i = 0; i < 16; i++) this.patients.add(patientFactory.create());
    }

    public List<Patient> getAllPatients() {return patients;}
    public static PatientRepository getInstance(){
        if (instance == null) instance = new PatientRepository();
        return instance;
    }
    public void addPatient(Patient patient) {patients.add(patient);}
    public Patient getPatientById(String id) {
        return patients.stream().filter(p -> p.getID().equals(id)).findFirst().orElse(null);
    }

}
