package menu;

import dao.EmployeeDao;
import dto.Employee;
import service.DBService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner input = new Scanner(System.in);
    EmployeeDao dao = new EmployeeDao();
    DBService dbService = new DBService(dao);


    public String display() throws Exception {

        while(true) {

            System.out.println("-- Main Menu--");
            System.out.println(
                    "Select an option: \n" +
                            "  1) Insert employee\n" +
                            "  2) Search employee\n" +
                            "  3) List employee salaries by department\n" +
                            "  4) Sair\n "
            );
            int selection = input.nextInt();
            input.nextLine();

            switch (selection) {
                case 1:
                    Employee toSave = readNewEmployee();
                    Employee employee = dbService.insertEmployee(toSave);
                    System.out.println(employee);

                    break;
                case 2:

                    break;
                case 3:
                    List<Employee> employeeSalaryByDept = dbService.listEmployeeSalariesByDepartment();

                    for (Employee employeee : employeeSalaryByDept) {
                        System.out.println(employeee.getFirstName() + " " + employeee.getLastName() + " - " + employeee.getSalary() + " - " + employeee.getDept());
                    }

                    break ;
                case 4:
                    System.exit(0);
                    break ;
                default:
                    System.out.println("Invalid action.");
                    break;
            }
        }
    }

    private Employee readNewEmployee() {

        System.out.println("-- New Employee Menu--");
        System.out.println("Insert date of birth as 'YYYY-MM-dd'");
        String dob = input.next();

        System.out.println("Insert first name");
        String firstName = input.next();

        System.out.println("Insert last name");
        String lastName = input.next();

        System.out.println("Insert gender as 'M' or 'F");
        String gender = input.next();

        Employee toSave = new Employee();
        toSave.setDob(dob);
        toSave.setFirstName(firstName);
        toSave.setLastName(lastName);
        toSave.setGender(gender);

        return toSave;
    }
}
