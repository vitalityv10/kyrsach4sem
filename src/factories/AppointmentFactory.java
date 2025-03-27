package factories;

import entities.Appointment;

public class AppointmentFactory implements AbstractFactory<Appointment>{
    @Override
    public Appointment create(){
        return new Appointment();
    }

}
