package Systemdesign.Stratergy;

public class AddStrategy implements Strategy{
    @Override
    public Integer calculate(Integer a, Integer b) {
        return a+b;
    }
}
