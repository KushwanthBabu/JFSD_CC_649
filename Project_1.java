import java.util.Scanner;
import java.util.ArrayList;

// Interface: GradingOperations
interface GradingOperations {
    void addStudent(String studentID, String name);
    void addGrade(String studentID, double grade);
    void viewGrades(String studentID);
    double calculateAverage(String studentID);
}

// Concrete Class: GradingSystem
class GradingSystem implements GradingOperations {
    // Inner class to represent a student
    static class Student {
        String studentID;
        String name;
        ArrayList<Double> grades;

        public Student(String studentID, String name) {
            this.studentID = studentID;
            this.name = name;
            this.grades = new ArrayList<>();
        }
    }

    private final ArrayList<Student> students;

    public GradingSystem() {
        this.students = new ArrayList<>();
    }

    @Override
    public void addStudent(String studentID, String name) {
        for (Student student : students) {
            if (student.studentID.equals(studentID)) {
                System.out.println("Student with ID " + studentID + " already exists.");
                return;
            }
        }
        students.add(new Student(studentID, name));
        System.out.println("Student added successfully.");
    }

    @Override
    public void addGrade(String studentID, double grade) {
        for (Student student : students) {
            if (student.studentID.equals(studentID)) {
                student.grades.add(grade);
                System.out.println("Grade added successfully for student " + student.name);
                return;
            }
        }
        System.out.println("Student with ID " + studentID + " not found.");
    }

    @Override
    public void viewGrades(String studentID) {
        for (Student student : students) {
            if (student.studentID.equals(studentID)) {
                if (student.grades.isEmpty()) {
                    System.out.println("No grades recorded for " + student.name);
                } else {
                    System.out.println("Grades for " + student.name + ": " + student.grades);
                }
                return;
            }
        }
        System.out.println("Student with ID " + studentID + " not found.");
    }

    @Override
    public double calculateAverage(String studentID) {
        for (Student student : students) {
            if (student.studentID.equals(studentID)) {
                if (student.grades.isEmpty()) {
                    System.out.println("No grades available for " + student.name);
                    return 0.0;
                }
                double sum = 0;
                for (double grade : student.grades) {
                    sum += grade;
                }
                double average = sum / student.grades.size();
                System.out.println("Average grade for " + student.name + ": " + average);
                return average;
            }
        }
        System.out.println("Student with ID " + studentID + " not found.");
        return 0.0;
    }
}

// Main Class: Menu-Driven Program
public class StudentGradingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GradingSystem gradingSystem = new GradingSystem();

        while (true) {
            System.out.println("\n--- Student Grading System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. View Grades");
            System.out.println("4. Calculate Average");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            // Handle invalid input for menu selection
            if (!sc.hasNextInt()) {
                System.out.println("Invalid choice. Please enter a number.");
                sc.next(); // Consume invalid input
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String studentID = sc.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();
                    gradingSystem.addStudent(studentID, name);
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    String gradeStudentID = sc.nextLine();
                    System.out.print("Enter Grade (numeric value only): ");

                    // Handle non-numeric input
                    if (!sc.hasNextDouble()) {
                        System.out.println("Invalid grade. Please enter a numeric value.");
                        sc.next(); // Consume invalid input
                        break;
                    }

                    double grade = sc.nextDouble();
                    sc.nextLine(); // Consume newline
                    gradingSystem.addGrade(gradeStudentID, grade);
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    String viewStudentID = sc.nextLine();
                    gradingSystem.viewGrades(viewStudentID);
                    break;

                case 4:
                    System.out.print("Enter Student ID: ");
                    String avgStudentID = sc.nextLine();
                    gradingSystem.calculateAverage(avgStudentID);
                    break;

                case 5:
                    System.out.println("Exiting the system...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
