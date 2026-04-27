package designpatterns.Observer;

import java.util.ArrayList;
import java.util.List;

public class ObserverA implements ObserverInterface {

    @Override
    public void update(String object) {
        System.out.println("Updating the observer A -> " + object);
        List< ? extends Number> list = new ArrayList<>();
    }
}
