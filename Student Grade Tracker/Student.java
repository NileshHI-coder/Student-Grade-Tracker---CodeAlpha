public class Student {

    private String name;
    private double marks;
    private String grade;

    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
        this.grade = calculateGrade();
    }

    private String calculateGrade() {

        if (marks >= 90) {
            return "A";
        } else if (marks >= 75) {
            return "B";
        } else if (marks >= 50) {
            return "C";
        } else {
            return "F";
        }
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public String getGrade() {
        return grade;
    }
}