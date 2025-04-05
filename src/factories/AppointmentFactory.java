package factories;

import entities.Appointment;

public class AppointmentFactory implements AbstractFactory<Appointment>{
    private static int appointmentCount = 1;
    @Override
    public Appointment create(){return null;}
    public Appointment create(String patientId, String doctorId, String date) {
        appointmentCount++;
        return new Appointment("A" + appointmentCount, patientId, doctorId, date);
    }
}
