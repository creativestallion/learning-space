package Systemdesign.Stratergy;

public class Calculator {

    private final StrategyContext strategyContext;

    public Calculator(StrategyContext strategyContext) {
        this.strategyContext = strategyContext;
    }

    public Integer calculate(final int num1, final int num2, final int operation) {
        if (operation == 1) {
            System.out.println("Add operation is selected");
            strategyContext.setStrategy(new AddStrategy());
        } else if (operation == 2) {
            System.out.println("Subtract operation is selected");
            strategyContext.setStrategy(new SubtractStrategy());
        } else {
            System.out.println("Multiply operation is selected");
            strategyContext.setStrategy(new MultiplyStrategy());
        }

        return strategyContext.execute(num1, num2);
    }
}
