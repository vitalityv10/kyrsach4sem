import storage.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PatientRepository.getInstance();
        DoctorRepository.getInstance();
        Scanner scanner = new Scanner(System.in);
        while (true) {
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
            System.out.print("Ваш вибір ➤ ");
            AuthFacade facade = new AuthFacade();
            String role = scanner.nextLine().toLowerCase();
            facade.handleAuth(role, scanner);
        }
    }
}