package systemdesign.Observer;

public interface SubjectInterface {
    void addObserver(ObserverInterface object);
    void removeObserver(ObserverInterface object);
    void notifyObservers(String str);
}
