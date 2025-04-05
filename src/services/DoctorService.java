package services;

import UI.DoctorActions;

import java.util.Scanner;

public class DoctorService implements DoctorActions {
    @Override
    public void viewSchedule() {

    }

    @Override
    public void updateMedicalRecord() {

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
    public void viewAppointment() {

    }

    @Override
    public void updateProfile() {

    }
}
