package com.lab1.my_lab1;

import java.util.Scanner;
import java.util.InputMismatchException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class MyLab1Application {

	public static void main(String[] args) {
		SpringApplication.run(MyLab1Application.class, args);
		
		Scanner scan=new Scanner(System.in);
		
		ApplicationContext ac=new ClassPathXmlApplicationContext("testBoot.xml");
		Customer c=(Customer)ac.getBean("customer");
		Ticket t=(Ticket)c.getTicket_instance();
		
		while(true) {
			System.out.println("\n*****************************************");
			System.out.println("Ticket Management System");
			System.out.println("1. Insert\n2. Display \n3. Exit");
			System.out.println("*****************************************");
			System.out.print("Enter your choice: ");
			int choice=validateInput();
			switch(choice) {
			case 1:
				System.out.println("\nInsert Customer Detials");
				System.out.print(" - Enter name: ");
				c.setName(scan.next());
				System.out.print(" - Enter Address: ");
				c.setAddress(scan.next());
				System.out.println("\nInsert Ticket Detials");
				t.setTicket_number(validatePositiveIntInput(" - Enter Ticket Number: "));
				System.out.print(" - Enter Ticket Type(economical/business): ");
				t.setTicket_type(scan.next());
				t.setSeat_number(validatePositiveIntInput(" - Enter Seat Number: "));
				t.setPrice(validatePositiveIntInput(" - Enter Ticket Price: "));
				System.out.print("\nDetails inserted successfully");
				System.out.println();
				break;
				
			case 2:
				System.out.println("\nCustomer Detials");
				System.out.println(" - Name: "+c.getName());
				System.out.println(" - Address: "+c.getAddress());
				System.out.println("\nTicket Detials");
				System.out.println(" - Ticket Number: "+t.getTicket_number());
				System.out.println(" - Ticket Type: "+t.getTicket_type());
				System.out.println(" - Seat Number: "+t.getSeat_number());
				System.out.println(" - Ticket Price: "+t.getPrice());
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
		Scanner scan=new Scanner(System.in);
        while (true) {
            try {
                input = scan.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("   Invalid input. Please enter a valid integer choice.");
                System.out.print("Enter the choice: ");
                scan.nextLine(); 
            }
        }
        return input;
	}
	
	private static int validatePositiveIntInput(String prompt) {
		Scanner scan=new Scanner(System.in);
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
            } catch (InputMismatchException e) {
                System.out.println("   Invalid input. Please enter a valid integer.");
                scan.nextLine(); 
            }
        }
        return input;
    }
	

}
