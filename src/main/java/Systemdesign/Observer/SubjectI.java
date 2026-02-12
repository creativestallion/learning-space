package Systemdesign.Observer;

public interface SubjectI {
    void addObserver(ObserverI object);
    void removeObserver(ObserverI object);
    void notifyObservers(String str);
}
