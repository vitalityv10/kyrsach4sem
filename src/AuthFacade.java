import proxies.ProxyMenu;
import storage.*;

import java.util.Scanner;

import static UI.menu.Registration.*;

public class AuthFacade {
        public boolean handleAuth(String role, Scanner scanner) {
            if (!validateRole(role)) {
                System.out.println("Невідома роль!");
                return false;
            }
            if (role.equals("patient")) {
                System.out.println("1. Авторизація");
                System.out.println("2. Зареєструватися");
                System.out.print("Ваш вибір: ");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice != 1) {
                    registration();
                    return false;
                }
            }
            System.out.print("Введіть ID (логін): ");
            String userId = scanner.nextLine();
            System.out.print("Введіть пароль: ");
            String password = scanner.nextLine();

            if (role.equals("admin")) {
                if (!password.equals("admin123")) {
                    System.out.println("Невірний пароль!");
                    return false;
                }
            } else if ((role.equals("patient") || role.equals("doctor")) &&
                    ((isExistD(userId) || isExistP(userId)) &&
                            (password.equals("password123") || getPasswords().containsValue(password)))) {
            } else {
                System.out.println("Невірний пароль!");
                return false;
            }

            ProxyMenu proxyMenu = new ProxyMenu(role);
            proxyMenu.handleMenu();
            return true;
        }
    private static boolean validateRole(String role) {
        return role.equals("admin") || role.equals("doctor") || role.equals("patient");
    }
    private static boolean isExistD(String ID){
        return DoctorRepository.getInstance().getAllDoctors().stream()
                .anyMatch(doctor -> doctor.getID().equals(ID));
    }
    private static boolean isExistP(String ID){
        return PatientRepository.getInstance().getAllPatients().stream()
                .anyMatch(doctor -> doctor.getID().equals(ID));
    }
}

