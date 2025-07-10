public class BillCalculator {

    public static double calculateTotal(String size, int toppings) {
        double basePrice = 0.0;

        switch (size.toUpperCase()) {
            case "XL":
                basePrice = 15.00;
                break;
            case "L":
                basePrice = 12.00;
                break;
            case "M":
                basePrice = 10.00;
                break;
            case "S":
                basePrice = 8.00;
                break;
            default:
                throw new IllegalArgumentException("Invalid pizza size: " + size);
        }

        double toppingsCost = toppings * 1.50;
        double subtotal = basePrice + toppingsCost;
        double total = subtotal * 1.15; // Adding 15% HST

        // Round to 2 decimal places
        return Math.round(total * 100.0) / 100.0;
    }
}
