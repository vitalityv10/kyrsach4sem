package entities.Persons;

import entities.Persons.creation.Sex;

import java.util.Scanner;

public class Human {
    protected String ID, firstName, lastName, phoneNumber;
    protected Sex sex;

    public Human(String ID, String firstName, String lastName, String phoneNumber, Sex sex) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
    }
    public Human(String firstName, String lastName, String phoneNumber, Sex sex) {
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

    public void setID(String ID) {this.ID = ID;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public static Human getPersonalInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть ім'я: ");
        String firstName = scanner.nextLine();
        System.out.print("Введіть прізвище: ");
        String lastName = scanner.nextLine();
        System.out.print("Введіть номер телефону: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Оберіть стать (1 - Чоловік, 2 - Жінка): ");
        int sexChoice = scanner.nextInt();
        Sex sex = (sexChoice == 1) ? Sex.MALE : Sex.FEMALE;

        return new Human(firstName, lastName, phoneNumber, sex);
    }
}
