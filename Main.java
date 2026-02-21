//Name: Logan Mooneyham
//Date: 2/20/2026
//Program: Course Grades Analyzer - reads CSV grade totals and analyzes A percentages.
 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Course> courses = new ArrayList<>();

        try {
            File file = new File("courseAndGradesData.csv");
            Scanner fileScanner = new Scanner(file);

            if (fileScanner.hasNextLine()) {
            fileScanner.nextLine(); // Skip first metadata/header line
        }

            while (fileScanner.hasNextLine()) {
    String line = fileScanner.nextLine().trim();

    if (line.isEmpty()) {
        continue;
    }

    String[] parts = line.split(",");

            if (parts.length < 6) {
            continue;
            }

            String courseName = parts[0].trim();

            if (courseName.equalsIgnoreCase("Course") ||
            courseName.equalsIgnoreCase("Total") ||
            courseName.toLowerCase().contains("count")) {
            continue;
    }

            ArrayList<Integer> grades = new ArrayList<>();

            boolean validRow = true;

            for (int i = 1; i <= 5; i++) {
        try {
            grades.add(Integer.parseInt(parts[i].trim()));
        } catch (Exception e) {
            validRow = false;
            break;
        }
    }

            if (!validRow) {
             continue;
          }

            courses.add(new Course(courseName, grades));
        }

            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }

        // Print table header
        System.out.printf("%-12s %6s %6s %6s %6s %6s %8s %8s\n",
                "Course", "A", "B", "C", "D", "F", "Total", "A%");

        // Print summary table
        for (Course c : courses) {
            System.out.println(c);
        }

        // Find course with highest A%
        if (courses.size() > 0) {
            Course best = courses.get(0);

            for (Course c : courses) {
                if (c.getAPercent() > best.getAPercent()) {
                    best = c;
                }
            }

            System.out.println("\nCourse with Highest A%:");
            System.out.println(best);
        }

        // Linear search
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter a course name to search: ");
        String searchName = input.nextLine();

        boolean foundCourse = false;

        for (Course c : courses) {
            if (c.getCourseName().equalsIgnoreCase(searchName)) {
                System.out.println("\nCourse Found:");
                System.out.println(c);
                foundCourse = true;
                break;
            }
        }

        if (!foundCourse) {
            System.out.println("Course not found.");
        }

        input.close();
    }
}