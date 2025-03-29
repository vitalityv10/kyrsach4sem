package UI.menu;

import services.DoctorService;

import java.util.Scanner;

public class DoctorMenu implements Menu {
    @Override
    public void showMenu() {
        System.out.println("=== Меню лікаря ===");
        System.out.println("1. Переглянути графік прийому");
        System.out.println("2. Оновити історію хвороби пацієнта");
        System.out.println("3. Згенерувати репорт"); //4 мої пацієнти 1) оновити історію хв 2) повторно записати
        System.out.println("4. Вихід");
    }

    @Override
    public void handleMenu() {
        DoctorService doctorService = new DoctorService();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            showMenu();
            System.out.print("Виберіть опцію: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> System.out.println("Перегляд графіку прийому...");
                case 2 -> System.out.println("Оновлення історії хвороби...");
                case 3 -> doctorService.generateReport();
                case 4 -> System.out.println("Вихід...");
                default -> System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        } while (choice != 3);
    }
}
