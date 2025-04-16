package entities.Persons;

import entities.Persons.creation.Sex;
import entities.Persons.creation.Specialization;

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
        return "Лікар{" +
                "ID='" + getID() + '\'' +
                ", Ім'я='" + getFirstName() + '\'' +
                ", Прізвище='" + getLastName() + '\'' +
                ", Телефон='" + getPhoneNumber() + '\'' +
                ", Стать=" + getSex() +
                ", Спеціалізація=" + specialization +
                '}';
    }
}
