package Systemdesign.Observer;

public class ObserverA implements ObserverI{

    @Override
    public void update(String object) {
        System.out.println("Updating the observer A -> " + object);
    }
}
