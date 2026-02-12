package designpatterns.Observer;

public class ObserverA implements ObserverInterface {

    @Override
    public void update(String object) {
        System.out.println("Updating the observer A -> " + object);
    }
}
