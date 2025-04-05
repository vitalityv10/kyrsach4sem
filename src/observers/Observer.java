package observers;

import entities.Appointment;

public interface Observer {
    void update(Appointment patientCount);
}
