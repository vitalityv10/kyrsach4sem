package strategies;

import entities.Persons.Patient;
import storage.PatientRepository;

import java.util.List;

public class PatientsByDoctor {
        public static List<Patient> getPatientsByDoctor(String doctorId) {
            return PatientRepository.getInstance().getAllPatients().stream()
                    .filter(patient -> patient.getMedicalRecord() != null
                            && doctorId.equals(patient.getMedicalRecord().getDoctorId()))
                    .toList();
        }
    }

