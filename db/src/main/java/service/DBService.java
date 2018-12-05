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
                    .append("insert into employees (emp_no, birth_date, first_name, last_name, gender, hire_date,")
                    .append("salary, from_date, to_date")
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
                    .append("', '")
                    .append(toSave.getSalary())
                    .append("', '")
                    .append(toSave.getFrom_date())
                    .append("', '")
                    .append(toSave.getTo_date())
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
                savedEmployee.setSalary(insertResultSet.getInt("salary"));
                savedEmployee.setFrom_date(insertResultSet.getString("from_date"));
                savedEmployee.setTo_date(insertResultSet.getString("to_date"));
                return savedEmployee;
            }
        }
        return null;
    }

    public List<Employee> EmployeeList() throws ClassNotFoundException, SQLException {

        Statement statement = DB.INSTANCE().connection().createStatement();

        String salary="select * from salaries where salary>=1000;";

        ResultSet ListresultSet = statement.executeQuery(salary);
        List<Employee> salaryList = new ArrayList();


        while (ListresultSet.next()){

            Employee Employee_salary= new Employee();

            Employee_salary.setNumber(ListresultSet.getInt("emp_no"));
            Employee_salary.setSalary(ListresultSet.getInt("salary"));
            Employee_salary.setFrom_date(ListresultSet.getString("from_date"));
            Employee_salary.setTo_date(ListresultSet.getString("to_date"));

            salaryList.add(Employee_salary);

        }
        System.out.println(salaryList);
        return salaryList;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DBService teste = new DBService();
        teste.EmployeeList();
        //System.out.println(teste.myList());
    }
}



