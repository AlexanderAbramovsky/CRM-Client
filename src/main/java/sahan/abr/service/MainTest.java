package sahan.abr.service;

import sahan.abr.entities.*;
import sahan.abr.repository.*;

import java.sql.*;


public class MainTest {

    private final static String FOR_NAME = "org.sqlite.JDBC";
    private final static String URL = "jdbc:sqlite:testDB.s3db";

    private static Connection connection;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName(FOR_NAME);
        connection = DriverManager.getConnection(URL);

        authorization();
    }

    public static void authorization() throws SQLException {
        AuthorizationRep authorizationRep = new AuthorizationRep(connection);

        authorizationRep.save(new Security("login", "123", null));
    }

    public static void employee() throws ClassNotFoundException, SQLException {
        EmployeeRepository employeeRepository = new EmployeeRepository(connection);
        System.out.println(employeeRepository.getAll());

       //employeeRepository.save(new Employee("1", "2", "3", "4", "5"));

        //employeeRepository.deleteById(2);

        //System.out.println(employeeRepository.getAll());

       // employeeRepository.update(new Employee(3, "5", "5", "5", "5", "6"));

        System.out.println(employeeRepository.getAll());
    }

    public static void subscription() throws ClassNotFoundException, SQLException {
        SubscriptionRepository subscriptionRepository = new SubscriptionRepository(connection);
        System.out.println(subscriptionRepository.getAll());
//        subscriptionRepository.update(new Subscription(4, "� ����������", 123, 12, 12));
//
        System.out.println("_________");
//
//        System.out.println(subscriptionRepository.getAll());
//
//        System.out.println();

        System.out.println(subscriptionRepository.getById(11));

        subscriptionRepository.save(new Subscription(
                "test", 123.5, 123, 123
        ));

        System.out.println("_________");

        subscriptionRepository.update(new Subscription(9, "��������!", 10, 10, 10));

        System.out.println(subscriptionRepository.getAll());
    }

    public static void schedule() throws ClassNotFoundException, SQLException {
        ScheduleRepository scheduleRepository = new ScheduleRepository(connection);
        //System.out.println(scheduleRepository.getAll());

        //System.out.println(scheduleRepository.save(new Schedule(ScheduleRole.CLIENT, 123, "12-12-12", "18:30", "19:30")));
//        System.out.println(scheduleRepository.save(new Schedule(ScheduleRole.EMPLOYEE, 999, "20-2-20", "21:30", "19:30")));

        System.out.println(scheduleRepository.deleteById(4));

//        System.out.println("________________");
//        System.out.println(scheduleRepository.getAll());

        //scheduleRepository.deleteById(2);
//
       //System.out.println("________________");
       //System.out.println(scheduleRepository.getAll());
//
//
//        System.out.println(scheduleRepository.update(new Schedule(3, ScheduleRole.EMPLOYEE, 100, "20-3-20", "21:30", "19:30")));
//
        System.out.println("________________");
        System.out.println(scheduleRepository.getAll());
//
//
        System.out.println("________________");
        System.out.println(scheduleRepository.getById(3));
    }

    public static void client() throws ClassNotFoundException, SQLException {
        ClientRepository clientRepository = new ClientRepository(connection);
        System.out.println(clientRepository.getAll());

        //System.out.println(clientRepository.save(new Client(1, 1, 1, "1", "1", "1", "1", "1")));
        System.out.println(clientRepository.update(new Client(2, 2, 1, 2, "1", "2", "1", "2", "1")));

        System.out.println("________________");
        System.out.println(clientRepository.getAll());
    }
}
