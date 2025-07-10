// File: PizzaOrder.java
import javafx.beans.property.*;

public class PizzaOrder {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty mobile = new SimpleStringProperty();
    private final StringProperty size = new SimpleStringProperty();
    private final IntegerProperty toppings = new SimpleIntegerProperty();
    private final DoubleProperty total = new SimpleDoubleProperty();

    public PizzaOrder(String name, String mobile, String size, int toppings, double total) {
        this.name.set(name);
        this.mobile.set(mobile);
        this.size.set(size);
        this.toppings.set(toppings);
        this.total.set(total);
    }

    public static double calculateTotal(String size, int toppings) {
        double base = switch (size) {
            case "XL" -> 15.0;
            case "L" -> 12.0;
            case "M" -> 10.0;
            case "S" -> 8.0;
            default -> 0.0;
        };
        double subtotal = base + (toppings * 1.5);
        return Math.round(subtotal * 1.15 * 100.0) / 100.0; // with 15% HST
    }

    // Getters and Property Methods
    public String getName() { return name.get(); }
    public void setName(String n) { name.set(n); }
    public StringProperty nameProperty() { return name; }

    public String getMobile() { return mobile.get(); }
    public void setMobile(String m) { mobile.set(m); }
    public StringProperty mobileProperty() { return mobile; }

    public String getSize() { return size.get(); }
    public void setSize(String s) { size.set(s); }
    public StringProperty sizeProperty() { return size; }

    public int getToppings() { return toppings.get(); }
    public void setToppings(int t) { toppings.set(t); }
    public IntegerProperty toppingsProperty() { return toppings; }

    public double getTotal() { return total.get(); }
    public void setTotal(double t) { total.set(t); }
    public DoubleProperty totalProperty() { return total; }
}
