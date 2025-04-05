package strategies;

import entities.Persons.Patient;
import storage.PatientRepository;

import java.util.List;

public class PatientRemover implements Removable<Patient> {

    @Override
    public void removeByName(String firstName, String lastName) {
        List<Patient> patients = PatientRepository.getInstance().getAllPatients().stream()
                .filter(patient -> patient.getFirstName().equalsIgnoreCase(firstName) && patient.getLastName().equalsIgnoreCase(lastName))
                .toList();
        if (patients.isEmpty()) {
            System.out.println("Пацієнта з таким ім'ям не знайдено.");
            return;
        }
        SelectionContext<Patient> context = new SelectionContext<>();
        context.setSelectionStrategy(new PatientSelectable());
        Patient patientToRemove = context.executeSelection(patients);
        if (patientToRemove != null) {
            PatientRepository.getInstance().getAllPatients().remove(patientToRemove);
            System.out.println("Пацієнт " + patientToRemove.getFirstName() + " " + patientToRemove.getLastName() + " видалений.");
        }
    }
}

