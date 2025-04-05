package entities.Persons;

public abstract class Human {
    protected String ID, firstName, lastName, phoneNumber;
    protected Sex sex;

    public Human(String ID, String firstName, String lastName, String phoneNumber, Sex sex) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public Sex getSex() {
        return sex;
    }
    public Human() {}
    public String getID(){
        return ID;
    }
}
