package patterns;

import UI.menu.*;

public class ProxyMenu implements Menu {
    private Menu menu;

    public ProxyMenu(String role, String id) {
        switch (role.toLowerCase()) {
            case "admin" -> menu = new AdminMenu();
            case "doctor" -> menu = new DoctorMenu(id);
            case "patient" -> menu = new PatientMenu(id);
            default -> {
                System.out.println("Невідома роль. Доступ заборонено.");
                menu = null;
            }
        }
    }

    @Override
    public void showMenu() {if (menu != null) menu.showMenu();}
    @Override
    public void handleMenu() {if (menu != null) menu.handleMenu();}
}