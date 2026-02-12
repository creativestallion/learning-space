package designpatterns.Observer;

import java.util.List;

public class SubjectConcrete implements SubjectInterface {

    private String msg;

    private final List<ObserverInterface> observers;

    public SubjectConcrete(List<ObserverInterface> observers) {
        this.observers = observers;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        notifyObservers(msg);
    }

    @Override
    public void addObserver(ObserverInterface object) {
        observers.add(object);
    }

    @Override
    public void removeObserver(ObserverInterface object) {
        try {
            observers.remove(object);
        } catch (Exception e){
            throw new RuntimeException("Object not found in the list");
        }
    }

    @Override
    public void notifyObservers(String str) {
        for(ObserverInterface object : observers){
            object.update(str);
        }
    }
}
