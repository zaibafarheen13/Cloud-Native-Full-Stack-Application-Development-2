package com.lab2.my_lab2;

import java.util.Scanner;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MyLab2Application {

	public static void main(String[] args) {
		SpringApplication.run(MyLab2Application.class, args);
		
		Scanner scan=new Scanner(System.in);
		
		ApplicationContext ac=new ClassPathXmlApplicationContext("testBoot.xml");
		
		Department d=(Department) ac.getBean("department");
		
		while(true) {
			System.out.println("******************************");
			System.out.println("1. Insert\n2. Display\n3. Exit");
			System.out.println("******************************");
			System.out.print("Enter the choice: ");
			int choice=scan.nextInt();
			
			switch(choice){
			case 1:
				System.out.println("\n Insert Department Details");
				System.out.print(" - Name: ");
				d.setDept_name(scan.next());
				System.out.print(" - ID: ");
				d.setDept_id(scan.nextInt());
				System.out.print(" - Desciption: ");
				d.setDept_description(scan.next());
				break;
				
			case 2:
				System.out.println("\nDepartment Details");
				System.out.println(" - Name: "+d.getDept_name());
				System.out.println(" - ID: "+d.getDept_id());
				System.out.println(" - Description: "+d.getDept_description());
				
				College c=d.getCollege_instance();
				
				System.out.println("\nCollege Details");
				System.out.println(" - Name: "+c.getCollege_name());
				System.out.println(" - Address: "+c.getCollege_address());
				break;
				
			case 3:
				System.out.println("\nExiting...");
				System.exit(0);
				
			default:
				System.out.println("\n Invalid Choice");
				
			}
		}
		
		
	}

}
