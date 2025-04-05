package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public MedicalRecord() {}

    public String getDiagnosis() {return diagnosis;}
    public String getDoctorId() {return doctorId;}
    public String getPatientId(){return patientId;}
    public void setDoctorId(String doctorId) {this.doctorId = doctorId;}

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "recordId='" + recordId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", treatment='" + treatment + '\'' +
                ", doctorIds=" +  doctorId+
                '}';
    }
}
