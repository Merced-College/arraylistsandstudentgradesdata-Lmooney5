/*
 * Name/s: Logan Mooneyham
 * Date: 2/20/2026
 * Program: Course Grades Analyzer - reads CSV grade totals and analyzes A percentages.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Course> courses = new ArrayList<>();

        try {
            File file = new File("grades.csv"); 
            Scanner fileScanner = new Scanner(file);

            // Skip header
            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
            }

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                String courseName = parts[0].trim();

                ArrayList<Integer> grades = new ArrayList<>();
                for (int i = 1; i < parts.length; i++) {
                    grades.add(Integer.parseInt(parts[i].trim()));
                }

                // Check for duplicate course
                boolean found = false;
                for (Course c : courses) {
                    if (c.getCourseName().equalsIgnoreCase(courseName)) {
                        c.addGrades(grades);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    courses.add(new Course(courseName, grades));
                }
            }

            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }

        // Print table header
        System.out.printf("%-10s %6s %6s %6s %6s %6s %8s %8s\n",
                "Course", "A", "B", "C", "D", "F", "Total", "A%");

        // Print all courses
        for (Course c : courses) {
            System.out.println(c);
        }

        // Find highest A%
        Course best = courses.get(0);

        for (Course c : courses) {
            if (c.getAPercent() > best.getAPercent()) {
                best = c;
            }
        }

        System.out.println("\nCourse with Highest A%:");
        System.out.println(best);

        // Linear search
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter a course name to search: ");
        String searchName = input.nextLine();

        boolean found = false;

        for (Course c : courses) {
            if (c.getCourseName().equalsIgnoreCase(searchName)) {
                System.out.println("Course found:");
                System.out.println(c);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Course not found.");
        }

        input.close();
    }
}

