package entities;

public class Appointment implements Cloneable {
    private String appointmentId,patientId, doctorId,
    date;

    public Appointment(String appointmentId, String patientId, String doctorId, String date) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
    }

    public Appointment(){}

    public String getAppointmentId() {return appointmentId;}
    public String getPatientId() {return patientId;}
    public String getDate() {return date;}
    public String getDoctorId() {return doctorId;}
    @Override
    public String toString() {
        return String.format("%-15s %-15s %-15s %-20s",
                appointmentId, patientId,
                doctorId, date);
    }


    @Override
    public Appointment clone() {
        try {
            Appointment clone = (Appointment) super.clone();
            clone.patientId = this.patientId; clone.doctorId = this.doctorId;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}


