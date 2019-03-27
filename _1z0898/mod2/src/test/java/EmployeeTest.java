import domain.Employee;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeTest {

    @Test
    public void newEmployee_valuesSet_success() {

        Employee employee = new Employee();
        employee.setId(101);
        employee.setName("Jane Smith");
        employee.setSsn("012-34-5678");
        employee.setSalary(120_345.27);

        Employee expected = new Employee();
        expected.setId(101);
        expected.setName("Jane Smith");
        expected.setSsn("012-34-5678");
        expected.setSalary(120_345.27);

//        assertThat(employee.getId()).isEqualTo(101);
//        assertThat(employee.getName()).isEqualTo("Jane Smith");
//        assertThat(employee.getSsn()).isEqualTo("012-34-5678");
//        assertThat(employee.getSalary()).isEqualTo(120_345.27);

        assertThat(employee).isEqualToComparingFieldByField(expected);
    }
}
