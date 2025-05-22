package patterns.builder;

import entities.MedicalRecord;
import entities.Persons.*;

import java.util.List;

public class ReportBuilder {
    private StringBuilder content = new StringBuilder();

    public ReportBuilder addTitle(String title) {
        content.append("=== ").append(title).append(" ===\n\n");
        return this;}

    public void addDoctors(List<Doctor> doctors) {
        content.append("Список лікарів:\n");
        for (Doctor doctor : doctors) {
            content.append(doctor.getFirstName())
                    .append(" ").append(doctor.getLastName())
                    .append(" - ").append(doctor.getSpecialization())
                    .append("\n");}}

    public void addPatients(List<Patient> patients) {
        content.append("Список пацієнтів:\n");
        if (patients.isEmpty()) {
            content.append("Немає пацієнтів.\n");
        } else {
            for (Patient patient : patients) {
                MedicalRecord record = patient.getMedicalRecord();
                String diagnosis = (record != null) ? record.getDiagnosis() : "Немає запису";
                content.append(patient.getFirstName()).append(" ")
                        .append(patient.getLastName()).append(" - Діагноз: ")
                        .append(diagnosis).append("\n");
            }}}

    public String build() {return content.toString();}
}

