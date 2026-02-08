package MainClass;


import Systemdesign.Singleton.EagerSingletonClass;
import Systemdesign.Stratergy.Calculator;
import Systemdesign.Stratergy.StrategyContext;

import java.util.Scanner;

public class Main {

    static void main(String[] args) {
        EagerSingletonClass eagerInstance1 = EagerSingletonClass.getInstance();
        EagerSingletonClass eagerInstance2 = EagerSingletonClass.getInstance();
        System.out.println("Comparing eager instances -> " + eagerInstance2.equals(eagerInstance1));

        Calculator calculator = new Calculator(new StrategyContext());
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter first num ");
        Integer num1 = sc.nextInt();

        System.out.println("Enter second num ");
        Integer num2 = sc.nextInt();

        System.out.println("""
                Select operation
                1 -> add
                2 -> subtract
                3 -> multiply
                """);
        int operation = sc.nextInt();
        int res = calculator.calculate(num1, num2, operation);
        System.out.println("Result -> " + res);
    }
}
