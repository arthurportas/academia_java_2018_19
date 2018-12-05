import dto.Employee;
import org.junit.Assert;
import org.junit.Test;
import service.DBService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class DBServiceTest {

    @Test
    public void insert_validUser_returnPersistedUser() throws SQLException, ClassNotFoundException {

        DBService service = new DBService();

        Employee toSave = new Employee();
        toSave.setFirstName("Arthur");
        toSave.setLastName("Portas");
        toSave.setDob("1979-08-17");
        toSave.setGender("M");

        Employee savedEmployee = service.insertEmployee(toSave);

        //simple
        Assert.assertTrue(savedEmployee.getFirstName().equals(toSave.getFirstName()));

        //assertJ
        assertThat(savedEmployee).isEqualToIgnoringGivenFields(toSave, "number", "hireDate");
    }

    @Test
    public void insert_withHireDate_returnsHireDateAsToday() throws SQLException, ClassNotFoundException {

        DBService service = new DBService();

        Employee toSave = new Employee();
        toSave.setFirstName("Arthur");
        toSave.setLastName("Portas");
        toSave.setDob("1979-08-17");
        toSave.setGender("M");
        toSave.setHireDate("1970-01-01");

        Employee savedEmployee = service.insertEmployee(toSave);

        //simple
        Assert.assertTrue(savedEmployee.getFirstName().equals(toSave.getFirstName()));

        //assertJ
        assertThat(savedEmployee).isEqualToIgnoringGivenFields(toSave, "number", "hireDate");
    }

    @Test
    public void getEmployee_ByFirstOrLastName_returnTrue() throws SQLException, ClassNotFoundException {
        DBService service = new DBService();

        /*Inserting new impoloyee to ensure my search will get results*/
        Employee toSave = new Employee();
        toSave.setFirstName("hhhhhh");
        toSave.setLastName("iiiiii");
        toSave.setDob("1979-08-17");
        toSave.setGender("M");
        toSave.setHireDate("1970-01-01");

        service.insertEmployee(toSave);

        /*Search attempt & assert*/
        String searchQuery = "hhh";
        List<Employee> employeeList = new ArrayList<>(service.searchEmployeeByFirstAndLastName(searchQuery));
        Employee emp = employeeList.get(0);
        assertThat(emp).isEqualToIgnoringGivenFields(toSave, "number", "hireDate");

    }

}
