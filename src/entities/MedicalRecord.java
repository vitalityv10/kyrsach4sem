package entities;

public class MedicalRecord {
    private String recordId, patientId, diagnosis,
            treatment, doctorId;

    public MedicalRecord(String recordId, String patientId, String diagnosis, String treatment, String doctorId) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.doctorId = doctorId;
    }

    public MedicalRecord() {

    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "recordId='" + recordId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", treatment='" + treatment + '\'' +
                ", doctorId='" + doctorId + '\'' +
                '}';
    }
}
