public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public Customer findCustomerById(String id) {
        // Simulate fetching customer from a data source
        return new Customer(id, "John Doe");
    }
}
