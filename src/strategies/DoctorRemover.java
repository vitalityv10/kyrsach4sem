package strategies;

import entities.Persons.Doctor;
import entities.Persons.creation.Specialization;
import storage.DoctorRepository;

import java.util.List;
public class DoctorRemover implements SpecializationRemovable<Doctor> {
   private final SelectionContext<Doctor> context = new SelectionContext<>();
    private final DoctorRepository doctorRepository = DoctorRepository.getInstance();
    @Override
    public void removeBySpecialization(Specialization specialization) {
        List<Doctor> doctors = doctorRepository.getAllDoctors().stream()
                .filter(doctor -> doctor.getSpecialization() == specialization)
                .toList();
        if(doctors.isEmpty()) {
            System.out.println("Лікарів з такою спеціалізацією не знайдено."); return;
        }

        context.setSelectionStrategy(new DoctorsSelectable());

        Doctor doctorToRemove = context.executeSelection(doctors);
        if (doctorToRemove != null) {
            doctorRepository.getAllDoctors().remove(doctorToRemove);
            System.out.println("Лікар " + doctorToRemove.getFirstName() + " " + doctorToRemove.getLastName() + " видалений.");
        }

    }

    @Override
    public void removeByName(String firstName, String lastName) {
        List<Doctor> doctors = doctorRepository.getAllDoctors().stream()
                .filter(doctor -> doctor.getFirstName().equalsIgnoreCase(firstName) && doctor.getLastName().equalsIgnoreCase(lastName))
                .toList();
        if (doctors.isEmpty()) {
            System.out.println("Лікаря з таким ім'ям не знайдено.");
            return;
        }
        context.setSelectionStrategy(new DoctorsSelectable());

        Doctor doctorToRemove = context.executeSelection(doctors);

        if (doctorToRemove != null) {
            boolean removed = doctorRepository.getAllDoctors().removeIf(d -> d.equals(doctorToRemove));
            if (removed) {
                System.out.println("Лікар " + doctorToRemove.getFirstName() + " " + doctorToRemove.getLastName() + " видалений.");
            } else {
                System.out.println("Помилка видалення лікаря.");
            }
        }

    }
}
