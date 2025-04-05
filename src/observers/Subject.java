package observers;

import entities.Appointment;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Appointment appointment);
}
