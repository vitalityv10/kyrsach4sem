package strategies;

import entities.Persons.Doctor;

import java.util.List;
import java.util.Scanner;

public class DoctorsSelection implements Selection<Doctor>{

    public Doctor getSelection(List<Doctor> doctors) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Оберіть лікаря:");
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = doctors.get(i);
            System.out.println((i + 1) + ". " + doctor.getFirstName() + " " + doctor.getLastName());
        }

        int doctorIndex = scanner.nextInt();
        if (doctorIndex < 1 || doctorIndex > doctors.size()) {
            System.out.println("Некоректний вибір лікаря!");
            return null;
        }

        return doctors.get(doctorIndex - 1);
    }
}
