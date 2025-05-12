package observer;

import entities.Appointment;

import java.util.*;

public class ObserverManager implements Subject{
  private List<Observer> observers = new ArrayList<>();
  private boolean enabled = true;
    @Override
    public void addObserver(Observer observer) {observers.add(observer);}
    @Override
    public void removeObserver(Observer observer) {observers.remove(observer);}
    @Override
    public void notifyObservers(Appointment appointment) {
        for (Observer observer : observers) observer.update(appointment);}
    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Observer> getObservers() {
        return observers;
    }
}
