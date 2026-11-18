public class Main {
    public static void main(String[] args) {
        InventoryService inventory = new InventoryService();
        inventory.addItem(new InventoryItem("Notebook", 12, 2.50));
        inventory.addItem(new InventoryItem("Pen", 30, 0.75));

        System.out.println("Inventory items: " + inventory.getItems().size());
        System.out.println("Total inventory value: " + inventory.calculateInventoryValue());
    }
}
