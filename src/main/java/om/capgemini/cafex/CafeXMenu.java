package om.capgemini.cafex;

import java.util.HashMap;

/**
 * @author akan
 */
class CafeXMenu {
    private final HashMap<String, MenuItem> items;

    CafeXMenu(HashMap<String, MenuItem> items) {
        this.items = items;
    }

    public MenuItem getMenuItem(String item) {
        return items.get(item);
    }
}
