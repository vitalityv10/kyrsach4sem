package observer;

import entities.Appointment;

public class AppointmentObserver implements Observer{
    @Override
    public void update(Appointment appointment) {System.out.println("Новий запис: " + appointment);}
}
