import proxies.ProxyMenu;
import storage.DoctorRepository;
import storage.PatientRepository;
import entities.Persons.Human;
import java.util.List;
import java.util.Scanner;

import static UI.menu.Registration.registration;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> docID = DoctorRepository.getInstance().getAllDoctors().stream()
                .map(Human::getID)
                .toList();
        List<String> patID = PatientRepository.getInstance().getAllPatients().stream()
                .map(Human::getID)
                .toList();

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║           РЕЄСТРАЦІЯ В СИСТЕМІ               ║");
        System.out.println("║           МІСЬКОЇ ПОЛІКЛІНІКИ  v1.0          ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║   Оберіть свою роль для подальших дій:       ║");
        System.out.println("║                                              ║");
        System.out.println("║   🔐 Адміністратор  ➤ Введіть: admin        ║");
        System.out.println("║   🩺 Лікар          ➤ Введіть: doctor       ║");
        System.out.println("║   👤 Пацієнт        ➤ Введіть: patient      ║");
        System.out.println("╠══════════════════════════════════════════════╣");
        System.out.println("║     Розробник: ст. гр. ІП-23-2               ║");
        System.out.println("║     Вінтоняк Віталій Миколайович             ║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.print  ("Ваш вибір ➤ ");

        String role = scanner.nextLine().toLowerCase();

        if (validateRole(role)) {
            if (role.equals("patient")) {
                System.out.println("1. Авторизація");
                System.out.println("2. Зареєструватися");
                System.out.print("Ваш вибір: ");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice != 1) {
                    registration();
                    return;
                }
            }
            System.out.print("Введіть ID (логін): ");
            String userId = scanner.nextLine();
            System.out.print("Введіть пароль: ");
            String password = scanner.nextLine();

            if ((role.equals("patient") || role.equals("doctor")) &&
                    ((patID.contains(userId) || docID.contains(userId)) && password.equals("password123"))) {

                ProxyMenu proxyMenu = new ProxyMenu(role);
                proxyMenu.handleMenu();

            } else if (role.equals("admin")) {
                if (password.equals("admin123")) {
                    ProxyMenu proxyMenu = new ProxyMenu(role);
                    proxyMenu.handleMenu();
                } else {System.out.println("Невірний пароль!");}
            } else {System.out.println("Невірний пароль!");}
        } else {System.out.println("Невідома роль!");}
    }

    private static boolean validateRole(String role) {
        return role.equals("admin") || role.equals("doctor") || role.equals("patient");
    }
}
