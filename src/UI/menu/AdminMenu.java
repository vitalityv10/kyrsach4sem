package UI.menu;

import services.AdminService;

import java.util.Scanner;

public class AdminMenu implements Menu {
    @Override
    public void showMenu() {
        System.out.println("=============================================");
        System.out.println("         МЕНЮ АДМІНІСТРАТОРА                ");
        System.out.println("1. Додати лікаря");
        System.out.println("2. Додати пацієнта");
        System.out.println("3. Видалити лікаря");
        System.out.println("4. Видалити пацієнта");
        System.out.println("5. Переглянути всі записи на прийом");
        System.out.println("6. Керування пацієнтами");
        System.out.println("7. Керування лікарями");// 6 керування лікарями// 6 керування лікарями
        System.out.println("8. Вихід");
        System.out.println("=============================================");
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
                case 4 -> adminService.removePatient();
                case 5 -> adminService.viewALlAppointments();
                case 6 -> adminService.managePatients();
                case 7 -> adminService.allDoctors();
                case 8 -> System.out.println("Вихід");
                default -> System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        } while (choice != 8);
    }

}


