package proxies;

import UI.menu.AdminMenu;
import UI.menu.DoctorMenu;
import UI.menu.Menu;
import UI.menu.PatientMenu;

public class ProxyMenu implements Menu {
    private Menu menu;

    public ProxyMenu(String role) {
        switch (role.toLowerCase()) {
            case "admin" -> menu = new AdminMenu();
            case "doctor" -> menu = new DoctorMenu();
            case "patient" -> menu = new PatientMenu();
            default -> {
                System.out.println("Невідома роль. Доступ заборонено.");
                menu = null;
            }
        }
    }

    @Override
    public void showMenu() {
        if (menu != null) {
            menu.showMenu();
        }
    }

    @Override
    public void handleMenu() {
        if (menu != null) {
            menu.handleMenu();
        }
    }
}