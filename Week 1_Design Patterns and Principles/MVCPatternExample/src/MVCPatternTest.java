public class MVCPatternTest {
    public static void main(String[] args) {
        // Create a student record
        Student model = new Student();
        model.setName("Abcd");
        model.setId("12345");
        model.setGrade("A");

        // Create a view to display student details
        StudentView view = new StudentView();

        // Create a controller
        StudentController controller = new StudentController(model, view);

        // Display initial student details
        controller.updateView();

        // Update student details
        controller.setStudentName("Efgh");
        controller.setStudentGrade("B");

        // Display updated student details
        controller.updateView();
    }
}
