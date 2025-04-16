package UI.menu;

import services.AdminService;

import java.util.Scanner;

import static entities.Persons.creation.PersonalInfo.getPersonalInfo;

public class Registration {
    public static void registration(){
        Scanner scanner = new Scanner(System.in);
        AdminService adminService = new AdminService();
        adminService.addPatient();
        System.out.print("Введіть пароль: ");
        String password1 = scanner.nextLine();
        System.out.print("Повторіть пароль: ");
        String password2 = scanner.nextLine();
    }
}
