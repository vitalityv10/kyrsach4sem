package services;

import UI.AdminActions;
import entities.*;
import entities.Persons.*;
import entities.Persons.creation.Specialization;
import factories.*;
import storage.*;
import strategies.*;

import java.util.List;
import java.util.Scanner;

import static entities.Persons.Human.getPersonalInfo;
import static strategies.SelectSpecialization.getSelectedSpecialization;

public class AdminService implements AdminActions {
    @Override
    public void addDoctor() {
        System.out.println("Додавання нового лікаря:");
        Human personalInfo = getPersonalInfo();
        Specialization specialization = getSelectedSpecialization();

        Doctor newDoctor = new DoctorFactory().create(personalInfo.getFirstName(), personalInfo.getLastName(),
                personalInfo.getPhoneNumber(), personalInfo.getSex(), specialization);
        DoctorRepository.getInstance().addDoctor(newDoctor);

        System.out.println("Лікар успішно доданий: " + newDoctor);
    }
    @Override
    public void addPatient() {
        System.out.println("Додавання нового пацієнта:");

        Human personalInfo = getPersonalInfo();
        Patient newPatient = new PatientFactory().create(personalInfo.getFirstName(), personalInfo.getLastName(),
                personalInfo.getPhoneNumber(), personalInfo.getSex(), null);
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
                    Specialization specialization = getSelectedSpecialization();
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
        System.out.printf("%-15s %-15s %-15s %-20s",
                "ID Запису", "ID Пацієнта", "ID Лікаря", "Дата прийому");
        List<Appointment> all = AppointmentRepository.getInstance().getAllAppointments();
        if (all.isEmpty()) System.out.println("🔎 Записів ще немає.");
         else all.forEach(System.out::println);}

    @Override
    public void managePatients() {
    System.out.printf("%-10s %-15s %-15s %-15s %-10s %-20s%n",
            "ID", "Ім'я", "Прізвище", "Телефон", "Стать", "ID мед. карти");
        PatientRepository.getInstance().getAllPatients().forEach(System.out::println);
}
    @Override
    public void allDoctors() {
        System.out.printf("%-10s %-15s %-15s %-15s %-10s %-20s%n",
                "ID", "Ім'я", "Прізвище", "Телефон", "Стать", "Спеціалізація");
        DoctorRepository.getInstance().getAllDoctors().forEach(System.out::println);
    }

    @Override
    public void generateReport() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введіть потрібну дату (00-00-0000)");
        String date = sc.nextLine();
        ReportService  reportService = new ReportService();
        reportService.generateAdminReport(date);
        sc.close();}
}

