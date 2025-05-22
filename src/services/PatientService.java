package services;

import patterns.State.AppointmentContext;
import patterns.State.Completed;
import patterns.State.Scheduled;
import UI.PatientActions;
import UI.menu.Registration;
import patterns.decorator.PatientDecorator;
import entities.Appointment;
import entities.MedicalRecord;
import entities.Persons.Doctor;
import entities.Persons.Patient;
import entities.Persons.creation.Specialization;
import patterns.factories.AppointmentFactory;
import patterns.observer.ObserverManager;
import storage.AppointmentRepository;
import storage.DoctorRepository;
import storage.PatientRepository;
import strategies.DoctorsSelectable;
import strategies.SelectSpecialization;
import strategies.SelectionContext;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static storage.AppointmentInitializer.getRandomDate;
import static strategies.DoctorsSelectable.getDoctorsBySpecialization;

public class PatientService implements PatientActions {
    private final Scanner sc = new Scanner(System.in);// Глобальний сканер
    private final String id;
    public PatientService(String id) {this.id = id;}
    @Override
    public void bookAppointment() {
        System.out.println("Запис на прийом до лікаря");
        Scanner sc = new Scanner(System.in);
        Specialization specialization = SelectSpecialization.getSelectedSpecialization();

        SelectionContext<Doctor> context = new SelectionContext<>();
        context.setSelectionStrategy(new DoctorsSelectable());
        Doctor doctorToChoice = context.executeSelection(getDoctorsBySpecialization(specialization));

        AppointmentFactory appointmentFactory = new AppointmentFactory();

        System.out.println("Виберіть доступну дату прийому: ");
        for(int i = 0 ; i < 10; i++)  System.out.println(getRandomDate());

        String date = sc.nextLine();
        Appointment appointment = appointmentFactory.create(id, doctorToChoice.getID(),date);

        AppointmentService service = new AppointmentService(AppointmentRepository.getInstance(), new ObserverManager());
        service.createAppointment(appointment);
        AppointmentContext context1=  new AppointmentContext();
        context1.setState(new Scheduled()); context1.apply();
    }

    @Override
    public void cancelAppointment() {
        viewMedicalRecord();
        System.out.println("Введіть ID запису який потрібно видалити: ");
        String appointmentID = sc.nextLine();
        Optional<Appointment> appointmentOptional = AppointmentRepository.getInstance().getAllAppointments().stream()
                .filter(appointment1 -> appointment1.getAppointmentId().equals(appointmentID)).findFirst();
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            boolean removed = AppointmentRepository.getInstance()
                    .getAllAppointments()
                    .remove(appointment);
            if (removed) {
                System.out.println("Запис успішно скасовано.");
            } else {System.out.println("Не вдалося скасувати запис.");}
        } else {
            System.out.println("Запис з таким ID не знайдено.");
        }
        AppointmentContext context1 = new AppointmentContext();
        context1.setState(new Completed()); context1.apply();
    }

    @Override
    public void viewMedicalRecord() {
        MedicalRecord medicalRecord = getPatientByID(id).
                get().getMedicalRecord();
        List<Appointment> appointments = AppointmentRepository.getInstance().getAllAppointments().stream()
                .filter(appointment -> appointment.getPatientId().equals(id))
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
        System.out.println("4. Додати адресу проживання");
        System.out.println("5. Змінити пароль");

          PatientDecorator patientDecorator;
            Patient patient = getPatientByID(id).get();
        String address = " ";
            System.out.print("Оберіть опцію (1-5): ");
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
                case 4 -> {System.out.print("Введіть адресу : ");
                    address = sc.nextLine();
                   // patientDecorator = new PatientDecorator(patient, address);
                }
                case 5 -> {System.out.print("Новий пароль: ");
                    Registration.changePassword(id);}
                default -> {System.out.println("Невірний вибір.");return;}
            }
        patientDecorator = new PatientDecorator(patient, address);
        System.out.println("Профіль успішно оновлено: ");
            System.out.println(patientDecorator.getInfo());
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
