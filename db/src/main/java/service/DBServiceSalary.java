package service;

import db.DB;
import dto.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBServiceSalary {

    public List<Employee> myList()  throws ClassNotFoundException, SQLException {

        Statement statement = DB.INSTANCE().connection().createStatement();

        String listSalaries = "select * from salaries where salary>=1000;";

        ResultSet listResultSet = statement.executeQuery(listSalaries);

        List<Employee> salaryList = new ArrayList();

        while (listResultSet.next()) {

            Employee salaryEmployee = new Employee();
            salaryEmployee.setNumber(listResultSet.getInt("emp_no"));
            salaryEmployee.setSalary(listResultSet.getInt("salary"));
            salaryList.add(salaryEmployee);
        }

        return salaryList;

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        DBServiceSalary exemplo = new DBServiceSalary();

        System.out.println(exemplo.myList());



    }



}
