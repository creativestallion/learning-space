package designpatterns.Stratergy;

public class SubtractStrategy implements Strategy{

    @Override
    public Integer calculate(Integer a, Integer b) {
        return b-a;
    }
}
