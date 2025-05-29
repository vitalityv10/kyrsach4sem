package services;

import UI.DoctorActions;
import entities.Appointment;
import entities.MedicalRecord;
import entities.Persons.Doctor;
import entities.Persons.Patient;
import storage.AppointmentRepository;
import storage.DoctorRepository;
import storage.PatientRepository;
import strategies.PatientSelectable;

import java.util.*;

import static entities.Persons.creation.Diagnosis.DIAGNOSES;
import static strategies.PatientsByDoctor.getPatientsByDoctor;

public class DoctorService implements DoctorActions {
    private final String id;
    public DoctorService(String id){
        this.id = id;
    }
    @Override
    public void viewSchedule() {
        System.out.println("Мій розклад: ");
        List<Appointment> appointments = AppointmentRepository.getInstance().getAllAppointments().stream()
                .filter(appointment -> appointment.getDoctorId().equals(id)).toList();

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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Мої пацієнти: ");
        List<Patient> patients = new ArrayList<>();
        List<Appointment> appointments = AppointmentRepository.getInstance().getAllAppointments().stream()
                .filter(appointment -> appointment.getDoctorId().equals(id)).toList();
        for (Appointment a : appointments){
            Patient patient1 = PatientRepository.getInstance().getPatientById(a.getPatientId());
            patients.add(patient1);
        }
        patients.addAll(getPatientsByDoctor(id));
        if (patients.isEmpty()) {
            System.out.println("У вас немає пацієнтів.");return;}

        PatientSelectable patientSelectable = new PatientSelectable();
        Patient patient = patientSelectable.getSelection(patients);

        if (patient == null) {
            System.out.println("Пацієнта не вибрано.");return;}

        MedicalRecord medicalRecord = patient.getMedicalRecord();
        System.out.println("Оновити діагноз. Доступні варіанти:");
        DIAGNOSES.getOrDefault(getDoctorById(id).get().getSpecialization(), List.of())
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
        System.out.print("Введіть назву звіту: ");
        String lastName = scanner.nextLine().trim();
        reportService.generateDoctorReport(lastName, id);
    }
    public void updateProfile() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Оновлення профілю: ");
        System.out.println("1. Змінити прізвище");
        System.out.println("2. Змінити логін");
        System.out.println("3. Змінити номер телефону");
        System.out.println("4. Змінити пароль");

        Doctor doctor = getDoctorById(id).get();

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
