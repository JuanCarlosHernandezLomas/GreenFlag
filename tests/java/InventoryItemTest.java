import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryItemTest {

    @Test
    public void testInventoryItemProperties() {
        InventoryItem item = new InventoryItem("TestItem", 10, 5.25);
        assertEquals("TestItem", item.getName());
        assertEquals(10, item.getQuantity());
        assertEquals(5.25, item.getUnitPrice());
    }
}
