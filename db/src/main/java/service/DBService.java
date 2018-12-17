package service;

import dao.EmployeeDao;
import dto.Employee;

import java.util.List;

public class DBService {

    private EmployeeDao dao;

    public DBService(EmployeeDao dao) {
        this.dao = dao;
    }

    public Employee insertEmployee(Employee toSave) throws Exception {

        return dao.insertEmployee(toSave);
    }

    public List<Employee> listEmployeeSalariesByDepartment() throws Exception {

        return dao.listEmployeeSalariesByDepartment();
    }
}
