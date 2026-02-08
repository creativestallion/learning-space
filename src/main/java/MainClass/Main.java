package MainClass;

import Systemdesign.Singleton.SingletonMain;

public class Main {
    
    static void main(String[] args) {
        String instance1 = SingletonMain.getSingletonInstance();
        String instance2 = SingletonMain.getSingletonInstance();
        System.out.println("Instance 1 -> " + instance1);
        System.out.println("Instance 2 -> " + instance2);
    }
}
