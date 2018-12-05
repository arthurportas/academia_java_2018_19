package service;

import db.DB;
import dto.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public List<Employee> searchEmployeeByFirstAndLastName(String searchFor) throws SQLException, ClassNotFoundException {

        Statement statement = DB.INSTANCE().connection().createStatement();

        List<Employee> employeeList = new ArrayList<>();

        String sql = "select * from employees where first_name LIKE '%"+searchFor+"%' or last_name LIKE '%"+searchFor+"%';";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Employee newEmployee = new Employee();

            newEmployee.setNumber(resultSet.getInt("emp_no"));
            newEmployee.setFirstName(resultSet.getString("first_name"));
            newEmployee.setLastName(resultSet.getString("last_name"));
            newEmployee.setHireDate(resultSet.getString("hire_date"));
            newEmployee.setDob(resultSet.getString("birth_date"));
            newEmployee.setGender(resultSet.getString("gender"));

            employeeList.add(newEmployee);

        }

        return employeeList;
    }
}
