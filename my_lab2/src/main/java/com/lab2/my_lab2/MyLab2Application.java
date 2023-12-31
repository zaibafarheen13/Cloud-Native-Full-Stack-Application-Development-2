package com.lab2.my_lab2;

import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class MyLab2Application {

    public static void main(String[] args) {
        SpringApplication.run(MyLab2Application.class, args);

        Scanner sc = new Scanner(System.in);
        ApplicationContext ac = new ClassPathXmlApplicationContext("testBoot.xml");
        Department d = (Department) ac.getBean("department");

        while (true) {
        	System.out.println("\n*****************************************");
            System.out.println("1. Insert Department details\n" +
                    "2. Display Department with College details \n" +
                    "3. Exit");
            System.out.println("\n*****************************************");
            System.out.print("Enter your choice: ");
            int choice = validateInput();

            switch (choice) {
                case 1:
                    System.out.println("\nInsert Department Details");
                    System.out.print(" - Enter Department Name: ");
                    d.setDept_name(sc.next());
                    d.setDept_id(validatePositiveIntInput(" - Enter Department ID: "));
                    System.out.print(" - Enter Department Description: ");
                    d.setDept_description(sc.next());
                    System.out.println("\nDetails inserted successfully");
                    break;
                case 2:
                    System.out.println("\nDepartment Details:");
                    System.out.println(" - Name: " + d.getDept_name());
                    System.out.println(" - ID: " + d.getDept_id());
                    System.out.println(" - Description: " + d.getDept_description());
                    System.out.println("\nCollege Details");
                    College c = d.getCollege_instance();
                    System.out.println(" - College Name: " + c.getCollege_name());
                    System.out.println(" - College Address: " + c.getCollege_address());
                    break;
                case 3:
                    System.out.println("\nExiting...");
                    System.exit(0);
                default:
                    System.out.println("\nInvalid Choice");
                    break;
            }
        }
    }

    private static int validateInput() {
        int input = 0;
        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                input = scan.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("   Invalid input. Please enter a valid integer choice.");
                System.out.print("Enter the choice: ");
                scan.nextLine();
            }
        }
        return input;
    }

    private static int validatePositiveIntInput(String prompt) {
        Scanner scan = new Scanner(System.in);
        int input = 0;
        while (true) {
            try {
                System.out.print(prompt);
                input = scan.nextInt();
                if (input > 0) {
                    break;
                } else {
                    System.out.println("   Invalid input. Please enter a positive integer.");
                }
            } catch (Exception e) {
                System.out.println("   Invalid input. Please enter a valid integer.");
                scan.nextLine();
            }
        }
        return input;
    }
}
