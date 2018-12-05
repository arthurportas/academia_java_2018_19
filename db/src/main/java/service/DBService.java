package service;

import db.DB;
import dto.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

        Statement statement = DB.INSTANCE().connection().createStatement();

        String IdQuery = "select emp_no from employees";

        ResultSet resultSet = statement.executeQuery(IdQuery);

        while (resultSet.next()) {

            int updateId= resultSet.getInt("emp_no");

            String updateQuery = new StringBuilder()
                    .append("insert into employees (emp_no, birth_date, first_name, last_name, gender, hire_date)")
                    .append(" values ('")
                    .append(updateId)
                    .append("', '")
                    .append(toUpdate.getDob())
                    .append("', '")
                    .append(toUpdate.getFirstName())
                    .append("', '")
                    .append(toUpdate.getLastName())
                    .append("', '")
                    .append(toUpdate.getGender())
                    .append("', '")
                    .append(LocalDate.now().toString())
                    .append("')")
                    .toString();
            boolean execute = statement.execute(updateQuery);

            String updatedEmployeeQuery = "select * from employees where emp_no = " + updateId;

            ResultSet insertResultSet = statement.executeQuery(updatedEmployeeQuery);
            while (insertResultSet.next()) {
                Employee updatedEmployee = new Employee();
                updatedEmployee.setNumber(insertResultSet.getInt("emp_no"));
                updatedEmployee.setDob(insertResultSet.getDate("birth_date").toString());
                updatedEmployee.setFirstName(insertResultSet.getString("first_name"));
                updatedEmployee.setLastName(insertResultSet.getString("last_name"));
                updatedEmployee.setGender(insertResultSet.getString("gender"));
                updatedEmployee.setHireDate(insertResultSet.getString("hire_date"));
                return updatedEmployee;
            }
        }
        return null;


    }
}
