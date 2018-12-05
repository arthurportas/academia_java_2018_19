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

    public List<Employee> listEmployeeSalariesByDepartment() throws ClassNotFoundException, SQLException {

        List employeeList = new ArrayList<>();

        Statement statement = DB.INSTANCE().connection().createStatement();

        String dbQuery = "select e.emp_no as empId, e.first_name as firstName, e.last_name as lastName, sum(s.salary) as salary, d.dept_name as dept" +
                " from employees e" +
                " join salaries s on e.emp_no = s.emp_no" +
                " join current_dept_emp ed on e.emp_no = ed.emp_no " +
                " join departments d on d.dept_no = ed.dept_no " +
                " where e.emp_no > 499990" +
                " group by e.emp_no3;";

        ResultSet rs = statement.executeQuery(dbQuery);


        while(rs.next()) {
            Employee newEmployee = new Employee();
            newEmployee.setNumber(rs.getLong("empId"));
            newEmployee.setFirstName(rs.getString("firstName").toString());
            newEmployee.setLastName(rs.getString("lastname"));
            newEmployee.setSalary(rs.getLong("salary"));
            newEmployee.setDept(rs.getString("dept"));

            employeeList.add(newEmployee);
        }

        return employeeList;
    }
}
