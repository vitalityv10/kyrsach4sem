package services;

import entities.Appointment;
import observer.ObserverManager;
import storage.AppointmentRepository;

import java.util.*;

public class AppointmentService  {
    private static final int MAX_PATIENTS_PER_DOCTOR = 4;
    private final AppointmentRepository appointmentRepository;
    private final Map<String, Integer> doctorPatientCount = new HashMap<>();
    private ObserverManager observerManager;

    public AppointmentService(AppointmentRepository repository, ObserverManager manager) {
        this.appointmentRepository = repository;
        this.observerManager = manager;
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

    public boolean createAppointment(Appointment appointment) {
        String doctorId = appointment.getDoctorId();
        int currentCount = doctorPatientCount.getOrDefault(doctorId, 0);

        if (currentCount >= MAX_PATIENTS_PER_DOCTOR) {
            System.out.println("❌ Ліміт пацієнтів для лікаря " + doctorId + " перевищено!");
            return false;
        }
        appointmentRepository.addAppointment(appointment);
        doctorPatientCount.put(doctorId, currentCount + 1);
        observerManager.notifyObservers(appointment); // Сповіщення спостерігачів
        return true;
    }

    public int getTotalPatientsForMonth(String month) {
        Set<String> uniquePatientIds = new HashSet<>();

        for (Appointment appointment : appointmentRepository.getAllAppointments()) {
            if (appointment.getDate().startsWith(month))
                uniquePatientIds.add(appointment.getPatientId());
        }
        return uniquePatientIds.size();
    }
}

