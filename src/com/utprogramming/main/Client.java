package com.utprogramming.main;

import com.utprogramming.dao.StudentDao;
import com.utprogramming.dao.StudentDaoInterface;
import com.utprogramming.model.Student;

import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDaoInterface dao = new StudentDao();

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Show All Students");
            System.out.println("3. Search Student By Roll Number");
            System.out.println("4. Delete Student");
            System.out.println("5. Update Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int ch = sc.nextInt();
            sc.nextLine(); // ← flush leftover newline

            switch (ch) {

                case 1:

                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine(); // ← nextLine for full name

                    System.out.print("Enter College Name: ");
                    String clgName = sc.nextLine(); // ← nextLine for full name

                    System.out.print("Enter City: ");
                    String city = sc.nextLine(); // ← nextLine for full name

                    System.out.print("Enter Percentage: ");
                    double percentage = sc.nextDouble();
                    sc.nextLine(); // ← flush leftover newline

                    Student student = new Student(name, clgName, city, percentage);

                    if (dao.insertStudent(student))
                        System.out.println("Student Added Successfully.");
                    else
                        System.out.println("Failed to Add Student.");

                    break;

                case 2:

                    dao.showAllStudent();

                    break;

                case 3:

                    System.out.print("Enter Roll Number: ");
                    int roll = sc.nextInt();
                    sc.nextLine(); // ← flush leftover newline

                    if (!dao.showStudentById(roll))
                        System.out.println("Student Not Found.");

                    break;

                case 4:

                    System.out.print("Enter Roll Number: ");
                    int deleteRoll = sc.nextInt();
                    sc.nextLine(); // ← flush leftover newline

                    if (dao.delete(deleteRoll))
                        System.out.println("Student Deleted Successfully.");
                    else
                        System.out.println("Student Not Found.");

                    break;

                case 5:

                    System.out.print("Enter Roll Number: ");
                    int updateRoll = sc.nextInt();
                    sc.nextLine(); // ← flush leftover newline

                    System.out.println("\nWhat do you want to update?");
                    System.out.println("1. Name");
                    System.out.println("2. College Name");
                    System.out.println("3. City");
                    System.out.println("4. Percentage");

                    int choice = sc.nextInt();
                    sc.nextLine(); // ← flush leftover newline

                    System.out.print("Enter New Value: ");
                    String value = sc.nextLine(); // ← nextLine so spaces work

                    Student s = new Student();

                    if (dao.update(updateRoll, value, choice, s))
                        System.out.println("Record Updated Successfully.");
                    else
                        System.out.println("Update Failed.");

                    break;

                case 6:

                    System.out.println("Thank You for Using Student Management System.");
                    sc.close();
                    System.exit(0);

                default:

                    System.out.println("Invalid Choice. Try Again.");
            }
        }
    }
}