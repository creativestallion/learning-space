package designpatterns.factoryandstrategy;

public class UPIPayment implements PaymentStrategy{

    private final int addOnCharges = 5;
    @Override
    public void pay(int amount) {
        final int total = amount+addOnCharges;
        System.out.println("Total amount paid using UPI -> " + total);
    }
}
