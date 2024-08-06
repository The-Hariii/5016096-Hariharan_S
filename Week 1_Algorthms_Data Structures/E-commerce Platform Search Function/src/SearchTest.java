public class SearchTest {
    public static void main(String[] args) {
        Product[] products = {
            new Product("P001", "Laptop", "Electronics"),
            new Product("P002", "Smartphone", "Electronics"),
            new Product("P003", "Tablet", "Electronics")
        };

        // Linear Search
        Product result = SearchAlgorithms.linearSearch(products, "P002");
        if (result != null) {
            System.out.println("Linear Search: Found " + result.getProductName());
        } else {
            System.out.println("Linear Search: Product not found");
        }

        // Binary Search (assuming the array is sorted)
        result = SearchAlgorithms.binarySearch(products, "P002");
        if (result != null) {
            System.out.println("Binary Search: Found " + result.getProductName());
        } else {
            System.out.println("Binary Search: Product not found");
        }
    }
}
