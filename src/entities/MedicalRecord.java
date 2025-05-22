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
    public String getRecordId() {return recordId;}
    public String getDiagnosis() {return diagnosis;}
    public String getDoctorId() {return doctorId;}
    public String getTreatment(){return treatment;}
    public void setDiagnosis(String diagnosis) {this.diagnosis = diagnosis;}
    public void setTreatment(String treatment) {this.treatment = treatment;}

    @Override
    public String toString() {
        return String.format("%-15s %-15s %-15s %-20s",
                recordId, patientId,diagnosis,
                treatment, doctorId);
    }
}
