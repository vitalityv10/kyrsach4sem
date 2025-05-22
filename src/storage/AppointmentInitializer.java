package storage;

import entities.Appointment;
import patterns.factories.AppointmentFactory;
import patterns.observer.*;
import services.AppointmentService;

import java.time.LocalDate;
import java.util.*;

public class AppointmentInitializer {
    private static final Random RANDOM = new Random();

    public static List<Appointment> appointmentInitializer(AppointmentFactory appointmentFactory, AppointmentService service) {
        List<Appointment> appointments = new ArrayList<>();
        ObserverManager observerManager = new ObserverManager();
        if (observerManager.getObservers().isEmpty()) {observerManager.addObserver(new AppointmentObserver());}
        observerManager.setEnabled(false);

        int numPatients = 15;
        int numDoctors = 15;

        for (int i = 1; i <= numPatients; i++) {
            String patientId = "P" + i;
            String doctorId = "D" + ((i - 1) % numDoctors + 1);
            String date = getRandomDate();

            Appointment appointment = appointmentFactory.create(patientId, doctorId, date);
            if (service.createAppointment(appointment)) appointments.add(appointment);

            if (RANDOM.nextBoolean() && service.canAcceptMorePatients(doctorId)) {
                Appointment secondAppointment = appointmentFactory.create(patientId, doctorId, getRandomDate());
                if (service.createAppointment(secondAppointment)) appointments.add(secondAppointment);
            }
        }
        observerManager.setEnabled(true);

        return appointments;
    }
     public static String getRandomDate() {
        LocalDate today = LocalDate.now();
        LocalDate randomDate = today.plusDays(RANDOM.nextInt(11));
        return randomDate.toString();
    }
}

