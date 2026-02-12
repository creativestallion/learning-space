package designpatterns.Stratergy;

public class MultiplyStrategy implements Strategy{

    @Override
    public Integer calculate(Integer a, Integer b) {
        return b*a;
    }
}
