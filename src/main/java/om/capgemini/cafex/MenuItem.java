package om.capgemini.cafex;

/**
 * @author akan
 */
public class MenuItem {
    private final String name;
    private final String description;
    private final double price;

    public MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
