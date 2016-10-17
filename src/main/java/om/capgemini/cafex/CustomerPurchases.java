package om.capgemini.cafex;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author akan
 */
class CustomerPurchases {
    private final Assistant assistant;
    private final Customer customer;
    private final static double serviceChargePercent = 10.0;
    private final static double serviceChargePercentHotFood = 20.0;

    private final List<MenuItem> items = new ArrayList<>();

    CustomerPurchases(Assistant assistant, Customer customer) {
        this.assistant = assistant;
        this.customer = customer;
    }

    void addMenuItem(MenuItem menuItem) {
        items.add(menuItem);
    }

    int getNumberOfItems() {
        return items.size();
    }

    List<MenuItem> getItems() {
        return items;
    }

    double getTotalPrice() {
        return items.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    boolean hasHotFood() {
        long count = items.stream().filter( i -> i instanceof HotFoodItem).count();

        return count > 0;
    }

    double getServiceCharge() {
        double serviceCharge = hasHotFood() ? serviceChargePercentHotFood : serviceChargePercent;
        return getTotalPrice() * (serviceCharge/100);
    }

    public String getServiceChargeRounded() {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(getServiceCharge());
    }

    public double getCappedServiceCharge() {
        double serviceCharge = getServiceCharge();
        return serviceCharge > 2.0 ? 2.0 : serviceCharge;
    }
}
