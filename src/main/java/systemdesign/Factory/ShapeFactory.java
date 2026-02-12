package systemdesign.Factory;

public class ShapeFactory {

    public static Shape createShape(String type) {
        if (type.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (type.equalsIgnoreCase("SQUARE")) {
            return new Square();
        } else if(type.equalsIgnoreCase("Trapezium")){
            return new Trapezium();
        }
        throw new IllegalArgumentException("Unknown shape");
    }
}
