package Systemdesign.Observer;

import java.util.List;

public class SubjectConcrete implements SubjectI {

    private String msg;

    private final List<ObserverI> observers;

    public SubjectConcrete(List<ObserverI> observers) {
        this.observers = observers;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        notifyObservers(msg);
    }

    @Override
    public void addObserver(ObserverI object) {
        observers.add(object);
    }

    @Override
    public void removeObserver(ObserverI object) {
        try {
            observers.remove(object);
        } catch (Exception e){
            throw new RuntimeException("Object not found in the list");
        }
    }

    @Override
    public void notifyObservers(String str) {
        for(ObserverI object : observers){
            object.update(str);
        }
    }
}
