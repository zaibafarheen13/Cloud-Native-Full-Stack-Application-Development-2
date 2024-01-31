/*
 * package com.lab1.my_lab1; import java.util.Scanner;
 * 
 * import org.springframework.boot.SpringApplication; import
 * org.springframework.boot.autoconfigure.SpringBootApplication; import
 * org.springframework.context.ApplicationContext; import
 * org.springframework.context.support.ClassPathXmlApplicationContext;
 * 
 * @SpringBootApplication public class MyLab1Application {
 * 
 * public static void main(String[] args) {
 * SpringApplication.run(MyLab1Application.class, args);
 * 
 * Scanner sc = new Scanner(System.in);
 * 
 * ApplicationContext ac = new ClassPathXmlApplicationContext("Testboot.xml");
 * 
 * Customer c = (Customer) ac.getBean("customer"); Ticket t = (Ticket)
 * c.getTicket_instance();
 * 
 * while (true) { System.out.println();
 * System.out.println("*****************************************");
 * System.out.println("Welcome to the Ticket Management System");
 * System.out.println("\n 1. Insert the details\n 2. Display details \n 3. Exit"
 * ); System.out.print("Enter the choice: "); int a = sc.nextInt();
 * System.out.println("*****************************************"); switch (a) {
 * case 1:
 * 
 * System.out.println(); System.out.println("\n Customer Details");
 * System.out.print(" - Enter Customer Name: "); c.setName(sc.next());
 * 
 * System.out.print(" - Enter Customer Address: "); c.setAddress(sc.next());
 * 
 * System.out.println(); System.out.println("\n Ticket Details");
 * System.out.print(" - Enter Ticket Number: ");
 * t.setTicket_number(sc.nextInt());
 * 
 * System.out.print(" - Enter Ticket Seat Number: ");
 * t.setSeat_number(sc.nextInt());
 * 
 * System.out.print(" - Enter Ticket Price: "); t.setPrice(sc.nextInt());
 * 
 * System.out.print(" - Enter Ticket Type - economical/business: ");
 * t.setTicket_type(sc.next());
 * 
 * System.out.println(); System.out.println("\nDetails entered successfully.");
 * break;
 * 
 * case 2: System.out.println();
 * System.out.println("-----------------------------------------------");
 * System.out.println("Customer and Ticket Details");
 * System.out.println("-----------------------------------------------");
 * 
 * System.out.println("Name: " + c.getName()); System.out.println("Address: " +
 * c.getAddress()); System.out.println("Ticket Number: " +
 * t.getTicket_number()); System.out.println("Ticket Type: " +
 * t.getTicket_type()); System.out.println("Ticket Seat Number: " +
 * t.getSeat_number()); System.out.println("Ticket Price: " + t.getPrice());
 * System.out.println("-----------------------------------------------"); break;
 * 
 * case 3: System.out.println("Exiting..."); System.exit(0);
 * 
 * default: System.out.println("Invalid choice. Please try again."); } } } }
 * 
 * 
 */

package com.lab1.my_lab1; 
import java.util.InputMismatchException;
import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class MyLab1Application {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(MyLab1Application.class, args);

        ApplicationContext ac = new ClassPathXmlApplicationContext("Testboot.xml");

        Customer c = (Customer) ac.getBean("customer");
        Ticket t = (Ticket) c.getTicket_instance();

        while (true) {
            System.out.println();
            System.out.println("*****************************************");
            System.out.println("Welcome to the Ticket Management System");
            System.out.println("\n 1. Insert the details\n 2. Display details \n 3. Exit");

            int choice;
            do {
                System.out.print("Enter the choice: ");
                choice = validateInput();
                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid choice. Please try again.");
                }
            } while (choice < 1 || choice > 3);

            System.out.println("*****************************************");

            switch (choice) {
                case 1:
                    System.out.println("\n Customer Details");
                    c.setName(validateStringInput(" - Enter Customer Name: ", false));
                    c.setAddress(validateStringInput(" - Enter Customer Address: ", false));

                    System.out.println("\n Ticket Details");
                    t.setTicket_number(validatePositiveIntInput(" - Enter Ticket Number: "));
                    t.setSeat_number(validatePositiveIntInput(" - Enter Ticket Seat Number: "));
                    t.setPrice(validatePositiveIntInput(" - Enter Ticket Price: "));
                    t.setTicket_type(validateTicketTypeInput(" - Enter Ticket Type - economical/business: "));

                    System.out.println("\nDetails entered successfully.");
                    break;

                case 2:
                    displayDetails(c, t);
                    break;

                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static int validateInput() {
        int input = 0;
        while (true) {
            try {
                input = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer choice.");
                System.out.print("Enter the choice: ");
                sc.nextLine(); // consume the invalid input
            }
        }
        return input;
    }

    private static String validateStringInput(String prompt, boolean allowNumbers) {
        String input = "";
        while (true) {
            System.out.print(prompt);
            input = sc.next();
            if (allowNumbers || isValidAlphabeticInput(input)) {
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid input with alphabetic characters only.");
            }
        }
        return input;
    }

    private static int validatePositiveIntInput(String prompt) {
        int input = 0;
        while (true) {
            try {
                System.out.print(prompt);
                input = sc.nextInt();
                if (input > 0) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a positive integer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                sc.nextLine(); // consume the invalid input
            }
        }
        return input;
    }

    private static String validateTicketTypeInput(String prompt) {
        String ticketType = "";
        while (true) {
            System.out.print(prompt);
            ticketType = sc.next().toLowerCase();
            if (ticketType.equals("economical") || ticketType.equals("business")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter 'economical' or 'business' for Ticket Type.");
            }
        }
        return ticketType;
    }

    private static boolean isValidAlphabeticInput(String input) {
        // Check if the input contains only alphabetic characters
        return !input.isEmpty() && input.matches("[a-zA-Z]+");
    }

    private static void displayDetails(Customer c, Ticket t) {
        System.out.println("-----------------------------------------------");
        System.out.println("Customer and Ticket Details");
        System.out.println("-----------------------------------------------");

        System.out.println("Name: " + c.getName());
        System.out.println("Address: " + c.getAddress());
        System.out.println("Ticket Number: " + t.getTicket_number());
        System.out.println("Ticket Type: " + t.getTicket_type());
        System.out.println("Ticket Seat Number: " + t.getSeat_number());
        System.out.println("Ticket Price: " + t.getPrice());
        System.out.println("-----------------------------------------------");
    }
}
