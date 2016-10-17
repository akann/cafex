package om.capgemini.cafex;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * @author akan
 */
public class CustomerPurchasesTest {

    @Test
    public void testAddItemToCustomerPurchase() throws Exception {

        Assistant assistant = new Assistant();
        Customer customer = new Customer();
        CustomerPurchases customerPurchases = new CustomerPurchases(assistant, customer);

        CafeXMenu menu = new CafeXMenu(
                new HashMap<String, MenuItem>(){{
                    put("cola",           new MenuItem("Cola", "Cold Drink",           0.5));
                    put("coffee",         new MenuItem("Coffee", "Hot Drink",          1.0));
                    put("chess sandwich", new MenuItem("Cheese Sandwich", "Cold Food", 2.0));
                    put("steak sandwich", new MenuItem("Steak Sandwich", "Hot Food",   4.5));
                }}
        );

        int noOfItemsBefore = customerPurchases.getNumberOfItems();

        assertEquals(customerPurchases.getNumberOfItems(), 0);

        customerPurchases.addMenuItem(menu.getMenuItem("cola"));

        assertEquals(customerPurchases.getNumberOfItems(), noOfItemsBefore + 1);

    }
}
