package storage;

import entities.Persons.Doctor;
import patterns.factories.*;

import java.util.*;

public class DoctorRepository {
    private static DoctorRepository instance;
    private List<Doctor> doctors = new ArrayList<>();

    public DoctorRepository() {
        FactoryMethod<Doctor> factory = new DoctorFactory();
        for (int i = 0; i < 16; i++) this.doctors.add(factory.create());
    }

    public List<Doctor> getAllDoctors() {return doctors;}

    public void addDoctor(Doctor doctor) {doctors.add(doctor);}

    public static DoctorRepository getInstance() {
        if (instance == null) {instance = new DoctorRepository();}
        return instance;
    }
}