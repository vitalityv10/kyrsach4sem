package services;
import UI.AdminActions;
import entities.Appointment;
import entities.MedicalRecord;
import entities.Persons.*;
import entities.Persons.creation.PersonalInfo;
import entities.Persons.creation.Specialization;
import factories.DoctorFactory;
import factories.MedicalRecordFactory;
import factories.PatientFactory;
import storage.AppointmentRepository;
import storage.DoctorRepository;
import storage.PatientRepository;
import strategies.DoctorRemover;
import strategies.PatientRemover;
import strategies.SelectSpecialization;

import java.util.List;
import java.util.Scanner;

import static entities.Persons.creation.PersonalInfo.getPersonalInfo;

public class AdminService implements AdminActions {
    @Override
    public void addDoctor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Додавання нового лікаря:");
        PersonalInfo personalInfo = getPersonalInfo();

        System.out.println("Оберіть спеціалізацію:");
        Specialization[] specializations = Specialization.values();
        for (int i = 0; i < specializations.length; i++)
            System.out.println((i + 1) + ". " + specializations[i]);

        int specChoice;
        do {
            System.out.print("Виберіть номер спеціалізації: ");
            specChoice = scanner.nextInt();
        } while (specChoice < 1 || specChoice > specializations.length);

    Specialization specialization = specializations[specChoice - 1];

        Doctor newDoctor = new DoctorFactory().create(personalInfo.getFirstName(), personalInfo.getLastName(), personalInfo.getPhoneNumber(), personalInfo.getSex(), specialization);
        DoctorRepository.getInstance().addDoctor(newDoctor);

        System.out.println("Лікар успішно доданий: " + newDoctor);
    }
    @Override
    public void addPatient() {
        System.out.println("Додавання нового пацієнта:");

        PersonalInfo personalInfo = getPersonalInfo();
        Patient newPatient = new PatientFactory().create(personalInfo.getFirstName(), personalInfo.getLastName(), personalInfo.getPhoneNumber(), personalInfo.getSex(), null);
        MedicalRecord medicalRecord = new MedicalRecordFactory().create(newPatient.getID());
        newPatient.setMedicalRecord(medicalRecord);

        PatientRepository.getInstance().addPatient(newPatient);
        System.out.println("Пацієнт успішно доданий: " + newPatient);
    }

    @Override
    public void removeDoctor() {
        DoctorRemover doctorRemover = new DoctorRemover();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Виберіть, як ви хочете видалити лікаря: \n1. За спеціальністю \n2. За ім’ям та прізвищем \n3. Вийти");

            if (!sc.hasNextInt()) {
                System.out.println("Некоректне введення! Введіть число.");
                sc.next();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> {
                    Specialization specialization = SelectSpecialization.getSelectedSpecialization();
                    if (specialization!= null) {
                        doctorRemover.removeBySpecialization(specialization);
                    }
                }
                case 2 -> {
                    System.out.print("Введіть ім'я лікаря: ");
                    String firstName = sc.nextLine().trim();
                    System.out.print("Введіть прізвище лікаря: ");
                    String lastName = sc.nextLine().trim();
                    doctorRemover.removeByName(firstName, lastName);
                }
                case 3 -> {
                    System.out.println("Вихід.");
                    return;
                }
                default -> System.out.println("Некоректний вибір! Спробуйте ще раз.");
            }
        }
    }

    @Override
    public void removePatient() {
        PatientRemover patientRemover = new PatientRemover();
        Scanner sc = new Scanner(System.in);
        System.out.print("Введіть ім'я пацієнта: ");
        String firstName = sc.nextLine().trim();
        System.out.print("Введіть прізвище пацієнта: ");
        String lastName = sc.nextLine().trim();
        patientRemover.removeByName(firstName, lastName);}

    @Override
    public void viewALlAppointments() {
        List<Appointment> all = AppointmentRepository.getInstance().getAllAppointments();
        if (all.isEmpty()) System.out.println("🔎 Записів ще немає.");
         else all.forEach(System.out::println);}

    @Override
    public void managePatients() {PatientRepository.getInstance().getAllPatients().forEach(System.out::println);}
    @Override
    public void allDoctors() {DoctorRepository.getInstance().getAllDoctors().forEach(System.out::println);}
    @Override
    public void generateReport() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введіть потрібну дату (00.00.0000)");
        String date = sc.nextLine();
        ReportService  reportService = new ReportService();
        reportService.generateAdminReport(date);
        sc.close();}
}

