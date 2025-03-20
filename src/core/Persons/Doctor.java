package core.Persons;

public class Doctor extends Human{
    Specialization specialization;

    public Doctor(String ID, String firstName, String lastName, String phoneNumber, Sex sex, Specialization specialization) {
        super(ID, firstName, lastName, phoneNumber, sex);
        this.specialization = specialization;
    }
}
