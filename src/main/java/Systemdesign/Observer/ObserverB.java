package Systemdesign.Observer;

public class ObserverB implements ObserverI{

    @Override
    public void update(String object) {
        System.out.println("Updating the Observer B  -> " + object);
    }
}
