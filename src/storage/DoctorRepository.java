package storage;

import entities.Persons.Doctor;
import entities.Persons.Sex;
import entities.Persons.Specialization;
import factories.DoctorFactory;

import java.util.ArrayList;
import java.util.List;

public class DoctorRepository {
    private static DoctorRepository instance;
    private List<Doctor> doctors;

    public DoctorRepository() {
        DoctorFactory factory = new DoctorFactory();
        this.doctors = DoctorInitializer.initializeDoctors(factory);
    }

    public List<Doctor> getAllDoctors() {
        return doctors;
    }
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }
    public static DoctorRepository getInstance() {
        if (instance == null) {
            instance = new DoctorRepository();
        }
        return instance;
    }
    public Doctor getDoctorById(String id) {
        return doctors.stream().filter(doc -> doc.getID().equals(id)).findFirst().orElseGet(null);
    }
}