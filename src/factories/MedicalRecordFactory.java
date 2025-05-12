package factories;

import entities.MedicalRecord;
import entities.Persons.creation.Specialization;

import java.util.List;
import java.util.Random;

import static entities.Persons.creation.Diagnosis.*;

public class MedicalRecordFactory implements AbstractFactory<MedicalRecord>{
    private static int recordIdCounter = 0;
    private static int doctorIdCounter = 0;
    private final Random random = new Random();

    @Override
    public MedicalRecord create() { return null;}
    public MedicalRecord create(String patientId) {
        Specialization[] specs = Specialization.values();
        Specialization selectedSpec = specs[random.nextInt(specs.length)] ;
//        List<String> doctors = DoctorRepository.getInstance().getAllDoctors().stream()
//                .map(Human::getID).toList();
        List<String> possibleDiagnoses = DIAGNOSES.get(selectedSpec);
        String diagnosis = possibleDiagnoses.get(random.nextInt(possibleDiagnoses.size()));
        String treatment = TREATMENTS.getOrDefault(diagnosis, "Консультація");
        String recordId = "MR" + (++recordIdCounter);
       String doctorId = "D" + (++doctorIdCounter);
        //String doctorId = String.valueOf(random.nextInt(doctors.size()));
        if(recordIdCounter - 1 < 16){return new MedicalRecord(recordId, patientId, diagnosis, treatment, doctorId); }
        return new MedicalRecord(recordId, patientId, "", "", doctorId);
    }
}
