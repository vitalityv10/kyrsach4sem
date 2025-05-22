package patterns.observer;

import entities.Appointment;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Appointment appointment);
    void setEnabled(boolean enabled);
}
