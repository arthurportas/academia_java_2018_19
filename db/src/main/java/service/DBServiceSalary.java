package service;

import db.DB;
import dto.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DBServiceSalary {

    public List<Employee> myList()  throws ClassNotFoundException, SQLException {

        Statement statement = DB.INSTANCE().connection().createStatement();
        Scanner input = new Scanner(System.in);

        System.out.println("Introduza o salário:");
        double salario = input.nextDouble();

      // String listSalaries = "select * from salaries";

        String query = new StringBuilder()
                .append("select * from salaries where salary>=").append(salario).toString();

      //  ResultSet listResultSet = statement.executeQuery(listSalaries);
        ResultSet listResultSet2 = statement.executeQuery(query);

        List<Employee> salaryList = new ArrayList<Employee>();

        while (listResultSet2.next()) {

            Employee salaryEmployee = new Employee();
            salaryEmployee.setNumber(listResultSet2.getInt("emp_no"));
            salaryEmployee.setSalary(listResultSet2.getInt("salary"));
            salaryEmployee.setFrom_date(listResultSet2.getString("from_date"));
            salaryEmployee.setTo_date(listResultSet2.getString("to_date"));

                salaryList.add(salaryEmployee);
        }

        for (Employee employee:salaryList){
            System.out.println(employee.toString());
        }

        return salaryList;

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        DBServiceSalary exemplo = new DBServiceSalary();

        exemplo.myList();

    }



}
