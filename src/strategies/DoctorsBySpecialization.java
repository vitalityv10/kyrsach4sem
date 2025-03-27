package strategies;

import entities.Persons.Doctor;
import entities.Persons.Specialization;
import storage.DoctorRepository;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorsBySpecialization {
    public static List<Doctor> getDoctorsBySpecialization(Specialization specialization) {
       return DoctorRepository.getInstance().getAllDoctors().stream()
               .filter(doctor -> doctor.getSpecialization() == specialization)
               .collect(Collectors.toList());
    }

    public static List<Doctor> getDoctorsByName(String firstName, String lastName){
        return DoctorRepository.getInstance().getAllDoctors().stream()
                .filter(doctor -> doctor.getFirstName().equalsIgnoreCase(firstName) && doctor.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

}
