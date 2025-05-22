package services;

import patterns.builder.ReportBuilder;
import entities.Persons.Patient;
import storage.*;
import strategies.PatientsByDoctor;

import java.io.*;
import java.util.*;

public class ReportService {
    PatientsByDoctor patientsByDoctor;
    DoctorRepository doctorRepository = DoctorRepository.getInstance();
    public ReportService() {
        this.patientsByDoctor = new PatientsByDoctor();
    }

    public void generateAdminReport(String date) {
        String fileName = "admin_report.txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            ReportBuilder builder = new ReportBuilder();

            builder.addTitle("Звіт для адміністратора").addDoctors(doctorRepository.getAllDoctors());
            builder.addPatients(PatientRepository.getInstance().getAllPatients());
            writer.write(builder.build());

            System.out.println("Звіт адміністратора створено у файлі " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка запису файлу: " + e.getMessage());}
    }
    public void generateDoctorReport(String doctorName, String doctorId) {
        String fileName = "doctor_report_" + doctorName.replace(" ", "_") + ".txt";
        List<Patient> patients = new ArrayList<>(patientsByDoctor.getPatientsByDoctor(doctorId));

        patients.sort(Comparator.comparing(Patient::getLastName));

        try (FileWriter writer = new FileWriter(fileName)) {
            ReportBuilder builder = new ReportBuilder();

            builder.addTitle("Звіт для лікаря").addPatients(patients);
            writer.write(builder.build());

            System.out.println("Звіт лікаря створено у файлі " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка запису файлу: " + e.getMessage());
        }
    }
}
