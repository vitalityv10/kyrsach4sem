package services;

import UI.DoctorActions;
import entities.*;
import entities.Persons.*;
import storage.*;
import strategies.PatientSelectable;

import java.util.*;

import static entities.Persons.creation.Diagnosis.DIAGNOSES;
import static strategies.PatientsByDoctor.getPatientsByDoctor;

public class DoctorService implements DoctorActions {
    @Override
    public void viewSchedule() {
        System.out.println("Мій розклад: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть ІД лікаря: ");
        String doctorId = scanner.nextLine().trim();

        List<Appointment> appointments = AppointmentRepository.getInstance().getAllAppointments().stream()
                .filter(appointment -> appointment.getDoctorId().equals(doctorId)).toList();

        if (appointments.isEmpty()) {
            System.out.println("Немає записів");
            return;
        }

        System.out.printf("%-15s %-20s %-30s %-20s%n", "ID Запису", "Дата", "Пацієнт", "ІД Пацієнта");

        for (Appointment a : appointments) {
            Patient patient = PatientRepository.getInstance().getPatientById(a.getPatientId());
            String fullName = (patient != null)
                    ? patient.getFirstName() + " " + patient.getLastName()
                    : "Пацієнт не знайдений";

            System.out.printf("%-15s %-20s %-30s %-20s%n",
                    a.getAppointmentId(), a.getDate(), fullName, a.getPatientId());
        }
    }

    @Override
    public void updateMedicalRecord() {
        System.out.println("Мої пацієнти: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть свій ІД: ");
        String ID = scanner.nextLine().trim();
        List<Patient> patients = getPatientsByDoctor(ID);

        if (patients.isEmpty()) {
            System.out.println("У вас немає пацієнтів.");return;}

        PatientSelectable patientSelectable = new PatientSelectable();
        Patient patient = patientSelectable.getSelection(patients);

        if (patient == null) {
            System.out.println("Пацієнта не вибрано.");return;}

        MedicalRecord medicalRecord = patient.getMedicalRecord();
        System.out.println("Оновити діагноз. Доступні варіанти:");
        DIAGNOSES.getOrDefault(getDoctorById(ID).get().getSpecialization(), List.of())
                .forEach(System.out::println);
        System.out.print("Введіть назву діагнозу: ");
        String newDiagnosis = scanner.nextLine();
        medicalRecord.setDiagnosis(newDiagnosis);
        System.out.println("Оновити лікування: ");
        String newTreatment = scanner.nextLine();
        medicalRecord.setTreatment(newTreatment);
        System.out.println(medicalRecord);

    }

    @Override
    public void generateReport() {
        ReportService reportService = new ReportService();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть прізвище лікаря: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Введіть ID лікаря: ");
        String doctorId = scanner.nextLine().trim();

        reportService.generateDoctorReport(lastName, doctorId);
    }

    @Override
    public void updateProfile() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Оновлення профілю: ");
        System.out.println("1. Змінити прізвище");
        System.out.println("2. Змінити логін");
        System.out.println("3. Змінити номер телефону");
        System.out.println("4. Змінити пароль");

        System.out.print("Введіть свій логін/ІД: ");
        String ID = sc.nextLine();

        Doctor doctor = getDoctorById(ID).get();

        System.out.print("Оберіть опцію (1-4): ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1 -> {System.out.print("Нове прізвище: ");
                String newLastName = sc.nextLine();
                doctor.setLastName(newLastName);}
            case 2 ->{System.out.print("Новий логін/ID: ");
                String newID = sc.nextLine();
                doctor.setID(newID);}
            case 3 -> {System.out.print("Новий номер телефону: ");
                String newPhone = sc.nextLine();
                doctor.setPhoneNumber(newPhone);}
            case 4 -> {System.out.print("Новий пароль: ");
                String newPassword = sc.nextLine();}
            default -> {System.out.println("Невірний вибір.");return;}
        }
        System.out.println("Профіль успішно оновлено: ");
        System.out.println(doctor);
    }
    public static Optional<Doctor> getDoctorById(String ID){
        return DoctorRepository.getInstance().getAllDoctors().stream()
                .filter(doctor -> doctor.getID().equals(ID)).findFirst();
    }
}
