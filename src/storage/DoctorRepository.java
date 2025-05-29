package storage;

import entities.Persons.Doctor;
import patterns.factories.DoctorFactory;

import java.util.List;

public class DoctorRepository {
    private static DoctorRepository instance;
    private List<Doctor> doctors;

//    public DoctorRepository() {
//        FactoryMethod<Doctor> factory = new DoctorFactory();
//        for (int i = 0; i < 16; i++) this.doctors.add(factory.create());
//    }
private DoctorRepository() {
    doctors = DoctorStorage.loadDoctors();
    DoctorFactory.initDoctorIdCounter(doctors); // 🔥 ключова зміна
}
    public List<Doctor> getAllDoctors() {return doctors;}

    public void addDoctor(Doctor doctor) {doctors.add(doctor); DoctorStorage.saveDoctors(doctors);}

    public static DoctorRepository getInstance() {
        if (instance == null) {instance = new DoctorRepository();}
        return instance;
    }
}