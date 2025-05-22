package patterns.observer;

import entities.Appointment;
public interface Observer {
    void update(Appointment patientCount);
}
