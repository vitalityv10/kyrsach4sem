package entities.Persons.creation;

import java.util.Scanner;

public class PersonalInfo {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Sex sex;
    public PersonalInfo(String firstName, String lastName, String phoneNumber, Sex sex) {
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
    public static PersonalInfo getPersonalInfo() {
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

        return new PersonalInfo(firstName, lastName, phoneNumber, sex);
    }
}
