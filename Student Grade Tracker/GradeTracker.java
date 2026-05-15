import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GradeTracker extends JFrame {

    ArrayList<Student> students = new ArrayList<>();

    JTextField nameField, marksField;

    JTable table;
    DefaultTableModel model;

    JLabel averageLabel;
    JLabel highestLabel;
    JLabel lowestLabel;
    JLabel totalStudentsLabel;

    public GradeTracker() {

        setTitle("Student Grade Tracker");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        // ---------------- TOP PANEL ----------------

        JPanel topPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        topPanel.setBorder(BorderFactory.createTitledBorder("Enter Student Details"));

        topPanel.add(new JLabel("Student Name:"));

        nameField = new JTextField();
        topPanel.add(nameField);

        topPanel.add(new JLabel("Marks:"));

        marksField = new JTextField();
        topPanel.add(marksField);

        JButton addButton = new JButton("Add Student");
        topPanel.add(addButton);

        JButton clearButton = new JButton("Clear All");
        topPanel.add(clearButton);

        add(topPanel, BorderLayout.NORTH);

        // ---------------- TABLE ----------------

        model = new DefaultTableModel();

        model.addColumn("Student Name");
        model.addColumn("Marks");
        model.addColumn("Grade");

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        // ---------------- BOTTOM PANEL ----------------

        JPanel bottomPanel = new JPanel(new GridLayout(5, 1, 5, 5));

        bottomPanel.setBorder(BorderFactory.createTitledBorder("Performance Report"));

        averageLabel = new JLabel("Average Marks: ");
        highestLabel = new JLabel("Highest Marks: ");
        lowestLabel = new JLabel("Lowest Marks: ");
        totalStudentsLabel = new JLabel("Total Students: 0");

        JButton calculateButton = new JButton("Calculate Report");

        bottomPanel.add(averageLabel);
        bottomPanel.add(highestLabel);
        bottomPanel.add(lowestLabel);
        bottomPanel.add(totalStudentsLabel);
        bottomPanel.add(calculateButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // ---------------- ADD BUTTON ACTION ----------------

        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {

                    String name = nameField.getText().trim();

                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Please enter student name.");
                        return;
                    }

                    double marks = Double.parseDouble(marksField.getText());

                    if (marks < 0 || marks > 100) {
                        JOptionPane.showMessageDialog(null,
                                "Marks must be between 0 and 100.");
                        return;
                    }

                    Student student = new Student(name, marks);

                    students.add(student);

                    model.addRow(new Object[]{
                            student.getName(),
                            student.getMarks(),
                            student.getGrade()
                    });

                    totalStudentsLabel.setText(
                            "Total Students: " + students.size());

                    nameField.setText("");
                    marksField.setText("");

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(null,
                            "Please enter valid numeric marks.");
                }
            }
        });

        // ---------------- CALCULATE BUTTON ACTION ----------------

        calculateButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (students.size() == 0) {

                    JOptionPane.showMessageDialog(null,
                            "No student data available.");

                    return;
                }

                double total = 0;

                double highest = students.get(0).getMarks();
                double lowest = students.get(0).getMarks();

                for (Student s : students) {

                    total += s.getMarks();

                    if (s.getMarks() > highest) {
                        highest = s.getMarks();
                    }

                    if (s.getMarks() < lowest) {
                        lowest = s.getMarks();
                    }
                }

                double average = total / students.size();

                averageLabel.setText(
                        "Average Marks: " + String.format("%.2f", average));

                highestLabel.setText(
                        "Highest Marks: " + highest);

                lowestLabel.setText(
                        "Lowest Marks: " + lowest);
            }
        });

        // ---------------- CLEAR BUTTON ACTION ----------------

        clearButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                students.clear();

                model.setRowCount(0);

                averageLabel.setText("Average Marks: ");
                highestLabel.setText("Highest Marks: ");
                lowestLabel.setText("Lowest Marks: ");
                totalStudentsLabel.setText("Total Students: 0");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {

        new GradeTracker();
    }
} 

