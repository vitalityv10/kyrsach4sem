import entities.Persons.Doctor;
import entities.Persons.Patient;
import proxies.ProxyMenu;
import storage.DoctorRepository;
import storage.PatientRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoctorRepository doctorRepository = DoctorRepository.getInstance();
        PatientRepository patientRepository = PatientRepository.getInstance();
        System.out.println("Вітаємо в системі реєстрації пацієнтів!");
        System.out.println("Виберіть роль (admin, doctor, patient):");
        String role = scanner.next().toLowerCase();

        ProxyMenu proxyMenu = new ProxyMenu(role);
        proxyMenu.handleMenu();


        System.out.println("Список лікарів у системі:");
        for (Doctor doctor : doctorRepository.getAllDoctors())  System.out.println(doctor);
        System.out.println("Список лікарів у системі:");
        for(Patient patient : patientRepository.getAllPatients()) System.out.println(patient);
//
//        // Отримання лікаря за ID
//        String searchId = "D5";
//        Doctor foundDoctor = doctorRepository.getDoctorById(searchId);
//        if (foundDoctor != null) {
//            System.out.println("\nЗнайдено лікаря за ID " + searchId + ":");
//            System.out.println(foundDoctor);
//        } else {
//            System.out.println("\nЛікаря з ID " + searchId + " не знайдено.");
//        }
    }
}