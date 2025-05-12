package State;

public class Completed implements AppointmentState{
    @Override
    public void handle() {System.out.println("Прийом завершений");}
}
