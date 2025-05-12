package State;

public class AppointmentContext {
    private AppointmentState state;

    public void setState(AppointmentState state) {this.state = state;}
    public void apply() {state.handle();}
}
