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

    public Appointment(){}

    public String getAppointmentId() {return appointmentId;}
    public String getPatientId() {return patientId;}
    public String getDate() {return date;}
    public String getDoctorId() {return doctorId;}
    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
   }


