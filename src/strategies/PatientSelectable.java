package strategies;

import entities.Persons.Patient;

import java.util.*;

public class PatientSelectable implements Selectable<Patient> {
    @Override
    public Patient getSelection(List<Patient> patients) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Оберіть пацієнта:");
        for (int i = 0; i < patients.size(); i++) {
            System.out.println((i + 1) + ". " + patients.get(i).getFirstName() + " " + patients.get(i).getLastName());
        }
        int index = scanner.nextInt();
        if (index < 1 || index > patients.size()) {
            System.out.println("Некоректний вибір!");
            return null;
        }
        return patients.get(index - 1);
    }
}
