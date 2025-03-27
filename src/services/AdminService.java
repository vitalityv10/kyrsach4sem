package services;
import UI.AdminUI;
import entities.MedicalRecord;
import entities.Persons.*;
import factories.DoctorFactory;
import factories.MedicalRecordFactory;
import factories.PatientFactory;
import storage.DoctorRepository;
import storage.PatientRepository;
import strategies.DoctorsBySpecialization;
import strategies.DoctorsSelection;

import java.util.List;
import java.util.Scanner;

import static entities.Persons.PersonalInfo.getPersonalInfo;
import static entities.Persons.Specialization.printSpecializations;
import static strategies.SelectSpecialization.getSelectedSpecialization;

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
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Виберіть як ви хочете видалити лікаря, за спеціальністю чи по імені");
//        int choice = sc.nextInt();
//        switch (choice){
//            case 1 -> removeBySpecialization();
//            case 2 -> removeByName();
//        }

        Specialization selectedSpec = getSelectedSpecialization();
        if (selectedSpec == null) return;

        List<Doctor> doctorsBySpec = DoctorsBySpecialization.getDoctorsBySpecialization(selectedSpec);
        if (doctorsBySpec.isEmpty()) {
            System.out.println("Лікарів з такою спеціалізацією не знайдено.");
            return;
        }

        Doctor doctorToRemove = DoctorsSelection.getDoctorSelection(doctorsBySpec);
        if (doctorToRemove == null) return;

        DoctorRepository.getInstance().getAllDoctors().remove(doctorToRemove);
        System.out.println("Лікар " + doctorToRemove.getFirstName() + " " + doctorToRemove.getLastName() + " видалений.");
    }



    @Override
    public void viewALlAppointments() {

    }

    @Override
    public void managePatients() {

    }
}

