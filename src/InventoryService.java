import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryService {
    private final List<InventoryItem> items = new ArrayList<>();

    public void addItem(InventoryItem item) {
        items.add(item);
    }

    public List<InventoryItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double calculateInventoryValue() {
        double total = 0;
        for (InventoryItem item : items) {
            total += item.getQuantity() * item.getUnitPrice();
        }
        return total;
    }

    public List<InventoryItem> findLowStockItems(int threshold) {
        List<InventoryItem> result = new ArrayList<>();
        for (InventoryItem item : items) {
            if (item.getQuantity() <= threshold) {
                result.add(item);
            }
        }
        return result;
    }
}
