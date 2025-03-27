package UI.menu;

import services.AdminService;

import java.util.Scanner;

public class AdminMenu implements Menu {
    @Override
    public void showMenu() {
        System.out.println("=== Адміністраторське меню ===");
        System.out.println("1. Додати лікаря");
        System.out.println("2. Додати пацієнта");
        System.out.println("3. Видалити лікаря");
        System.out.println("4. Переглянути всі записи на прийом");
        System.out.println("5. Керування пацієнтами"); // 6 керування лікарями
        System.out.println("6. Вихід");
        // курити бамбук
    }

    @Override
    public void handleMenu() {
        AdminService adminService = new AdminService();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            showMenu();
            System.out.println("Виберіть опцію: ");
            choice = sc.nextInt();
            switch (choice){
                case 1 -> adminService.addDoctor();
                case 2 -> adminService.addPatient();
                case 3 -> adminService.removeDoctor();
                case 4 -> adminService.viewALlAppointments();
                case 5 ->adminService.managePatients();
                case 6 -> System.out.println("6. Вихід");
                default -> System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        } while (choice != 6);
    }

}


