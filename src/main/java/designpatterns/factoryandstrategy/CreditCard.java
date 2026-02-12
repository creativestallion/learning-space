package designpatterns.factoryandstrategy;

public class CreditCard implements PaymentStrategy{

    private final int addOnCharges = 10;
    @Override
    public void pay(int amount) {
        int total = addOnCharges + amount;
        System.out.println("Total amount paid using credit card -> " + total);
    }
}
