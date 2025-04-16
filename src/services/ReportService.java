package services;

import entities.MedicalRecord;
import entities.Persons.Doctor;
import entities.Persons.Patient;
import observer.ObserverManager;
import storage.AppointmentRepository;
import storage.DoctorRepository;
import strategies.PatientsByDoctor;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class ReportService {
    PatientsByDoctor patientsByDoctor;
    DoctorRepository doctorRepository = DoctorRepository.getInstance();
    public ReportService() {
        this.patientsByDoctor = new PatientsByDoctor();
    }

    // Звіт для адміна
    public void generateAdminReport(String date) {
        ObserverManager observerManager = new ObserverManager();
        String fileName = "admin_report.txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("=== Звіт для адміністратора ===\n\n");

            writer.write("Список лікарів:\n");
            for (Doctor doctor : doctorRepository.getAllDoctors()) {
                writer.write(doctor.getFirstName() + " " + doctor.getLastName() + " - " + doctor.getSpecialization() + "\n");
            }
            AppointmentService appointmentService = new AppointmentService(AppointmentRepository.getInstance(), observerManager);
            observerManager.setEnabled(false);
            int totalPatients = appointmentService.getTotalPatientsForMonth(date);
            observerManager.setEnabled(true);
            writer.write("\nЗагальна кількість пацієнтів за місяць: " + totalPatients + "\n");

            System.out.println("Звіт адміністратора створено у файлі " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка запису файлу: " + e.getMessage());
        }
    }

    // Звіт для лікаря
    public void generateDoctorReport(String doctorName, String doctorId) {
        String fileName = "doctor_report_" + doctorName.replace(" ", "_") + ".txt";
        List<Patient> patients = new ArrayList<>(patientsByDoctor.getPatientsByDoctor(doctorId));

        patients.sort(Comparator.comparing(Patient::getLastName));

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("=== Звіт для лікаря " + doctorName + " ===\n\n");
            writer.write("Список пацієнтів:\n");

            if (patients.isEmpty()) {
                writer.write("Немає пацієнтів.\n");
            } else {
                for (Patient patient : patients) {
                    MedicalRecord record = patient.getMedicalRecord();
                    String diagnosis = (record != null) ? record.getDiagnosis() : "Немає запису";
                    writer.write(patient.getFirstName() + " " + patient.getLastName() + " - Діагноз: " + diagnosis + "\n");
                }
            }
            System.out.println("Звіт лікаря створено у файлі " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка запису файлу: " + e.getMessage());
        }
    }
//    public List<Patient> sortPatientsByLastName(String doctorName) {
//        List<Patient> patients = patientsByDoctor.getPatientsByDoctor(doctorName);
//        patients.sort(Comparator.comparing(Patient::getLastName));
//        return patients;
//    }
}
