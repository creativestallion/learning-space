package designpatterns.factoryandstrategy;

// so the only purpose of this class is instansiating the correct object
public class PaymentStrategyFactory {

    public static PaymentStrategy decidePaymentStrategy(String type) {
        if(type.equalsIgnoreCase("card")){
            return new CreditCard();
        } else if(type.equalsIgnoreCase("upi")) {
            return new UPIPayment();
        } else {
            throw new IllegalArgumentException("Not a valid strategy type");
        }
    }
}
