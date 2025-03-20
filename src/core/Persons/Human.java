package core.Persons;

public abstract class Human {
    protected String ID, firstName,
    lastName, phoneNumber;
    protected Sex sex;

    public Human(String ID, String firstName, String lastName, String phoneNumber, Sex sex) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
    }
}
