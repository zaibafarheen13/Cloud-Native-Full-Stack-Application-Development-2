package studentDatabase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Student_Main {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public Student_Main() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    public void insert(int id, String usn, String name, String address, int totalmarks) {
        try {
        	if (!transaction.isActive()) {
                transaction = session.beginTransaction();
            }
        	
            Student student = new Student();
            student.setId(id);
            student.setUsn(usn);
            student.setName(name);
            student.setAddress(address);
            student.setTotalmarks(totalmarks);
            session.save(student);
            transaction.commit();
            System.out.println("\nStudent inserted successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(String usn) {
        try {
        	if (!transaction.isActive()) {
                transaction = session.beginTransaction();
            }
        	
            Query query = session.createNativeQuery("delete from Student where usn = :usn");
            query.setParameter("usn", usn);
            int status = query.executeUpdate();
            if (status > 0) {
                System.out.println(usn + " deleted successfully.");
            } else {
                System.out.println(usn + " not found.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public void display() {
        try {
            if (!transaction.isActive()) {
                transaction = session.beginTransaction();
            }

            Query q = session.createQuery("from Student");
            List<Student> students = q.getResultList();

            System.out.println("\nList of Students:");

            for (Student student : students) {
                System.out.println("\n Name: " + student.getName());
                System.out.println("  - USN: " + student.getUsn());
                System.out.println("  - Address: " + student.getAddress());
                System.out.println("  - Total Marks: " + student.getTotalmarks());
                System.out.println();
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public void search(String usn) {
        try {
        	if (!transaction.isActive()) {
                transaction = session.beginTransaction();
            }
        	
            Query<Student> query = session.createQuery("from Student where usn = :usn", Student.class);
            query.setParameter("usn", usn);
            List<Student> students = query.getResultList();

            if (students.isEmpty()) {
                System.out.println("\nStudent with USN " + usn + " not found.");
            } else {
                System.out.println("\nStudent Details:");
                for (Student student : students) {
                    System.out.println(" Name: " + student.getName());
                    System.out.println("  - USN: " + student.getUsn());
                    System.out.println("  - Address: " + student.getAddress());
                    System.out.println("  - Total Marks: " + student.getTotalmarks());
                }
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Session getSession() {
        return session;
    }

    public static void main(String[] args) {
        Student_Main sm = new Student_Main();
        Scanner sc = new Scanner(System.in);

        try {
            while (true) {
            	System.out.println("\nMenu");
                System.out.println("1: Insert");
                System.out.println("2: Delete");
                System.out.println("3: Search");
                System.out.println("4: Display");
                System.out.println("5: Exit");

                System.out.print("\nEnter the choice: ");
                try {
                    int ch = sc.nextInt();

                    switch (ch) {
                        case 1:
                            System.out.println("\nEnter the Student Details to insert");
                            System.out.print(" - Enter the Student id: ");
                            int id = sc.nextInt();
                            System.out.print(" - Enter the Student usn: ");
                            String usn = sc.next();
                            System.out.print(" - Enter the Student name: ");
                            String name = sc.next();
                            System.out.print(" - Enter the Student address: ");
                            String add = sc.next();
                            System.out.print(" - Enter the Student totalmarks: ");
                            int tm = sc.nextInt();
                            sm.insert(id, usn, name, add, tm);
                            break;

                        case 2:
                            System.out.print("\nEnter student usn to delete: ");
                            String usnDelete = sc.next();
                            sm.delete(usnDelete);
                            break;

                        case 3:
                            System.out.print("\nEnter student usn to search: ");
                            String usnSearch = sc.next();
                            sm.search(usnSearch);
                            break;

                        case 4:
                            System.out.println("\nDisplaying all students");
                            sm.display();
                            break;

                        case 5:
                            System.out.println("\nExiting...");
                            return;

                        default:
                            System.out.println("\nInvalid choice! Please make a valid choice.\n");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\nInvalid input. Please enter a number.");
                    sc.nextLine();
                }
            }
        } finally {
            sc.close();
            sm.getSession().close();
        }
    }
}
