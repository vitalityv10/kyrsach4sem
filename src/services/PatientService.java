package services;

import UI.PatientActions;
import entities.Appointment;
import entities.MedicalRecord;
import entities.Persons.Patient;
import factories.AbstractFactory;
import storage.AppointmentRepository;
import storage.DoctorRepository;
import storage.PatientRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PatientService implements PatientActions {
    @Override
    public void bookAppointment() {

    }

    @Override
    public void cancelAppointment() {

    }

    @Override
    public void viewMedicalRecord() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введіть свій логін/ІД: ");
        String ID = sc.nextLine();
        MedicalRecord medicalRecord = getPatientByID(ID).
                get().getMedicalRecord();
        List<Appointment> appointments = AppointmentRepository.getInstance().getAllAppointments().stream()
                .filter(appointment -> appointment.getPatientId().equals(ID))
                .toList();

        System.out.printf("%-15s %-20s %-30s %-20s %-15s%n",
                "RecordID", "Діагноз", "Лікування", "Лікар", "Дата прийому");
        System.out.println("-----------------------------------------------------------------------------------------------");
        for (Appointment a : appointments) {
            String docName = getDoctorNameById(a.getDoctorId());
            System.out.printf("%-15s %-20s %-30s %-20s %-15s%n",
                    a.getAppointmentId(), medicalRecord.getDiagnosis(), medicalRecord.getTreatment(), docName, a.getDate());
        }
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

            Patient patient = getPatientByID(ID).get();

            System.out.print("Оберіть опцію (1-4): ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {System.out.print("Нове прізвище: ");
                    String newLastName = sc.nextLine();
                    patient.setLastName(newLastName);}
                case 2 ->{System.out.print("Новий логін/ID: ");
                    String newID = sc.nextLine();
                    patient.setID(newID);}
                case 3 -> {System.out.print("Новий номер телефону: ");
                    String newPhone = sc.nextLine();
                    patient.setPhoneNumber(newPhone);}
                case 4 -> {System.out.print("Новий пароль: ");
                    String newPassword = sc.nextLine();}
                default -> {System.out.println("Невірний вибір.");return;}
            }
            System.out.println("Профіль успішно оновлено: ");
            System.out.println(patient);
    }
    private static Optional<Patient> getPatientByID(String ID){
        return PatientRepository.getInstance().getAllPatients().stream()
                .filter(patient -> patient.getID().equals(ID))
                .findFirst();
    }
    private String getDoctorNameById(String doctorId) {
        return DoctorRepository.getInstance().getAllDoctors().stream()
                .filter(d -> d.getID().equals(doctorId))
                .map(d -> d.getFirstName() + " " + d.getLastName())
                .findFirst()
                .orElse("Невідомий лікар");
    }
}
