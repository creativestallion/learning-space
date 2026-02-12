package designpatterns.factoryandstrategy;

public class PaymentStrategyContext {

    // there are two things context strategy do -> 1. set correct strategy.
    private final PaymentStrategy paymentStrategy;

    public PaymentStrategyContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void execute(int amount) {
         paymentStrategy.pay(amount);
    }

}
