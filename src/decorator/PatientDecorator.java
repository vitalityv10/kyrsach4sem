package decorator;

import entities.Persons.Patient;

public class PatientDecorator implements PatientInfo{
    private final Patient patient;
    private final String address;

    public PatientDecorator(Patient patient, String address) {
        this.patient = patient;
        this.address = address;
    }
    @Override
    public String getInfo() {
        return patient.toString() + ", Address='" + address + "'";
    }
}
