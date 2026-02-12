package systemdesign.Observer;

public class ObserverB implements ObserverInterface {

    @Override
    public void update(String object) {
        System.out.println("Updating the Observer B  -> " + object);
    }
}
