package UI.menu;

import java.util.Scanner;

public class PatientMenu implements Menu {
    @Override
    public void showMenu() {
        System.out.println("=== Меню пацієнта ===");
        System.out.println("1. Записатися на прийом");
        System.out.println("2. Скасувати запис");
        System.out.println("3. Переглянути історію хвороби");
        System.out.println("4. Оновити профіль");
        System.out.println("5. Вихід");
    }

    @Override
    public void handleMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            showMenu();
            System.out.println("Виберіть опцію: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> System.out.println("Запис на прийом...");
                case 2 -> System.out.println("Скасування запису...");
                case 3 -> System.out.println("Перегляд історії хвороби...");
                case 4 -> System.out.println("Оновлення профілю...");
                case 5 -> System.out.println("Вихід...");
                default -> System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        } while (choice != 5);
    }
}

