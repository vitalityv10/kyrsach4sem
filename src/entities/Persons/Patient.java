package entities.Persons;

import entities.MedicalRecord;

public class Patient extends Human {
    MedicalRecord medicalRecord;

    public Patient(String ID, String firstName, String lastName, String phoneNumber, Sex sex, MedicalRecord medicalRecord) {
        super(ID, firstName, lastName, phoneNumber, sex);
        this.medicalRecord = medicalRecord;
    }

    public Patient() {

    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "medicalRecord=" + medicalRecord +
                ", ID='" + ID + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", sex=" + sex +
                '}';
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}
