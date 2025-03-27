package factories;

import entities.MedicalRecord;

public class MedicalRecordFactory implements AbstractFactory<MedicalRecord>{
        private static int recordIdCounter = 101;  // Початковий номер для ідентифікаторів медичних карток

    @Override
    public MedicalRecord create() {
        return null;
    }
        public MedicalRecord create(String patientId) {
            String recordId = "MR" + (recordIdCounter++);
            String diagnosis = "";
            String treatment = "";
            String doctorId = "";

            return new MedicalRecord(recordId, patientId, diagnosis, treatment, doctorId);
        }
        public static int getRecordIdCounter() {
            return recordIdCounter;
        }


}
