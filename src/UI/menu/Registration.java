package UI.menu;

import services.AdminService;
import java.util.*;

public class Registration {
    public static Map<String, String> passwords = new HashMap<>();
    public static void registration(){

        Scanner scanner = new Scanner(System.in);
        AdminService adminService = new AdminService();
        adminService.addPatient();
        System.out.print("Введіть ваш ІД: ");
        String ID = scanner.nextLine();
        String password1, password2;
        do {
            System.out.print("Введіть пароль: ");
            password1 = scanner.nextLine();
            System.out.print("Повторіть пароль: ");
            password2 = scanner.nextLine();

            if (!password1.equals(password2)) {
                System.out.println("Паролі не співпадають! Спробуйте ще раз.");}
        } while (!password1.equals(password2));

        passwords.put(ID, password1);
    }

    public static void changePassword(String id) {
        Scanner sc = new Scanner(System.in);
        if (!passwords.containsKey(id)) {
            System.out.println("Користувача з таким ID не знайдено.");
            return;
        }
        System.out.print("Введіть старий пароль: ");
        String oldPassword = sc.nextLine();

        if (!passwords.get(id).equals(oldPassword)) {
            System.out.println("Неправильний пароль.");
            return;
        }
        System.out.print("Новий пароль: ");
        String newPassword = sc.nextLine(); passwords.put(id, newPassword);
        System.out.println("Пароль успішно змінено.");
    }
    public static Map<String, String> getPasswords() {return passwords;}
}
