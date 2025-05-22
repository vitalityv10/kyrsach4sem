package patterns.State;

public class InProgress implements AppointmentState{
    @Override
    public void handle() {System.out.println("Прийом триває");}
}
