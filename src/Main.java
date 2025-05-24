import patterns.AuthFacade;
import storage.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PatientRepository.getInstance();
        DoctorRepository.getInstance();
        Scanner scanner = new Scanner(System.in);
        String role;
        do {
            System.out.println("╔══════════════════════════════════════════════╗");
            System.out.println("║           РЕЄСТРАЦІЯ В СИСТЕМІ               ║");
            System.out.println("║           МІСЬКОЇ ПОЛІКЛІНІКИ  v1.0          ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.println("║   Оберіть свою роль для подальших дій:       ║");
            System.out.println("║                                              ║");
            System.out.println("║   🔐 Адміністратор  ➤ Введіть: admin        ║");
            System.out.println("║   🩺 Лікар          ➤ Введіть: doctor       ║");
            System.out.println("║   👤 Пацієнт        ➤ Введіть: patient      ║");
            System.out.println("║      Вихід          ➤ Введіть: exit         ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.println("║     Розробник: ст. гр. ІП-23-2               ║");
            System.out.println("║     Вінтоняк Віталій Миколайович             ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            System.out.print("Ваш вибір ➤ ");
            AuthFacade facade = new AuthFacade();
            role = scanner.nextLine().toLowerCase().trim();
            if (!role.equals("exit")) {facade.handleAuth(role, scanner);}
        }  while (!role.equals("exit"));
    }
}