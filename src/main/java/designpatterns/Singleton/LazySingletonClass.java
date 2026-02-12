package designpatterns.Singleton;

public class LazySingletonClass {
    private LazySingletonClass() {

    }

    private LazySingletonClass Holder() {
         final LazySingletonClass instance = new LazySingletonClass();
         return instance;
    }

    public static LazySingletonClass getInstance() {
        return getInstance().Holder();
    }
}
