/*
 * Name/s: Logan Mooneyham 
 * Date: 2/20/2026
 * Program: Course Grades Analyzer - reads CSV grade totals and analyzes A percentages.
 */

import java.util.ArrayList;

public class Course {

    // Required data variables
    private String courseName;
    private ArrayList<Integer> courseGrades; // index 0=A, 1=B, 2=C, 3=D, 4=F

    // Constructor
    public Course(String courseName, ArrayList<Integer> grades) {
        this.courseName = courseName;
        this.courseGrades = new ArrayList<>(grades);
    }

    // Getters
    public String getCourseName() {
        return courseName;
    }

    public ArrayList<Integer> getCourseGrades() {
        return courseGrades;
    }

    // Setters
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseGrades(ArrayList<Integer> courseGrades) {
        this.courseGrades = courseGrades;
    }

    // Returns total number of grades
    public int getTotalGrades() {
        int total = 0;
        for (int value : courseGrades) {
            total += value;
        }
        return total;
    }

    // Returns A percentage
    public double getAPercent() {
        int total = getTotalGrades();
        if (total == 0) {
            return 0.0;
        }
        return (double) courseGrades.get(0) / total * 100.0;
    }

    // Add grades together (used when duplicate course found)
    public void addGrades(ArrayList<Integer> newGrades) {
        for (int i = 0; i < courseGrades.size(); i++) {
            courseGrades.set(i, courseGrades.get(i) + newGrades.get(i));
        }
    }

    @Override
    public String toString() {
        return String.format(
            "%-10s %6d %6d %6d %6d %6d %8d %8.2f",
            courseName,
            courseGrades.get(0),
            courseGrades.get(1),
            courseGrades.get(2),
            courseGrades.get(3),
            courseGrades.get(4),
            getTotalGrades(),
            getAPercent()
        );
    }
}
