package services;
import UI.AdminUI;
import entities.MedicalRecord;
import entities.Persons.*;
import factories.DoctorFactory;
import factories.MedicalRecordFactory;
import factories.PatientFactory;
import storage.DoctorRepository;
import storage.PatientRepository;
import strategies.DoctorRemover;
import strategies.PatientRemover;
import strategies.SelectSpecialization;

import java.util.Scanner;

import static entities.Persons.PersonalInfo.getPersonalInfo;

public class AdminService implements AdminUI {
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
        Scanner scanner = new Scanner(System.in);
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
        patientRemover.removeByName(firstName, lastName);
    }


    @Override
    public void viewALlAppointments() {

    }

    @Override
    public void managePatients() {

    }
}

