public class EmployeeManagementTest {
    public static void main(String[] args) {
        EmployeeManagement management = new EmployeeManagement(10);

        // Add employees
        Employee emp1 = new Employee("E001", "Alice", "Manager", 75000);
        Employee emp2 = new Employee("E002", "Bob", "Developer", 60000);
        management.addEmployee(emp1);
        management.addEmployee(emp2);

        // Search employee
        Employee found = management.searchEmployee("E001");
        if (found != null) {
            System.out.println("Found: " + found.getName() + " - " + found.getPosition());
        } else {
            System.out.println("Employee not found.");
        }

        // Traverse employees
        System.out.println("All Employees:");
        management.traverseEmployees();

        // Delete employee
        management.deleteEmployee("E002");
        System.out.println("After Deletion:");
        management.traverseEmployees();
    }
}
