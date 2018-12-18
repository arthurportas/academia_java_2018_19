package Converter;

import dto.Employee;
import dto.InsertEmployeeRequest;
import dto.InsertEmployeeResponse;
import Dao.EmployeeDao;

import java.sql.SQLException;

public class Converter {

    public InsertEmployeeResponse convertEmployee(InsertEmployeeRequest request) throws SQLException, ClassNotFoundException {

        EmployeeDao dao = new EmployeeDao();
        Employee toSave = new Employee();

        toSave.setFirstName(request.getFirstName());
        toSave.setLastName(request.getLastName());
        toSave.setGender(request.getGender());
        toSave.setDob(request.getDob());
        toSave.setHireDate(request.getHireDate());
        toSave.setNumber(request.getNumber());

        Employee savedEmployee = dao.insertEmployee(toSave);

        InsertEmployeeResponse response = new InsertEmployeeResponse();
        response.setFirstName(savedEmployee.getFirstName());
        response.setLastName(savedEmployee.getLastName());
        response.setDob(savedEmployee.getDob());
        response.setGender(savedEmployee.getGender());
        response.setHireDate(savedEmployee.getHireDate());
        response.setNumber(savedEmployee.getNumber());

        return response;

    }

}
