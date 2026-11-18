import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryServiceTest {

    @Test
    public void testCalculateInventoryValue() {
        InventoryService service = new InventoryService();
        service.addItem(new InventoryItem("Notebook", 12, 2.50));
        service.addItem(new InventoryItem("Pen", 30, 0.75));

        double expectedValue = (12 * 2.50) + (30 * 0.75);
        assertEquals(expectedValue, service.calculateInventoryValue(), 0.001);
    }

    @Test
    public void testFindLowStockItems() {
        InventoryService service = new InventoryService();
        InventoryItem item1 = new InventoryItem("Notebook", 5, 2.50);
        InventoryItem item2 = new InventoryItem("Pen", 50, 0.75);
        service.addItem(item1);
        service.addItem(item2);

        var lowStock = service.findLowStockItems(10);
        assertEquals(1, lowStock.size());
        assertEquals("Notebook", lowStock.get(0).getName());
    }
}
