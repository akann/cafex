package om.capgemini.cafex;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * @author akan
 */
public class CustomerPurchasesTest {
    private Assistant assistant;
    private Customer customer;
    private CustomerPurchases customerPurchases;
    private CafeXMenu menu;

    private static final double DELTA = 0.000001;

    @Before
    public void setUp() {
        assistant = new Assistant();
        customer = new Customer();
        customerPurchases = new CustomerPurchases(assistant, customer);

        menu = new CafeXMenu(
                new HashMap<String, MenuItem>(){{
                    put("cola",           new MenuItem("Cola", "Cold Drink",           0.5));
                    put("coffee",         new HotFoodItem("Coffee", "Hot Drink",          1.0));
                    put("chess sandwich", new MenuItem("Cheese Sandwich", "Cold Food", 2.0));
                    put("steak sandwich", new HotFoodItem("Steak Sandwich", "Hot Food",   4.5));
                }}
        );
    }

    @Test
    public void testAddItemToCustomerPurchase() throws Exception {

        int noOfItemsBefore = customerPurchases.getNumberOfItems();

        assertEquals(customerPurchases.getNumberOfItems(), 0);

        customerPurchases.addMenuItem(menu.getMenuItem("cola"));

        assertEquals(customerPurchases.getNumberOfItems(), noOfItemsBefore + 1);
    }

    @Test
    public void testCustomerPurchaseItemPrices() {
        addAllItems();

        for (MenuItem item : customerPurchases.getItems()) {
            switch (item.getName()) {
                case "Cola":
                    assertEquals(item.getPrice(), 0.5, DELTA);
                    break;
                case "Coffee":
                    assertEquals(item.getPrice(), 1.0, DELTA);
                    break;
                case "Cheese Sandwich":
                    assertEquals(item.getPrice(), 2.0, DELTA);
                    break;
                case "Steak Sandwich":
                    assertEquals(item.getPrice(), 4.5, DELTA);
                    break;
            }
        }

        assertEquals(customerPurchases.getTotalPrice(), 8.0, DELTA);
    }

    @Test
    public void testCustomerPurchaseServiceCharge() {
        addAllItems();

        assertEquals(customerPurchases.getServiceCharge(), 1.6, DELTA);
    }

    @Test
    public void testServiceChargeTo2DecimalPlaces() {
        addAllItems();

        assertEquals(customerPurchases.getServiceChargeRounded(), "1.60");
    }

    @Test
    public void testCappedServiceChargeUnder2Pounds() {
        addAllItems();
        assertEquals(customerPurchases.getCappedServiceCharge(), 1.6, DELTA);

    }

    @Test
    public void testCappedServiceChargeOver2Pounds() {
        addAllItems();

        customerPurchases.addMenuItem(menu.getMenuItem("steak sandwich"));
        customerPurchases.addMenuItem(menu.getMenuItem("steak sandwich"));
        customerPurchases.addMenuItem(menu.getMenuItem("steak sandwich"));
        customerPurchases.addMenuItem(menu.getMenuItem("steak sandwich"));

        assertEquals(customerPurchases.getCappedServiceCharge(), 2.0, DELTA);
    }

    @Test
    public void testTotalPricePlusServiceCharge() {
        addAllItems();

        assertEquals(customerPurchases.getCappedServiceCharge() + customerPurchases.getTotalPrice(), 9.6, DELTA);
    }

    private void addAllItems() {
        customerPurchases.addMenuItem(menu.getMenuItem("cola"));
        customerPurchases.addMenuItem(menu.getMenuItem("coffee"));
        customerPurchases.addMenuItem(menu.getMenuItem("chess sandwich"));
        customerPurchases.addMenuItem(menu.getMenuItem("steak sandwich"));
    }
}
