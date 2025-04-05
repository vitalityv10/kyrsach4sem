import entities.Appointment;
import entities.MedicalRecord;
import entities.Persons.Doctor;
import entities.Persons.Human;
import entities.Persons.Patient;
import factories.AppointmentFactory;
import proxies.ProxyMenu;
import services.AppointmentService;
import storage.*;

import java.util.List;
import java.util.Scanner;

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
        System.out.println("║          АВТОРИЗАЦІЯ В СИСТЕМІ               ║");
        System.out.println("║        МІС МІСЬКОЇ ПОЛІКЛІНІКИ v1.0          ║");
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
            System.out.print("Введіть ID (логін): ");
            String userId = scanner.nextLine();
            System.out.print("Введіть пароль: ");
            String password = scanner.nextLine();

            // Перевірка пароля для лікарів та пацієнтів
            if (role.equals("doctor") || role.equals("patient")) {
                if (patID.contains(userId) || docID.contains(userId)&&password.equals("password123")) {  // Однотипний пароль для лікарів та пацієнтів
                    ProxyMenu proxyMenu = new ProxyMenu(role);
                    proxyMenu.handleMenu();
                } else {
                    System.out.println("Невірний пароль!");
                }
            }
            // Перевірка пароля для адміністратора
            else if (role.equals("admin") && password.equals("admin123")) {
                ProxyMenu proxyMenu = new ProxyMenu(role);
                proxyMenu.handleMenu();
            } else {
                System.out.println("Невірний пароль!");
            }
        } else {
            System.out.println("Невідома роль!");
        }
    }

    private static boolean validateRole(String role) {
        return role.equals("admin") || role.equals("doctor") || role.equals("patient");
    }

    }
