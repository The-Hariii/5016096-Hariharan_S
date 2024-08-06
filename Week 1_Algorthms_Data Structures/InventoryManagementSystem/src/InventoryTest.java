public class InventoryTest {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        // Add products
        Product product1 = new Product("P001", "Laptop", 10, 999.99);
        Product product2 = new Product("P002", "Smartphone", 20, 499.99);
        inventory.addProduct(product1);
        inventory.addProduct(product2);

        // Update product
        product1.setPrice(899.99);
        inventory.updateProduct(product1);

        // Get product
        Product retrievedProduct = inventory.getProduct("P001");
        System.out.println("Retrieved Product: " + retrievedProduct.getProductName() + " - $" + retrievedProduct.getPrice());

        // Delete product
        inventory.deleteProduct("P002");
    }
}
