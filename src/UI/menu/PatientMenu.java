package UI.menu;

import services.PatientService;

import java.util.Scanner;

public class PatientMenu implements Menu {
    private final String id;
    public PatientMenu(String id) {this.id = id;}
    @Override
    public void showMenu() {
        System.out.println("=============================================");
        System.out.println("              МЕНЮ ПАЦІЄНТА                |*|");
        System.out.println("1. Записатися на прийом                    |*|");
        System.out.println("2. Скасувати запис                         |*|");
        System.out.println("3. Переглянути історію хвороби             |*|");
        System.out.println("4. Оновити профіль                         |*|");
        System.out.println("5. Вихід                                   |*|");
        System.out.println("=============================================");
    }

    @Override
    public void handleMenu() {
        Scanner sc = new Scanner(System.in);
        PatientService patientService = new PatientService(id);
        int choice;
        do {
            showMenu();
            System.out.println("Виберіть опцію: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> patientService.bookAppointment();
                case 2 -> patientService.cancelAppointment();
                case 3 -> patientService.viewMedicalRecord();
                case 4 -> patientService.updateProfile();
                case 5 -> System.out.println("Вихід...");
                default -> System.out.println("Неправильний вибір, спробуйте ще раз.");
            }
        } while (choice != 5);
    }
}

