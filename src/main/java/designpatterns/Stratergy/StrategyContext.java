package designpatterns.Stratergy;

public class StrategyContext {

    private Strategy strategy;

    public void setStrategy(final Strategy strategy) {
        this.strategy = strategy;
    }

    public Integer execute(final Integer a, final Integer b) {
        return strategy.calculate(a,b);
    }
}
