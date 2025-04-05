package factories;

import entities.MedicalRecord;


public class MedicalRecordFactory implements AbstractFactory<MedicalRecord>{
    private static int recordIdCounter = 1;
    private static int doctorIdCounter = 1;

    @Override
    public MedicalRecord create() {
        return null;
    }
    public MedicalRecord create(String patientId) {
        String recordId = "MR" + (recordIdCounter++);
        String diagnosis = "";
        String treatment = "";
       String doctorId = "D" + (doctorIdCounter++);

        return new MedicalRecord(recordId, patientId, diagnosis, treatment, doctorId);
    }

    public static int getRecordIdCounter() {
            return recordIdCounter;
        }
}
