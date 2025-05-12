package entities.Persons;

import entities.Persons.creation.*;

public class Doctor extends Human{
    Specialization specialization;
    public Doctor(String ID, String firstName, String lastName, String phoneNumber, Sex sex, Specialization specialization) {
        super(ID, firstName, lastName, phoneNumber, sex);
        this.specialization = specialization;
    }
    public Specialization getSpecialization() {
        return specialization;
    }
    @Override
    public String toString() {
        return String.format("%-10s %-15s %-15s %-15s %-10s %-20s",
                getID(), getFirstName(),
                getLastName(), getPhoneNumber(),
                getSex(), (specialization != null ? specialization.toString() : "Невідомо"));
    }

}
