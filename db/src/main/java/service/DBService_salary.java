package service;

import db.DB;
import dto.Employee_salary;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DBService_salary {




    public List myList()  throws ClassNotFoundException, SQLException {

      /*  Class.forName("com.mysql.cj.jdbc.Driver");
        String jdbc = "jdbc:mysql://localhost/employees?user=root&password=password";

       Connection connection = DriverManager.getConnection(jdbc);

        Statement st = connection.createStatement();*/


        Statement statement = DB.INSTANCE().connection().createStatement();


        String listSalaries = "select * from salaries where salary>=1000;";


        ResultSet listResultSet = statement.executeQuery(listSalaries);

        while (listResultSet.next()) {


            List salaryList = new ArrayList();
            salaryList.add((listResultSet.getInt("emp_no")));
            salaryList.add(listResultSet.getInt("salary"));
            salaryList.add(listResultSet.getString("from_date"));
            salaryList.add(listResultSet.getString("to_date"));

            return salaryList;
        }

        return null;
    }





}
