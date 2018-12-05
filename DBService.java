package service;

import dto.Employee;

import java.sql.*;

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

    public String updateEmployee(Employee toUpdate) throws ClassNotFoundException, SQLException {

        String uEmployee = "update employees set birth_date = ?, first_name = ?, last_name = ?, hire_date = ? where emp_no = ?";
        Connection conn = null;
        PreparedStatement statement = conn.prepareStatement(uEmployee);
        statement.setString(1, "1996-09-04");
        statement.setString(2, "Jorge");
        statement.setString(3, "Cardoso");
        statement.setString(4, "2020-04-09");
        statement.setString(5, "500000");

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing user was updated successfully!");
        }

        String selEmployee = "select * from employees where emp_no = '500000'";

        ResultSet insertResultSet = statement.executeQuery(selEmployee);
        while (insertResultSet.next()) {
            Employee savedEmployee = new Employee();
            savedEmployee.setNumber(insertResultSet.getInt("emp_no"));
            savedEmployee.setDob(insertResultSet.getDate("birth_date").toString());
            savedEmployee.setFirstName(insertResultSet.getString("first_name"));
            savedEmployee.setLastName(insertResultSet.getString("last_name"));
            savedEmployee.setGender(insertResultSet.getString("gender"));
            savedEmployee.setHireDate(insertResultSet.getString("hire_date"));
            return selEmployee;
        }
        return null;
    }

}
