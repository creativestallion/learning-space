package systemdesign.Singleton;

public class EagerSingletonClass {

    private EagerSingletonClass() {
    }
    private static final EagerSingletonClass INSTANCE = new EagerSingletonClass();

    public static EagerSingletonClass getInstance() {
        return INSTANCE;
    }
}
/*
Final rules to remember (memorize)

1. Singleton instance type = class type

2. Eager init → simplest + thread-safe

3. Lazy init → needs Holder or synchronization

4. Use lazy only if you have a real reason
 */