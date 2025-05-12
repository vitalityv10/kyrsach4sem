package storage;

import entities.Appointment;
import factories.AppointmentFactory;
import observer.ObserverManager;
import services.AppointmentService;

import java.util.*;

public class AppointmentRepository {
    private static AppointmentRepository instance;
    private List<Appointment> appointments;

    private AppointmentRepository(){}

    public static AppointmentRepository getInstance() {
        if (instance == null) {
            instance = new AppointmentRepository();
            AppointmentFactory factory = new AppointmentFactory();
            AppointmentService service = new AppointmentService(instance, new ObserverManager());
            instance.appointments = AppointmentInitializer.appointmentInitializer(factory, service);
        }
        return instance;
    }

    public List<Appointment> getAllAppointments() {return appointments != null ? appointments : new ArrayList<>();}

    public void addAppointment(Appointment appointment) {
        if (appointments == null) {appointments = new ArrayList<>();}
        appointments.add(appointment);
    }
}
