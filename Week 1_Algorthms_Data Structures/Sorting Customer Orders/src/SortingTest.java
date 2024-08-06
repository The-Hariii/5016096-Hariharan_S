public class SortingTest {
    public static void main(String[] args) {
        Order[] orders = {
            new Order("O001", "Alice", 250.0),
            new Order("O002", "Bob", 150.0),
            new Order("O003", "Charlie", 300.0)
        };

        // Bubble Sort
        SortingAlgorithms.bubbleSort(orders);
        System.out.println("Bubble Sort:");
        for (Order order : orders) {
            System.out.println(order.getOrderId() + ": " + order.getTotalPrice());
        }

        // Quick Sort
        orders = new Order[]{
            new Order("O001", "Alice", 250.0),
            new Order("O002", "Bob", 150.0),
            new Order("O003", "Charlie", 300.0)
        };
        SortingAlgorithms.quickSort(orders, 0, orders.length - 1);
        System.out.println("Quick Sort:");
        for (Order order : orders) {
            System.out.println(order.getOrderId() + ": " + order.getTotalPrice());
        }
    }
}
