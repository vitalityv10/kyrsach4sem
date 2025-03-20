package UI.menu;

import java.util.Scanner;

public class AdminMenu implements Menu {
    @Override
    public void showMenu() {
        System.out.println("=== Адміністраторське меню ===");
        System.out.println("1. Додати лікаря");
        System.out.println("2. Видалити лікаря");
        System.out.println("3. Переглянути всі записи на прийом");
        System.out.println("4. Керування пацієнтами");
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
            switch (choice){
                case 1 -> System.out.println("Додавання лікаря...");
                case 2 -> System.out.println("Видалення лікаря...");
                case 3 -> System.out.println("Список записів на прийом...");
                case 4 -> System.out.println("Керування пацієнтами...");
                case 5 -> System.out.println("Вихід...");
                default -> System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        } while (choice != 5);
    }
}


