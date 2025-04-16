package strategies;

import entities.Persons.creation.Specialization;

import java.util.Scanner;

public class SelectSpecialization {
    public static Specialization getSelectedSpecialization() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Оберіть спеціалізацію:");
        Specialization.printSpecializations();

        int specIndex = scanner.nextInt();
        Specialization[] specializations = Specialization.values();

        if (specIndex < 1 || specIndex > specializations.length) {
            System.out.println("Некоректний вибір спеціалізації!");
            return null;
        }
        return specializations[specIndex - 1];
    }
}
