package patterns.State;

public class Scheduled implements AppointmentState{
    @Override
    public void handle() {System.out.println("Прийом заплановано.");}
}
