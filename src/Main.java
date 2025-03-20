import proxies.ProxyMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Вітаємо в системі реєстрації пацієнтів!");
        System.out.println("Виберіть роль (admin, doctor, patient):");
        String role = scanner.next().toLowerCase();

        ProxyMenu proxyMenu = new ProxyMenu(role);
        proxyMenu.handleMenu();
    }
}