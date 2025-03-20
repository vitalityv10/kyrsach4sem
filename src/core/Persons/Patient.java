package core.Persons;

import core.MedicalRecord;

public class Patient extends Human {
    MedicalRecord medicalRecord;

    public Patient(String ID, String firstName, String lastName, String phoneNumber, Sex sex, MedicalRecord medicalRecord) {
        super(ID, firstName, lastName, phoneNumber, sex);
        this.medicalRecord = medicalRecord;
    }
}
