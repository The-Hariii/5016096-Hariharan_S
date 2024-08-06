public class TaskManagementTest {
    public static void main(String[] args) {
        SinglyLinkedList taskList = new SinglyLinkedList();

        // Add tasks
        Task task1 = new Task("T001", "Design Database", "Pending");
        Task task2 = new Task("T002", "Develop API", "In Progress");
        taskList.addTask(task1);
        taskList.addTask(task2);

        // Search task
        Task found = taskList.searchTask("T001");
        if (found != null) {
            System.out.println("Found: " + found.getTaskName() + " - " + found.getStatus());
        } else {
            System.out.println("Task not found.");
        }

        // Traverse tasks
        System.out.println("All Tasks:");
        taskList.traverseTasks();

        // Delete task
        taskList.deleteTask("T002");
        System.out.println("After Deletion:");
        taskList.traverseTasks();
    }
}
