package patterns;

import entities.Persons.Patient;

public class PatientDecorator{
    private final Patient patient;
    private final String address;

    public PatientDecorator(Patient patient, String address) {
        this.patient = patient;
        this.address = address;
    }
    public String getInfo() {
        return patient.toString() + ", Address='" + address + "'";
    }
}
