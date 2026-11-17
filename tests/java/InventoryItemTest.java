import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryItemTest {

    @Test
    public void testInventoryItemProperties() {
        InventoryItem item = new InventoryItem("Widget", 10, 5.99);
        assertEquals("Widget", item.getName());
        assertEquals(10, item.getQuantity());
        assertEquals(5.99, item.getUnitPrice());
    }
}
