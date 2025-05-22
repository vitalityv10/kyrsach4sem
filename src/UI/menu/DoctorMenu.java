package UI.menu;

import services.DoctorService;

import java.util.Scanner;

public class DoctorMenu implements Menu {
    private final String id;
    public DoctorMenu(String id) {this.id = id;}
    @Override
    public void showMenu() {
        System.out.println("=============================================");
        System.out.println("           МЕНЮ ЛІКАРЯ                      ");
        System.out.println("1. Переглянути графік прийому");
        System.out.println("2. Оновити історію хвороби пацієнта");
        System.out.println("3. Згенерувати репорт");
        System.out.println("4. Оновити профіль");
        System.out.println("5. Вихід");
        System.out.println("=============================================");
    }

    @Override
    public void handleMenu() {
        DoctorService doctorService = new DoctorService(id);
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            showMenu();
            System.out.print("Виберіть опцію: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> doctorService.viewSchedule();
                case 2 -> doctorService.updateMedicalRecord();
                case 3 -> doctorService.generateReport();
                case 4 -> doctorService.updateProfile();
                case 5 -> System.out.println("Вихід...");
                default -> System.out.println("Неправильний вибір, спробуйте ще раз.");
            }
        } while (choice != 5);
    }
}
