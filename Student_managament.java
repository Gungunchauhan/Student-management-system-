package com.student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class StudentManagementApp {
    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent(sc);
                case 2 -> viewStudents();
                case 3 -> updateStudent(sc);
                case 4 -> deleteStudent(sc);
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        factory.close();
        sc.close();
    }

    private static void addStudent(Scanner sc) {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // flush
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();

        Student student = new Student(id, name, marks);

        Session session = factory.openSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        session.close();

        System.out.println("Student added.");
    }

    private static void viewStudents() {
        Session session = factory.openSession();
        List<Student> students = session.createQuery("from Student", Student.class).list();
        students.forEach(System.out::println);
        session.close();
    }

    private static void updateStudent(Scanner sc) {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // flush

        Session session = factory.openSession();
        Student student = session.get(Student.class, id);

        if (student != null) {
            System.out.print("Enter new name: ");
            String name = sc.nextLine();
            System.out.print("Enter new marks: ");
            double marks = sc.nextDouble();

            session.beginTransaction();
            student.setName(name);
            student.setMarks(marks);
            session.update(student);
            session.getTransaction().commit();
            System.out.println("Student updated.");
        } else {
            System.out.println("Student not found.");
        }
        session.close();
    }

    private static void deleteStudent(Scanner sc) {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();

        Session session = factory.openSession();
        Student student = session.get(Student.class, id);

        if (student != null) {
            session.beginTransaction();
            session.delete(student);
            session.getTransaction().commit();
            System.out.println("Student deleted.");
        } else {
            System.out.println("Student not found.");
        }
        session.close();
    }
}
