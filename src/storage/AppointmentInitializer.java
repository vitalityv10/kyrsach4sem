package storage;

import entities.Appointment;
import entities.MedicalRecord;
import entities.Persons.Patient;
import factories.AppointmentFactory;
import observers.AppointmentObserver;
import services.AppointmentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppointmentInitializer {
    private static final Random RANDOM = new Random();

    public static List<Appointment> appointmentInitializer(AppointmentFactory appointmentFactory, AppointmentService service) {
        List<Appointment> appointments = new ArrayList<>();

        if (service.getObservers().isEmpty()) {service.addObserver(new AppointmentObserver());}
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

        return appointments;
    }
     private static String getRandomDate() {
        LocalDate today = LocalDate.now();
        LocalDate randomDate = today.plusDays(RANDOM.nextInt(30));
        return randomDate.toString();
    }
}

