package services;

import entities.Appointment;
import observers.Observer;
import observers.Subject;
import storage.AppointmentRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

public class AppointmentService implements Subject {
    private static final int MAX_PATIENTS_PER_DOCTOR = 4;
    private final List<Observer> observers = new ArrayList<>();
    private final AppointmentRepository appointmentRepository;
    private final Map<String, Integer> doctorPatientCount = new HashMap<>();

    public AppointmentService(AppointmentRepository repository) {
        this.appointmentRepository = repository;
        initializeDoctorPatientCount();
    }

    private void initializeDoctorPatientCount() {
        for (Appointment appointment : appointmentRepository.getAllAppointments()) {
            doctorPatientCount.put(appointment.getDoctorId(),
                    doctorPatientCount.getOrDefault(appointment.getDoctorId(), 0) + 1);
        }
    }
    public boolean canAcceptMorePatients(String doctorId) {
        int currentCount = doctorPatientCount.getOrDefault(doctorId, 0);
        return currentCount < MAX_PATIENTS_PER_DOCTOR;
    }


    @Override
    public void addObserver(Observer observer) {observers.add(observer);}
    @Override
    public void removeObserver(Observer observer) {observers.remove(observer);}
    @Override
    public void notifyObservers(Appointment appointment) {
        for (Observer observer : observers) observer.update(appointment);
    }
    public List<Observer> getObservers() {return observers;}

    public boolean createAppointment(Appointment appointment) {
        String doctorId = appointment.getDoctorId();
        int currentCount = doctorPatientCount.getOrDefault(doctorId, 0);

        if (currentCount >= MAX_PATIENTS_PER_DOCTOR) {
            System.out.println("❌ Ліміт пацієнтів для лікаря " + doctorId + " перевищено!");
            return false;
        }
        appointmentRepository.addAppointment(appointment);
        doctorPatientCount.put(doctorId, currentCount + 1);
        notifyObservers(appointment); // Сповіщення спостерігачів
        return true;
    }
}

