package entities.Persons;

import entities.MedicalRecord;
import entities.Persons.creation.Sex;

public class Patient extends Human {
    MedicalRecord medicalRecord;

    public Patient(String ID, String firstName, String lastName, String phoneNumber, Sex sex, MedicalRecord medicalRecord) {
        super(ID, firstName, lastName, phoneNumber, sex);
        this.medicalRecord = medicalRecord;
    }

    public Patient() {}
    public MedicalRecord getMedicalRecord() {return medicalRecord;}
    @Override
    public String toString() {
        return String.format("%-10s %-15s %-15s %-15s %-10s %-20s",
                ID, firstName,
                lastName, phoneNumber,
                sex, (medicalRecord != null ? medicalRecord.getRecordId() : "Немає"));
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}
