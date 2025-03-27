package entities;

public class Appointment {
    private String appointmentId,patientId, doctorId,
    date;

    public Appointment(String appointmentId, String patientId, String doctorId, String date) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
    }

    public Appointment(){
    }
}
