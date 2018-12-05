package service;

import db.DB;
import dto.Employee;

import java.sql.*;
import java.time.LocalDate;

public class DBService {

    public Employee insertEmployee(Employee toSave) throws ClassNotFoundException, SQLException {

        Statement statement = DB.INSTANCE().connection().createStatement();

        String lastIdQuery = "select max(emp_no) as last_id from employees";

        ResultSet resultSet = statement.executeQuery(lastIdQuery);

        while (resultSet.next()) {

            int lastId = resultSet.getInt("last_id");
            int nextId = lastId + 1;
            String insertQuery = new StringBuilder()
                    .append("insert into employees (emp_no, birth_date, first_name, last_name, gender, hire_date)")
                    .append(" values ('")
                    .append(nextId)
                    .append("', '")
                    .append(toSave.getDob())
                    .append("', '")
                    .append(toSave.getFirstName())
                    .append("', '")
                    .append(toSave.getLastName())
                    .append("', '")
                    .append(toSave.getGender())
                    .append("', '")
                    .append(LocalDate.now().toString())
                    .append("')")
                    .toString();
            boolean execute = statement.execute(insertQuery);

            String savedEmployeeQuery = "select * from employees where emp_no = " + nextId;

            ResultSet insertResultSet = statement.executeQuery(savedEmployeeQuery);
            while (insertResultSet.next()) {
                Employee savedEmployee = new Employee();
                savedEmployee.setNumber(insertResultSet.getInt("emp_no"));
                savedEmployee.setDob(insertResultSet.getDate("birth_date").toString());
                savedEmployee.setFirstName(insertResultSet.getString("first_name"));
                savedEmployee.setLastName(insertResultSet.getString("last_name"));
                savedEmployee.setGender(insertResultSet.getString("gender"));
                savedEmployee.setHireDate(insertResultSet.getString("hire_date"));
                return savedEmployee;
            }
        }
        return null;
    }

    public Employee updateEmployee(Employee toUpdate) throws ClassNotFoundException, SQLException {

        Connection newConnection = null;
        String updateparameters = "";

        PreparedStatement statement = newConnection.prepareStatement(updateparameters);

        String updateEmployee = "update employees set birth_date = ?, first_name = ?, last_name = ?, hire_date = ? where emp_no = ?";

        statement.setString(1, "30001-10-10");
        statement.setString(2, "Carlos");
        statement.setString(3, "Castanheira");
        statement.setString(4, "2000-10-01");
        statement.setString(5, "500007");

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("The existing user has been updated successfully");
        }

        String selectEmployeeQuery = "select * from employees where emp_no = 50007";

        ResultSet insertResultSet = statement.executeQuery(selectEmployeeQuery);
        while (insertResultSet.next()) {
            Employee newSavedEmployee = new Employee();
            newSavedEmployee.setDob(insertResultSet.getDate("birth_date").toString());
            newSavedEmployee.setFirstName(insertResultSet.getString("first_name"));
            newSavedEmployee.setLastName(insertResultSet.getString("last_name"));
            newSavedEmployee.setGender(insertResultSet.getString("gender"));
            newSavedEmployee.setHireDate(insertResultSet.getString("hire_date"));
            return newSavedEmployee;

        }
    return null;
    }

}